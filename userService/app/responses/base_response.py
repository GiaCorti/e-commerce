# class BaseResponse:
#     def __init__(self, code):
#         self.statusCode = code

from typing import Optional


class SuccessResponse:
    def __init__(self, data):
        #        super().__init__(code=code)
        self.data = data


class CustomException(Exception):
    def __init__(self, code: int, message: str, description: Optional[str] = None) -> None:
        super().__init__(message)
        self.code = code
        self.message = message
        self.description = description


class BadRequestException(CustomException):
    def __init__(self, description: Optional[str] = None) -> None:
        super().__init__(code=400, message='Bad Request')
        self.description = description


class ConflictException(CustomException):
    def __init__(self, description: Optional[str] = None) -> None:
        super().__init__(code=409, message='Conflict')
        self.description = description


class NotFoundException(CustomException):
    def __init__(self, description: Optional[str] = None) -> None:
        super().__init__(code=404, message='Not Found')
        self.description = description
