from typing import Optional


# class SuccessResponse:
#     def __init__(self, data):
#         self.data = data


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


class NotAllowedException(CustomException):
    def __init__(self, description: Optional[str] = None) -> None:
        super().__init__(code=405, message='Not Allowed')
        self.description = description
