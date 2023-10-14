package com.exam.Lms.Response;

import com.exam.Lms.Dto.CourseDto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllCoursesResponse {

    private String message;
    private List<CourseDto> list;
    private String exception;




    public GetAllCoursesResponse(List<CourseDto> content, int numberOfElements) {
    }
}
