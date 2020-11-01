from flask_restful import Resource
from modules.store_model import StoreModel


class StoreResources(Resource):

    def get(self, name):
        store = StoreModel.find_by_name(name)
        if (store):
            return store.json(), 200
        else:
            return {'message': "Store not found"}, 404
        pass

    def post(self, name):
        if (StoreModel.find_by_name(name)):
            return {'message': "Store with name : {} already exists".format(name)}, 400

        store = StoreModel(name)

        try:
            store.save_to_db()
        except:
            return {'message': "Error was ocurred while creating the stores"}, 500

        return store.json()

    def delete(self, name):
        if (StoreModel.find_by_name(name)):
            return {'message': "Store with name : {} not exists".format(name)}, 400

        store = StoreModel(name)

        try:
            store.delete_to_db()
            return {'message': "Object deleted"}, 500

        except:
            return {'message': "Error was ocurred while deleting the stores"}, 500
