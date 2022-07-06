from urllib.request import Request
import uvicorn
from fastapi import FastAPI
from uvicorn.middleware.debug import PlainTextResponse
from fastapi.middleware.cors import CORSMiddleware

from controllers.user_controller import router as user_router
from responses.base_response import NotFoundException, ConflictException, BadRequestException


async def main():
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
    async def unicorn_exception_handler(request: Request, exc: NotFoundException):
        return PlainTextResponse(str(exc.description), status_code=exc.code)

    @app.exception_handler(BadRequestException)
    async def unicorn_exception_handler(request: Request, exc: NotFoundException):
        return PlainTextResponse(str(exc.description), status_code=exc.code)

    app.include_router(user_router, tags=["User"], prefix="/user")
    uvicorn.run(app, host="127.0.0.1", port=8001)


if __name__ == "__main__":
    main()