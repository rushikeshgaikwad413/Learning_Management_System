package com.exam.Lms.Exception;

import org.springframework.http.HttpStatus;

public class CourseUpdateNotFoundException extends RuntimeException {

    private HttpStatus httpStatus;

    public CourseUpdateNotFoundException(String s, Object p1) {
    }
}
