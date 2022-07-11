from urllib.request import Request

import uvicorn
from fastapi import FastAPI
from uvicorn.middleware.debug import PlainTextResponse
from fastapi.middleware.cors import CORSMiddleware

from controllers.user_controller import router as user_router
from controllers.internal_controller import router as internal_router
from responses.base_response import NotFoundException, ConflictException, BadRequestException, NotAllowedException


def main():
    app = FastAPI()
    origins = ["*"]

    app.add_middleware(
        CORSMiddleware,
        allow_origins=origins,
        allow_credentials=True,
        allow_methods=["*"],
        allow_headers=["*"],
    )


    @app.exception_handler(NotFoundException)
    async def unicorn_notfound_exception_handler(request: Request, exc: NotFoundException):
        return PlainTextResponse(str(exc.description), status_code=exc.code)

    @app.exception_handler(ConflictException)
    async def unicorn_exception_handler(request: Request, exc: ConflictException):
        return PlainTextResponse(str(exc.description), status_code=exc.code)

    @app.exception_handler(BadRequestException)
    async def unicorn_exception_handler(request: Request, exc: BadRequestException):
        return PlainTextResponse(str(exc.description), status_code=exc.code)

    @app.exception_handler(NotAllowedException)
    async def unicorn_exception_handler(request: Request, exc: NotAllowedException):
        return PlainTextResponse(str(exc.description), status_code=exc.code)

    app.include_router(user_router, tags=["Account"], prefix="")
    app.include_router(internal_router, tags=["Internal"], prefix="")
    uvicorn.run(app, host="0.0.0.0", port=14002)


if __name__ == "__main__":
    main()
