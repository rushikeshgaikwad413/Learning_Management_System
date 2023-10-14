package com.exam.Lms.Exception;

public class pageNotFoundException extends RuntimeException {
    public pageNotFoundException(String pageNotFound) {

        super(pageNotFound);
    }
}
