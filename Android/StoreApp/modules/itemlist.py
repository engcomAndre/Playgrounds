from modules.item_model import ItemModel
from flask_restful import Resource

class ItemList(Resource):
    def get(self):
         return {'items' : [item.json() for item in ItemModel.query.all()]}
