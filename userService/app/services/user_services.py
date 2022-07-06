from database import user_connector
from responses.base_response import SuccessResponse, ConflictException, NotFoundException, BadRequestException
from utils.base_logger import BaseLogger
from repositories.user_repository import UserRepository
from models.user_model import UserModel, UpdateUserModel


class UserService(BaseLogger):

    def __init__(self):
        super().__init__()
        self.repository = UserRepository(user_connector)

    async def add_user_data(self, user: UserModel):
        find_user = await self.repository.retrieve_user_by_fiscal_code(user.fiscal_code)
        if find_user:
            ex = ConflictException(description=f'User identified by fiscal code {user.fiscal_code} already exists')
            raise ex
        self.logger.info(f"Starting to add a new user {user.fiscal_code} inside database")
        new_user = await self.repository.add_user(user)
        self.logger.info(f"Successfully added user {new_user} inside database")
        return SuccessResponse(user)

    async def get_users(self):
        users = await self.repository.retrieve_users()
        if users:
            return SuccessResponse(users)

    async def get_user_data(self, fiscal_code: str):
        user = await self.repository.retrieve_user_by_fiscal_code(fiscal_code)
        if not user:
            ex = NotFoundException(description=f'User identified by fiscal code {fiscal_code} not exists')
            raise ex
        return SuccessResponse(user)

    async def update_user_data(self, fiscal_code: str, req: UpdateUserModel):
        req = {k: v for k, v in req.dict().items() if v is not None}
        updated_user = await self.repository.update_user(fiscal_code, req)
        if updated_user:
            return SuccessResponse(
                "User with fiscal code: {} updated successfully".format(fiscal_code)
            )
        ex = BadRequestException(description=f'User identified by {fiscal_code} was not found ')
        raise ex

    async def delete_user_data(self, fiscal_code: str):
        deleted_user = await self.repository.delete_user(fiscal_code)
        if not deleted_user:
            ex = NotFoundException(description=f'User identified by fiscal code {fiscal_code} not exists')
            raise ex
        return SuccessResponse(
            "User with fiscal code: {} removed".format(fiscal_code)
        )