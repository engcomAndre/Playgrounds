from modules.item_model import ItemModel
from flask_restful import Resource

from modules.store_model import StoreModel

class StoreList(Resource):
    def get(self):
        return {'stores': [store.json() for store in StoreModel.query.all()]}