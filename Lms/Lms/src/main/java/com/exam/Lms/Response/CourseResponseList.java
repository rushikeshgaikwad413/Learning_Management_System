package com.exam.Lms.Response;



import com.exam.Lms.Dto.CourseDto;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseResponseList {

    private List<CourseDto> content;
    private int numberOfElements;

    public CourseResponseList(List<CourseDto> content, int numberOfElements) {
        this.content = content;
        this.numberOfElements = numberOfElements;
    }
}
