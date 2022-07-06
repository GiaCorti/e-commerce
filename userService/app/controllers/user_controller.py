from fastapi import APIRouter, Body

from models.user_model import UserModel, UpdateUserModel
from services.user_services import UserService

router = APIRouter()
user_service = UserService()


@router.post("/")
async def add_user(user: UserModel = Body(...)):
    return await user_service.add_user_data(user)


@router.get("/")
async def get_users():
    return await user_service.get_users()


@router.get("/{fiscal_code}")
async def get_id(fiscal_code: str):
    return await user_service.get_user_data(fiscal_code)


@router.put("/{fiscal_code}")
async def update_user_data(fiscal_code: str, req: UpdateUserModel = Body(...)):
    return await user_service.update_user_data(fiscal_code, req)


@router.delete("/{fiscal_code}")
async def delete_user_data(fiscal_code: str):
    return await user_service.delete_user_data(fiscal_code)