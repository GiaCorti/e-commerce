from typing import Optional
from pydantic import BaseModel, Field


class UserModel(BaseModel):
    """
    Complete Model
    Structure Example
    schema_extra = {
        "example": {
            "fiscal_code": "VHHBPF48A31B393K",
            "name": "John",
            "surname": "Doe",
            "birthday": "12/03/1994",
            "wallet": 23,
        }
    }
    """
    fiscal_code: str = Field(...)
    name: str = Field(...)
    surname: str = Field(...)
    birthday: str = Field(...)
    wallet: str = Field(...)


class UpdateUserModel(BaseModel):
    """
    Partial Model
    Structure Example
    schema_extra = {
        "example": {
            "fiscal_code": "VHHBPF48A31B393K",
            "name": "John",
            "surname": "Doe",
            "birthday": "12/03/1994",
            "wallet": 10,
        }
    }
    """
    fiscal_code: Optional[str]
    name: Optional[str]
    surname: Optional[str]
    birthday: Optional[str]
    wallet: Optional[str]