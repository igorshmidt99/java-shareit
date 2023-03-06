package ru.practicum.shareit.item.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.InternalErrorExceptionHandler;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice("ru.practicum.shareit.item")
public class ItemExceptionHandler extends InternalErrorExceptionHandler {

    @ExceptionHandler({ItemNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(RuntimeException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Не найден item", e.getMessage());
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(RuntimeException e) {
        log.error(e.getMessage());
        return new ErrorResponse("Запрос не прошел валидацию", e.getMessage());
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handleInternalError(RuntimeException e) {
//        return super.handleOtherExceptions(e);
//    }
}