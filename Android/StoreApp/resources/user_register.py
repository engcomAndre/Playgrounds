from flask_restful import Resource, reqparse
from modules.user_model import UserModel


class UserRegister(Resource):
    parser = reqparse.RequestParser()
    parser.add_argument('username',
                        type=str,
                        required=True,
                        help="This field cannot be blank"
                        )

    parser.add_argument('password',
                        type=str,
                        required=True,
                        help="This fielf cannot be blank"
                        )

    def post(self):
        data = UserRegister.parser.parse_args()

        if (UserModel.find_by_username(data['username'])):
            return {"message": f"User with username:\'{data['username']}\' already exists."}, 400

        try:
            user = UserModel(**data)
            user.save_to_db()
        except:
            return {"message": "Error in create user."}, 400

        return {"message": "User create succesfully. "}, 201
