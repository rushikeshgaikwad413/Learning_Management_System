package com.exam.Lms.Response;

import com.exam.Lms.Dto.CourseDto;
import com.exam.Lms.Entity.Course;
import lombok.Data;

@Data
public class CourseUpdateResponse {

    private String message;
    private String status;
    private String exception;
    private CourseDto response;

    public CourseUpdateResponse(String message) {
        this.message = message;
    }
}
