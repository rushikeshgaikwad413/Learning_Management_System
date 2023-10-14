package com.exam.Lms.Dto;

import com.exam.Lms.Entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private int id;

    private String name;
    private String description;
    private String coursePrice;
    private String numberOfLession;
    private int studentIntake;
    private String faculty;


    public CourseDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.numberOfLession = course.getNumberOfLession();
        this.studentIntake = course.getStudentIntake();
        this.faculty = course.getFaculty();
        this.coursePrice= course.getCoursePrice();
    }

}
