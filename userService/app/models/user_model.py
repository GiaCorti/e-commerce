from datetime import date, datetime
from typing import Optional

from bson import ObjectId
from pydantic import BaseModel, Field


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

    email: str = Field( ... )
    firstName: str = Field( ... )
    lastName: str = Field( ... )
    password: str = Field( ... )
    role: str = Field( ... )
    balance: float = Field( ... )

class User_1(BaseModel):
    id: str = Field(..., alias='_id')

def _helper(UserModel) -> dict:
    return {
        "id": str(UserModel["_id"])
    }


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

class UpdateRole (BaseModel):
        role: str


class Getpass( BaseModel ):
    password: str

