package com.exam.Lms.Exception;

public class CourseNameNotFoundException extends RuntimeException {
    public CourseNameNotFoundException(String s) {
        super(s);
    }
}
