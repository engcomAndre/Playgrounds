import os

from flask import Flask
from flask_jwt import JWT
from flask_restful import Api

from modules.itemlist import ItemList
from modules.store_list import StoreList
from resources.item_resource import ItemResource
from resources.store_resource import StoreResources
from resources.user_register import UserRegister
from security import authenticate, identity

app = Flask(__name__)
app.secret_key = "MeninoBom"
app.config['SQLALCHEMY_DATABASE_URI'] = os.environ.get('DATABASE_URL', 'sqlite:///data.db')
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
api = Api(app)

jwt = JWT(app, authenticate, identity)

api.add_resource(ItemList, '/items/')
api.add_resource(ItemResource, '/items/<string:name>')
api.add_resource(UserRegister, '/register')
api.add_resource(StoreResources, '/stores/<string:name>')
api.add_resource(StoreList, '/stores')

if (__name__ == '__main__'):
    from db import db

    db.init_app(app)
    app.run(port=5000, debug=True)
