package com.exam.Lms.Response.StudentResponse;

import com.exam.Lms.Entity.Course;
import com.exam.Lms.Entity.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class StudentResponse {

    private String status;
    private Student response;
    private String exception;

    public StudentResponse(String status) {
        this.status = status;
    }
}
