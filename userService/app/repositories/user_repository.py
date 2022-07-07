from typing import Optional
from bson.objectid import ObjectId
from fastapi.encoders import jsonable_encoder

from database import user_collection
from models.user_model import UserModel, UserListDto, Getpass, User_1, _helper


class UserRepository:
    def __init__(self, connector: any):
        self.connector = connector

    async def add_user(self, user_data: dict) -> UserModel:
        """
        Add a user into to the database
        :param user_data: user
        :return: None -> new_user
        """
        user_data = jsonable_encoder(user_data)
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
            users.append(UserListDto(**user))
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

    async def retrieve_user_by_email(self, email: str) -> UserModel:
        """
        Retrieve a user with a matching email
        :param email: the user email
        :return: UserModel | None -> user model
        """
        user = user_collection.find_one({"email": email})
        if user:
            return UserModel(**user)

    async def return_user_password (self, email: str) -> Getpass:
        """
        Retrieve a user with a matching password
        :param password: the user password
        :return: Getpass | None -> Getpass
        """
        user = user_collection.find_one( {"email": email} )
        if user:
            return Getpass  ( **user )

    async def retrieve_id(self, email: str) -> dict:
        user = user_collection.find_one( {"email": email} )
        if user:
            return _helper( user )

    async def update_user(self, email: str, data: dict):

        """
        Update a user with a matching ID
        :param data:
        :param email: the user email
        :return: None
        """

        # Return false if an empty request body is sent.
        if len(data) < 1:
            return False
        user = user_collection.find_one({"email": email})
        if user:
            updated_user = user_collection.update_one(
                {"email": email}, {"$set": data}
            )
            if updated_user:
                return True
            return False

    # Delete a user from the database
    async def delete_user(self, email: str):
        """
        Delete a user from the database
        :param email: the user mail
        :return:| None
        """
        user = user_collection.find_one({"email": email})
        if user:
            user_collection.delete_one({"email": email})
            return True
