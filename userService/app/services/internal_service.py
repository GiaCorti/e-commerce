from database import user_connector
from repositories.internal_repository import InternalRepository
from responses.base_response import NotFoundException
from utils.base_logger import BaseLogger


class InternalService(BaseLogger):

    def __init__(self):
        super().__init__()
        self.repository = InternalRepository(user_connector)

    async def get_password(self, email: str):
        user_password = await self.repository.return_user_password(email)
        return user_password

    async def get_role(self, id: str):
        user_role = await self.repository.return_user_role(id)
        return user_role

    async def get_id(self, email: str):
        user_id = await self.repository.retrieve_id(email)
        if not user_id:
            ex = NotFoundException(description="User not exists")
            raise ex
        return user_id

    async def get_balance(self, id: str):
        user_balance = await self.repository.retrieve_balance(id)
        if not user_balance:
            ex = NotFoundException(description="Balance field required")
            raise ex
        return user_balance

    async def up_balance(self, id: str, total: float):
        user_balance = await self.repository.retrieve_balance(id)
        user_balance -= total
        await self.repository.update_balance(id, user_balance)
        return user_balance
