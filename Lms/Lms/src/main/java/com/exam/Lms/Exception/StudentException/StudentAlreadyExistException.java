package com.exam.Lms.Exception.StudentException;

public class StudentAlreadyExistException extends RuntimeException {
    public StudentAlreadyExistException(String s) {
        super(s);
    }
}
