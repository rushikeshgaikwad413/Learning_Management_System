package com.exam.Lms.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseDeleteResponse {

    private String status;
    private String message;

    public CourseDeleteResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
