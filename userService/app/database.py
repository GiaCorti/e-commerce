import pymongo

#client = pymongo.MongoClient("mongodb:27017") #Docker
client = pymongo.MongoClient("host.docker.internal:15000")
# client = pymongo.MongoClient("localhost:27017")

user_collection = client.users.get_collection("users_collection")


def user_connector() -> any:
    return client.users.get_collection("users_collection")

