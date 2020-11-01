from flask_restful import Resource, reqparse
from flask_jwt import JWT, jwt_required

from modules.item_model import ItemModel
from modules.store_model import StoreModel


class ItemResource(Resource):
    parser = reqparse.RequestParser()
    parser.add_argument('name',
                        type=str,
                        required=False,
                        help="")

    parser.add_argument('price',
                        type=float,
                        required=True,
                        help="This field not br left blank")

    parser.add_argument('store_id',
                        type=int,
                        required=True,
                        help="Every item needs a store id.")

    @jwt_required()
    def get(self, name):
        item = ItemModel.find_by_name(name)
        if item:
            return item.json()
        else:
            return {'message': f'Item with name \'{name}\' not exist.'}

    def post(self, name):
        data = ItemResource.parser.parse_args()
        if ItemModel.find_by_name(name):
            return {'message': f'Item with name {name} already exist'}, 400

        if not StoreModel.find_by_id(data['store_id']):
                return {'message': 'Store with id : {id} not exists'.format(id=data['store_id'])}, 400

        data['name'] = name

        item = ItemModel(**data)

        try:
            item.save_to_db()
            return item.json(), 201
        except:
            return {"message": "An error was ocurred in inserting item"}, 404

    def delete(self, name):
        item = ItemModel.find_by_name(name)
        if (item is None):
            return {'message': f'Item with name {name} not exists'}, 400
        try:
            item.delete_from_db()
        except:
            return {"message": "An error was ocurred in deleting an item"}, 404

        return {'message': f'Item with name : {name} was Deleted'}, 200

    def put(self, name):
        data = self.parser.parse_args()
        item = ItemModel.find_by_name(name)

        if (item):
            item = {'name': item.name, 'price': item.price, 'store_id': item.store_id}
        else:
            return {'message': f'Item with name : {name} not found'}, 404

        if data['name'] is None:
            # prices updates
            item = {**data}
        else:
            # all parameters for a item updates
            item.update(data)
        item = ItemModel(item['name'], item['price'], item['store_id'])
        item.save_to_db()
        return item.json(), 200
