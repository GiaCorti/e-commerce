from fastapi import APIRouter
from services.internal_service import InternalService
from fastapi.responses import PlainTextResponse

router = APIRouter()
internal_service = InternalService()


@router.get("/internal/id/{email}", response_class=PlainTextResponse)
async def get_id(email: str):
    return await internal_service.get_id(email)


@router.get("/internal/getBalance/")
async def get_balance(user: str):
    return await internal_service.get_balance(user)


@router.patch("/internal/subtractBalance/")
async def up_balance(user: str, total: float):
    return await internal_service.up_balance(user, total)


@router.get("/internal/isAdmin/{id}")
async def get_role(id: str):
    return await internal_service.get_role(id)


@router.get("/internal/password/{email}", response_class=PlainTextResponse)
async def get_password(email: str):
    return await internal_service.get_password(email)
