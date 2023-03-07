package ru.practicum.shareit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.item.exception.ItemNotFoundException;
import ru.practicum.shareit.user.exception.UserAlreadyExistException;
import ru.practicum.shareit.user.exception.UserNotFoundException;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice("ru.practicum.shareit")
public class MainExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleOtherExceptions(RuntimeException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Неизвестная ошибка", e.getMessage());
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(RuntimeException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Запрос не прошел валидацию", e.getMessage());
    }

    @ExceptionHandler({UserNotFoundException.class, ItemNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(RuntimeException e) {
        String message = e.getMessage();
        log.error(message);
        return new ErrorResponse("Ресурс не найден.", message);
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(RuntimeException e) {
        String message = e.getMessage();
        log.error(message);
        return new ErrorResponse("Ресурс с таким состоянием уже существует.", message);
    }

    @Getter
    @AllArgsConstructor
    private class ErrorResponse {
        private String name;
        private String description;
    }
}