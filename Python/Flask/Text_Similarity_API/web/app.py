from flask import Flask, jsonify, request
from flask_restful import Api, Resource
from pymongo import MongoClient
import bcrypt
import spacy


client = MongoClient("mongodb://db:27017")
db = client.SimilarityDB
users = db["users"]

app = Flask(__name__)
api = Api(app)


def UserExist(username):
    return (users.find({"Username": username}).count() > 0)
    
def verify_pw(username, password):
    if not UserExist(username):
        return False
    hash_pw = users.find({"Username" : username})[0]["Password"]
    print(hash_pw)
    return bcrypt.hashpw(password.encode('utf8'),hash_pw) == hash_pw

def countTokens(username):
    return users.find({"Username" : username})[0]["Tokens"]


class Register(Resource):
    def post(self):
        data = request.get_json()

        username = data["username"]
        password = data["password"]

        if UserExist(username):
            return jsonify({
                "status": 301,
                "msg": "Invalid Username"
            })

        hash_pass = bcrypt.hashpw(password.encode('utf8'), bcrypt.gensalt())

        users.insert({
            "Username": username,
            "Password": hash_pass,
            "Tokens": 6
        })


        return jsonify({
            "status": 200,
            "msg": "Sucess"
        })


class Detect(Resource):
    def post(self):
        data = request.get_json()

        username = data["username"]
        password = data["password"]

        text1 = data["text1"]
        text2 = data["text2"]

        if not UserExist(username):
            return jsonify({
                "status": 301,
                "msg": "Invalid Username"
            })

        correct_pw = verify_pw(data["username"],data["password"])

        if(not correct_pw):
            return jsonify({
                "status": 302,
                "msg": "Invalid Password"
            })

        current_tokens = countTokens(username)

        if current_tokens <= 0:
            return jsonify({
                "status": 303,
                "msg": "Tokens nedd refill..."
            })

        # Calculate the edit distance
        nlp = spacy.load('en_core_web_sm')

        text1 = nlp(text1)
        text2 = nlp(text2)

        # Ratio is a float num precision range in 0 and 1
        # -> 1 more similar
        ratio = text1.similarity(text2)

        users.update({
            "Username": username
        }, {
            "$set": {
                "Tokens": current_tokens - 1
            }
        })

        return jsonify({
            "status": 200,
            "similarity": ratio,
            "msg": "Similarity calculated"
        })



class Refill(Resource):
    def post(self):
        data = request.get_json()

        username = data["username"]        
        password = data["admin_pw"]
        refill_amount = data["refill"]

        if not UserExist(username):
            return jsonify({
                "status": 301,
                "msg": "Invalid Username"
            })

        correct_pw = verify_pw(**data)
        correct_pw = "123456"
        if(not correct_pw == password):
            return jsonify({
                "status": 302,
                "msg": "Invalid Password"
            })

        current_tokens = countTokens(username)

        users.update({
            "Username": username
        }, {
            "$set": {
                "Tokens": refill_amount
            }
        })

        return jsonify({
            "status": 200,
            "similarity": ratio,
            "msg": "Similarity calculated"
        })

        return jsonify({
            "status": 200,            
            "msg": "Refill successful"
        })

        
api.add_resource(Register,"/register")
api.add_resource(Detect,"/detect")
api.add_resource(Refill,"/refill")


if __name__ == "__main__":
    app.run(host='0.0.0.0',debug=True)
     




