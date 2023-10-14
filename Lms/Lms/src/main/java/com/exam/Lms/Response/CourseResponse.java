package com.exam.Lms.Response;

import com.exam.Lms.Dto.CourseDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseResponse {

    private String status;
    private CourseDto response;
    private String exception;

    public CourseResponse(String status) {
        this.status = status;
    }
}
