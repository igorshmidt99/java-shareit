package ru.practicum.shareit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
public class InternalErrorExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherExceptions(RuntimeException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Неизвестная ошибка", e.getMessage());
    }

    @Getter
    @AllArgsConstructor
    protected class ErrorResponse {
        private String name;
        private String description;
    }
}