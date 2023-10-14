package com.exam.Lms.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseNotFoundException1 extends RuntimeException {
    public CourseNotFoundException1(String s) {
        super(s);
    }
}
