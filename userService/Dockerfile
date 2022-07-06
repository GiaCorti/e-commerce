FROM python:3.10

COPY . /app

WORKDIR /app

EXPOSE 8000

RUN pip install -r requirements.txt

#RUN pip install debugpy

CMD ["uvicorn","app:main", "--host=127.0.0.1"]

#FROM base as debugger

#ENTRYPOINT ["python","-m", "debugpy", "--listen", "0.0.0.0:5678", "--wait-for-client", "-m"]


#ENTRYPOINT ["python", "-m", "app:main"]
