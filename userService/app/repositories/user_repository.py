from typing import Optional
from bson.objectid import ObjectId
from fastapi.encoders import jsonable_encoder

from database import user_collection
from models.user_model import UserModel


class UserRepository:
    def __init__(self, connector: any):
        self.connector = connector

    async def add_user(self, user_data: dict) -> UserModel:
        """
        Add a user into to the database
        :param user_data: user
        :return: None -> new_user
        """
        user_data=jsonable_encoder(user_data)
        new_user = user_collection.insert_one(user_data)
        return new_user

    async def retrieve_users(self):
        """
        Retrieve all users into to the database
        :param None
        :return:  None -> Users
        """
        users = []
        for user in user_collection.find():
            users.append(UserModel(**user))
        return users

    async def retrieve_user(self, id: str) -> Optional[UserModel]:
        """
        Retrieve a user with a matching ID
        :param id: the user id
        :return: UserModel | None -> user model
        """
        user = user_collection.find_one({"_id": ObjectId(id)})
        if user:
            return UserModel(**user)

    async def retrieve_user_by_fiscal_code(self, fiscal_code: str) -> UserModel:
        """
        Retrieve a user with a matching Fiscal Code
        :param fiscal_code: the user fiscal code
        :return: UserModel | None -> user model
        """
        user = user_collection.find_one({"fiscal_code": fiscal_code})
        if user:
            return UserModel(**user)



    async def update_user(self, fiscal_code: str, data: dict):
        """
        Update a user with a matching ID
        :param fiscal_code: the user fiscal code
        :return: None
        """
        # Return false if an empty request body is sent.
        if len(data) < 1:
            return False
        user = user_collection.find_one({"fiscal_code": fiscal_code})
        if user:
            updated_user = user_collection.update_one(
                {"fiscal_code": fiscal_code}, {"$set": data}
            )
            if updated_user:
                return True
            return False

    # Delete a user from the database
    async def delete_user(self, fiscal_code: str):
        """
        Delete a user from the database
        :param fiscal_code: the user fiscal code
        :return:| None
        """
        user = user_collection.find_one({"fiscal_code": fiscal_code})
        if user:
            user_collection.delete_one({"fiscal_code": fiscal_code})
            return True