package com.bogoliubova.training_service.controller.exception_handler;

import com.bogoliubova.training_service.dto.ErrorResponse;
import com.bogoliubova.training_service.exception.TeacherInThisCityNotFound;
import com.bogoliubova.training_service.exception.TeacherNotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAspect {

    @ExceptionHandler(TeacherNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Not found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
    })
    public ResponseEntity<ErrorResponse> handleTeacherNotFoundException(TeacherNotFoundException ex, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("!!!!! " + ex.getMessage());
        errorResponse.setUrl(String.valueOf(request.getRequestURL()));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(errorResponse);
    }

    @ExceptionHandler(TeacherInThisCityNotFound.class)
    @ApiResponse(responseCode = "404", description = "Not found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
    })
    public ResponseEntity<String> handleTeacherInThisCityNotFound(TeacherInThisCityNotFound ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body("!!!!! " + ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ApiResponse(responseCode = "404", description = "Not found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
    })
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Validation error");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body("!!!!! " + errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ApiResponse(responseCode = "404", description = "Not found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
    })
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("Validation error");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .headers(headers)
                .body("!!!!! " + errorMessage);
    }
}

//
//import org.springframework.web.context.request.WebRequest;
//
//// ...
//
//@ControllerAdvice
//public class ExceptionHandlerAspect {
//
//    @ExceptionHandler(TeacherNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleTeacherNotFoundException(
//            TeacherNotFoundException ex,
//            WebRequest request) {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setMessage("!!!!! " + ex.getMessage());
//        errorResponse.setUrl(request.getDescription(false)); // Используйте getDescription для получения URL
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .headers(headers)
//                .body(errorResponse);
//    }
//}

//    @ExceptionHandler(TeacherNotFoundException.class)
//    public ResponseEntity<String> handleTeacherNotFoundException(TeacherNotFoundException ex) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .headers(headers)
//                .body("!!!!! " + ex.getMessage());
//    }