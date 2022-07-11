from database import user_connector
from responses.base_response import ConflictException, NotAllowedException
from utils.base_logger import BaseLogger
from repositories.user_repository import UserRepository
from models.user_model import UpdateUserModel, UpdateRole, UpdateFund, UserRegisterDto
import requests


class UserService(BaseLogger):

    def __init__(self):
        super().__init__()
        self.repository = UserRepository(user_connector)

    async def register_user(self, user: UserRegisterDto):
        find_user = await self.repository.retrieve_user_by_email(user.email)
        if find_user:
            ex = ConflictException(description=f'User identified by email {user.email} already exists')
            raise ex
        self.logger.info(f"Starting to add a new user {user.email} inside database")
        req = {k: v for k, v in user.dict().items() if v is not None}
        new_user = await self.repository.add_user(req)
        self.logger.info(f"Successfully added user {new_user} inside database")
        return user

    async def get_users(self, header: str):
        lens = len(header)
        token = header[7:lens + 1]
        url = 'http://host.docker.internal:14000/internal/getUser?token='
        id_exec = requests.get(url + token)
        users = await self.repository.retrieve_users(id_exec)
        if users:
            return users

    async def get_user_data(self, id_user: str, header: str):
        lens = len(header)
        token = header[7:lens + 1]
        url = 'http://host.docker.internal:14000/internal/getUser?token='
        id_exec = requests.get(url + token)
        self.logger.info(f"id_exec: {id_exec}, id_user: {id_user}, is_admin: {self.repository.is_admin(id_exec)}")
        if id_exec == id_user or self.repository.is_admin(id_exec):
            user = await self.repository.retrieve_user(id_user)
            return user
        else:
            ex = NotAllowedException(description="Permission required")
            raise ex


    async def update_user_data(self, id_user: str, header: str, req: UpdateUserModel):
        lens = len(header)
        token = header[7:lens+1]
        url = 'http://host.docker.internal:14000/internal/getUser?token='
        id_exec = requests.get(url + token)
        req = {k: v for k, v in req.dict().items() if v is not None}
        if id_exec == id_user or self.repository.is_admin(id_exec):
            await self.repository.update_user(id_user, req)
            return "User: {} data updated".format(id_user)
        else:
            ex = NotAllowedException(description="Permission required")
            raise ex


    async def delete_user_data(self, id_user: str, header: str):
        lens = len(header)
        token = header[7:lens+1]
        url = 'http://host.docker.internal:14000/internal/getUser?token='
        id_exec = requests.get(url + token)
        if id_exec == id_user or self.repository.is_admin(id_exec):
            await self.repository.delete_user(id_user)
            return "User: {} removed".format(id_user)
        else:
            ex = NotAllowedException(description="Permission required")
            raise ex

    async def change_role(self, header: str, role: str, id_user: str):
        lens = len(header)
        token = header[7:lens + 1]
        url = 'http://host.docker.internal:14000/internal/getUser?token='
        id_exec = requests.get(url + token)
        await self.repository.update_user_by_admin(id_exec, id_user, role)
        return "User: {} role updated successfully".format(id_user)

    async def add_fund(self, id_user, header: str, balance: float):
        lens = len(header)
        token = header[7:lens + 1]
        url = 'http://host.docker.internal:14000/internal/getUser?token='
        id_exec = requests.get(url + token)
        if id_exec == id_user or self.repository.is_admin(id_exec):
            await self.repository.update_fund(id_user, balance)
            return "Fund added"
        else:
            ex = NotAllowedException( description="Permission required" )
            raise ex
