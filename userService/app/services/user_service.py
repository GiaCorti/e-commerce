from bson import ObjectId

from database import user_connector, user_collection
from responses.base_response import SuccessResponse, ConflictException, NotFoundException, BadRequestException
from utils.base_logger import BaseLogger
from repositories.user_repository import UserRepository
from models.user_model import UserModel, UpdateUserModel, UpdateRole


class UserService(BaseLogger):

    def __init__(self):
        super().__init__()
        self.repository = UserRepository(user_connector)

    async def register_user(self, user: UserModel):
        find_user = await self.repository.retrieve_user_by_email(user.email)
        if find_user:
            ex = ConflictException(description=f'User identified by email {user.email} already exists')
            raise ex
        self.logger.info(f"Starting to add a new user {user.email} inside database")
        new_user = await self.repository.add_user(user)
        self.logger.info(f"Successfully added user {new_user} inside database")
        return SuccessResponse(user)

    async def get_users(self):
        users = await self.repository.retrieve_users()
        if users:
            return SuccessResponse(users)

    async def get_user_data(self, email: str):
        user = await self.repository.retrieve_user_by_email(email)
        if not user:
            ex = NotFoundException(description=f'User identified by email {email} not exists')
            raise ex
        return SuccessResponse(user)

    async def get_password(self, email: str):
        user = await self.repository.return_user_password(email)
        if not user:
            ex = NotFoundException(description=f'User identified by email {email} not exists')
            raise ex
        return SuccessResponse(user)

    async def get_id(self, id: str):
        user = await self.repository.retrieve_id(id)
        if not user:
            ex = NotFoundException(description=f'User identified by email not exists')
            raise ex
        return SuccessResponse(user)


    async def update_user_data(self, email: str, req: UpdateUserModel):
        req = {k: v for k, v in req.dict().items() if v is not None}
        updated_user = await self.repository.update_user(email, req)
        if updated_user:
            return SuccessResponse(
                "User with email: {} updated successfully".format(email)
            )
        ex = BadRequestException(description=f'User identified by {email} was not found ')
        raise ex

    async def delete_user_data(self, email: str):
        deleted_user = await self.repository.delete_user(email)
        if not deleted_user:
            ex = NotFoundException(description=f'User identified by email {email} not exists')
            raise ex
        return SuccessResponse(
            "User with email: {} removed".format(email)
        )

    async def change_role(self, email: str, req:UpdateRole):
        req = {k: v for k, v in req.dict().items() if v is not None}
        updated_user = await self.repository.update_user(email, req)
        if updated_user:
            return SuccessResponse(
                "User role with email: {} updated successfully".format(email)
            )
        ex = BadRequestException(description=f'User identified by {email} was not found ')
        raise ex
