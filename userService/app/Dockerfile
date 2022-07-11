FROM python:3.10

COPY . /app

WORKDIR /app

EXPOSE 14002

RUN pip3 install -r requirements.txt

#RUN pip install debugpy

CMD ["python", "./app.py"]

#FROM base as debugger

#ENTRYPOINT ["python","-m", "debugpy", "--listen", "0.0.0.0:5678", "--wait-for-client", "-m"]

#ENTRYPOINT ["python", "-m", "app:main"]
