package com.schoolProject.schoolProject.exception;

import com.schoolProject.schoolProject.exception.model.*;
import com.schoolProject.schoolProject.model.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;


import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice

public class ExceptionHandling implements ErrorController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";
    public static final String ERROR_PATH = "/error";


    @ExceptionHandler(CompanyExistException.class)
    public ResponseEntity<HttpResponse> companyExistException(CompanyExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<HttpResponse> companyNotFoundException(CompanyNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(LocalesExistException.class)
    public ResponseEntity<HttpResponse> localesExistException(LocalesExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(LocalesNotFoundException.class)
    public ResponseEntity<HttpResponse> localesNotFoundException(LocalesNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }


    @ExceptionHandler(DiscriminationsExistException.class)
    public ResponseEntity<HttpResponse> discriminationsExistException(DiscriminationsExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(DiscriminationsNotFoundException.class)
    public ResponseEntity<HttpResponse> discriminationsNotFoundException(DiscriminationsNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(IncomeExistException.class)
    public ResponseEntity<HttpResponse> incomeExistException(IncomeExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(IncomeNotFoundException.class)
    public ResponseEntity<HttpResponse> incomeNotFoundException(IncomeNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }


    @ExceptionHandler(ExitPermitExitException.class)
    public ResponseEntity<HttpResponse> exitPermitExitException(ExitPermitExitException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ExitPermitNotFoundException.class)
    public ResponseEntity<HttpResponse> exitPermitNotFoundException(ExitPermitNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(StockExistException.class)
    public ResponseEntity<HttpResponse> stockExistException(StockExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<HttpResponse> stockNotFoundException(StockNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(DischargeExistException.class)
    public ResponseEntity<HttpResponse> dischargeExistException(DischargeExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(DischargeNotFoundException.class)
    public ResponseEntity<HttpResponse> dischargeNotFoundException(DischargeNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(TransitExistException.class)
    public ResponseEntity<HttpResponse> transitExistException(TransitExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(TransitNotFoundException.class)
    public ResponseEntity<HttpResponse> transitNotFoundException(TransitNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }


    @ExceptionHandler(FuelNotFoundException.class)
    public ResponseEntity<HttpResponse> fuelNotFoundException(FuelNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(FuelExistException.class)
    public ResponseEntity<HttpResponse> fuelExistException(FuelExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(FuelTicketFDeptNotFoundException.class)
    public ResponseEntity<HttpResponse> fuelTicketFDeptNotFoundException(FuelTicketFDeptNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(StockQuantityExceptionNotFound.class)
    public ResponseEntity<HttpResponse> stockQuantityExceptionNotFound(FuelTicketFDeptNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(FuelTicketFDeptExistException.class)
    public ResponseEntity<HttpResponse> fuelTicketFDeptExistException(FuelTicketFDeptExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(FuelTicketFDeptAvailableQuantityException.class)
    public ResponseEntity<HttpResponse> fuelTicketFDeptAvailableQuantityException(FuelTicketFDeptAvailableQuantityException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }



    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404() {
        return createHttpResponse(NOT_FOUND, "There is no mapping for this URL");
    }


    public String getErrorPath() {return ERROR_PATH;}


}
