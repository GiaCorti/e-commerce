import logging


class BaseLogger:
    def __init__(self) -> None:
        self._logger = logging.getLogger(__name__)
        self._logger.setLevel(level=logging.INFO)

    @property
    def logger(self):
        return self._logger
