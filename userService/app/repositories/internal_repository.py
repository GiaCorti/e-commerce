from bson import ObjectId

from database import user_collection
from models.user_model import Getpass, UpdateRole


class InternalRepository:
    def __init__(self, connector: any):
        self.connector = connector

    async def return_user_password(self, email: str) -> Getpass:
        """
        Retrieve a user with a matching password
        :param email: the user password
        :return: Getpass | None -> Getpass
        """

        user = user_collection.find_one({"email": email})
        var = str(user['password'])
        if user:
            return var

    async def return_user_role(self, id: str):

        user = user_collection.find_one({"_id": ObjectId(id)})
        var = str(user['role'])
        if var == "ADMIN":
            return True
        else:
            return False

    async def retrieve_id(self, email: str):
        user = user_collection.find_one({"email": email})
        var = str(user['_id'])
        if user:
            return var

    async def retrieve_balance(self, id: str):
        user = user_collection.find_one({"_id": ObjectId(id)})
        var = float(user['balance'])
        if user:
            return var

    async def update_balance(self, id: str, data: float):

        """
        Update a user with a matching ID
        :param data:
        :param id: the user id
        :return: None
        """

        user = user_collection.find_one({"_id": ObjectId(id)})
        if user:
            myquery = {"_id": ObjectId(id)}
            new_values = {"$set": {"balance": data}}
            user_collection.update_one(myquery, new_values)
