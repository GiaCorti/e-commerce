from fastapi import APIRouter, Body
from fastapi import Request

from models.user_model import UpdateUserModel, UpdateRole, UserRegisterDto, UpdateFund
from services.user_service import UserService

router = APIRouter()
user_service = UserService()


@router.post("/account/register/")
async def add_user(user: UserRegisterDto = Body(...)):
    return await user_service.register_user(user)


@router.get("/account/")
async def get_users(request: Request):
    token = request.headers.get("Authorization")
    return await user_service.get_users(token)


@router.get("/account/{id_account}")
async def get_user(id_account: str, request: Request):
    token = request.headers.get("Authorization")
    return await user_service.get_user_data(id_account, token)


@router.put("/account/{id_account}")
async def update_user(id_account: str, request: Request, req: UpdateUserModel = Body(...)):
    token = request.headers.get("Authorization")
    return await user_service.update_user_data(id_account, token, req)


@router.delete("/account/{id_account}")
async def delete_user(id_account: str, request: Request):
    token = request.headers.get("Authorization")
    return await user_service.delete_user_data(id_account, token)


@router.patch("/account/")
async def change_role(request: Request, role: str, id_user: str):
    token = request.headers.get("Authorization")
    return await user_service.change_role(token, role, id_user)


@router.patch("/account/{id_account}")
async def add_fund(id_account: str, request: Request, balance: float):
    token = request.headers.get("Authorization")
    return await user_service.add_fund(id_account, token, balance)
