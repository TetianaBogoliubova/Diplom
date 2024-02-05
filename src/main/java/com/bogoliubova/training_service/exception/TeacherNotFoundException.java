package com.bogoliubova.training_service.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String massage) {
        super(massage);
    }
}


//    @ExceptionHandler(AuthorNotFoundException.class)
//    public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException ex) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .headers(headers)
//                .body("!!!!" + ex.getMessage());
//    }