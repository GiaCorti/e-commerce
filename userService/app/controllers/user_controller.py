from fastapi import APIRouter, Body

from models.user_model import UpdateUserModel, UpdateRole, UserRegisterDto
from services.user_service import UserService

router = APIRouter()
user_service = UserService()


@router.post("/register")
async def add_user(user: UserRegisterDto = Body(...)):
    return await user_service.register_user(user)


@router.get("/id/{email}")
async def get_id(email: str):
    return await user_service.get_id(email)


@router.get("/password/{email}")
async def get_password(email: str):
    return await user_service.get_password(email)


@router.get("/")
async def get_users():
    return await user_service.get_users()


@router.get("/{email}")
async def get_user(email: str):
    return await user_service.get_user_data(email)


@router.put("/{email}")
async def update_user(email: str, req: UpdateUserModel = Body(...)):
    return await user_service.update_user_data(email, req)


@router.delete("/{email}")
async def delete_user(email: str):
    return await user_service.delete_user_data(email)


@router.patch("/role/{email}")
async def change_role(email: str, req: UpdateRole = Body(...)):
    return await user_service.change_role(email, req)


# @router.get("/health_check", status_code=status.HTTP_200_OK)
# async def get_users():
#     pass
