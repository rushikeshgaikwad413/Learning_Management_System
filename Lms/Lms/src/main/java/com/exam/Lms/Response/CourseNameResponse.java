package com.exam.Lms.Response;

import com.exam.Lms.Entity.Course;
import lombok.Data;

@Data

public class CourseNameResponse {

    private String status;
    private Course response;
    private String exception;

    public CourseNameResponse(String status) {
        this.status = status;
    }
}
