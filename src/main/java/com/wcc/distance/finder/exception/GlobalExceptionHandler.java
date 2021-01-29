package com.wcc.distance.finder.exception;

import com.wcc.distance.finder.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * Global Exception handler for distance finder service application
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingParams(MissingServletRequestParameterException ex) {
        ErrorResponse errorMessage = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getParameterName().concat(" parameter is missing"),
                "Request Param Postcode is mandatory");
        return errorMessage;
    }

    @ExceptionHandler({DistanceFinderCustomException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse distanceFinderCustomException(DistanceFinderCustomException distanceFinderCustomException) {
        ErrorResponse errorMessage = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                distanceFinderCustomException.getMessage(),
                "Internal Server Error occurred in Distance Finder Service");
        return errorMessage;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse genericException(Exception exception) {
        ErrorResponse errorMessage = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                exception.getMessage(),
                "Exception occurred in Distance Finder Service");
        return errorMessage;
    }
}
