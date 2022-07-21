from typing import Optional
from pydantic import BaseModel, Field
from bson.objectid import ObjectId as BsonObjectId


class PydanticObjectId(BsonObjectId):
    @classmethod
    def __get_validators__(cls):
        yield cls.validate

    @classmethod
    def validate(cls, v):
        if not isinstance(v, BsonObjectId):
            raise TypeError('ObjectId required')
        return str(v)


class UserModel(BaseModel):
    """
    Complete Model
    Structure Example
    schema_extra = {
        "example": {
            "email": "johndoe@example.com",
            "firstName": "John",
            "lastName": "Doe",
            "password":"",
            "birthday": "12/03/1994",
            "role": admin,
            "balance": 0
        }
    }
    """
    email: str = Field(...)
    firstName: str = Field(...)
    lastName: str = Field(...)
    password: str = Field(...)
    birthday: str = Field(...)
    role: str = Field(...)
    balance: float = Field(...)


class UserRegisterDto(BaseModel):

    email: str = Field(...)
    firstName: str = Field(...)
    lastName: str = Field(...)
    password: str = Field(...)
    birthday: str = Field(...)


class UserListDto(BaseModel):

    id: PydanticObjectId = Field(default_factory=PydanticObjectId, alias="_id")
    email: str = Field(...)
    firstName: str = Field(...)
    lastName: str = Field(...)
    role: str = Field(...)
    balance: float = Field(...)


class User(BaseModel):
    id: str = Field(..., alias='_id')


class UpdateUserModel(BaseModel):
    """
    Partial Model
    Structure Example
    schema_extra = {
        "example": {
            "email": "johndoe@example.com",
            "firstName": "John",
            "lastName": "Doe",
            "password": "",
            "birthday": "10/10/2022",
            "role": "admin",
            "balance": 0
        }
    }
    """
    email: Optional[str]
    firstName: Optional[str]
    lastName: Optional[str]
    password: Optional[str]
    birthday: Optional[str]
    role: Optional[str]
    balance: Optional[float]


class UpdateRole(BaseModel):
    role: str = Field(...)


class UpdateFund(BaseModel):
    balance: float = Field(...)


class Getpass(BaseModel):
    password: str = Field(...)
