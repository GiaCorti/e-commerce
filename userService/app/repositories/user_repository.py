from typing import Optional
from bson.objectid import ObjectId
from database import user_collection
from models.user_model import UserModel, UserListDto, UserRegisterDto
from responses.base_response import NotFoundException, NotAllowedException, BadRequestException


class UserRepository:
    def __init__(self, connector: any):
        self.connector = connector

    async def add_user(self, user_data: dict) -> UserRegisterDto:

        """
        Add a user into to the database
        :param user_data: user
        :return: new_user
        """

        if str(user_data['email']) == "":
            ex = BadRequestException(description="Email field is required")
            raise ex
        if str(user_data['password']) == "":
            ex = BadRequestException(description="Password field is required")
            raise ex
        user_data['balance'] = 0.0
        user_data['role'] = "USER"
        new_user = user_collection.insert_one(user_data)
        return new_user

    async def retrieve_users(self, id: str):

        """
        Retrieve all users into to the database
        :param id: the admin id
        :return: Users
        """

        user = user_collection.find_one({"_id": ObjectId(id)})
        if user:
            var = str(user['role'])
            if var == "ADMIN":
                users = []
                for user in user_collection.find():
                    users.append(UserListDto(**user))
                return users
            else:
                ex = NotAllowedException(description="Permission required")
                raise ex
        else:
            ex = NotFoundException(description="User not found")
            raise ex

    async def retrieve_user(self, id: str) -> Optional[UserModel]:
        """
        Retrieve a user with a matching ID
        :param id: the user id
        :return: UserModel
        """
        user = user_collection.find_one({"_id": ObjectId(id)})
        if user:
            return UserModel(**user)
        else:
            ex = NotFoundException(description="User not found")
            raise ex

    async def retrieve_user_by_email(self, email: str) -> UserRegisterDto:
        """
        Retrieve a user with a matching email
        :param email: the user email
        :return: UserModel
        """
        user = user_collection.find_one({"email": email})
        if user:
            return UserRegisterDto(**user)

    async def update_user_by_admin(self, id_exec: str, id_user: str, role: str):

        """
        Update a user with a matching ID
        :param data: data to update
        :param id: the admin id
        :return: None
        """
        exec = user_collection.find_one({"_id": ObjectId(id_exec)})
        user = user_collection.find_one({"_id": ObjectId(id_user)})
        if exec and user:
            var = str(exec['role'])
            if var == "ADMIN":
                myquery = {"_id": ObjectId( id_user)}
                new_values = {"$set": {"role": role}}
                user_collection.update_one( myquery, new_values )
                return True
            else:
                ex = NotAllowedException(description="Permission required")
                raise ex
        else:
            ex = NotFoundException(description="User not found")
            raise ex

    async def update_user(self, id: str, data: dict):

        """
        Update a user with a matching ID
        :param data: data to update
        :param id: the user id
        :return: None
        """

        user = user_collection.find_one({"_id": ObjectId(id)})
        if user:
            user_collection.update_one(
                {"_id": ObjectId(id)}, {"$set": data}
            )
            return True
        else:
            ex = NotFoundException(description="User not found")
            raise ex

    async def update_fund(self, id: str, balance: float):

        """
        Update a fund with a matching ID
        :param data: data to update
        :param id: the user id
        :return: None
        """

        user = user_collection.find_one({"_id": ObjectId(id)})
        if user:
            old_balance = float(user['balance'])
            myquery = {"_id": ObjectId(id)}
            new_values = {"$set": {"balance": old_balance+balance}}
            user_collection.update_one(myquery, new_values)
            return True
        else:
            ex = NotFoundException(description="User not found")
            raise ex

    async def delete_user(self, id: str):

        """
        Delete a user from the database
        :param id: the user id
        :return:| None
        """

        user = user_collection.find_one({"_id": ObjectId(id)})
        if user:
            user_collection.delete_one({"_id": ObjectId(id)})
        else:
            ex = NotFoundException(description="User not found")
            raise ex

    async def retrieve_balance(self, id: str) -> dict:

        """
        Retrieve a user balance from the database
        :param id: the user id
        :return:| balance float value
        """

        user = user_collection.find_one({"_id": ObjectId(id)})
        var = float(user['balance'])
        if user:
            return var

    def is_admin(self, id_exec: str):
        exec = user_collection.find_one({"_id": ObjectId(id_exec)})
        var = str(exec['role'])
        if var == "ADMIN":
            return True
        else:
            return False