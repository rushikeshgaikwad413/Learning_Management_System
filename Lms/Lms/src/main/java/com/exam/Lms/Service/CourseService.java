package com.exam.Lms.Service;

import com.exam.Lms.Dto.CourseDto;
import com.exam.Lms.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    void addCourse(CourseDto courseDto);

    Course findById(int id);

    CourseDto updateCourse(CourseDto courseDto);

    String deleteCourseById(int id);

    String deleteAllCourse();

//    List<CourseDto> findAllCourses(int pageNo);

    Page<CourseDto> findAllCourses(Pageable pageable);

    Course findCourseByName(String name);

    List<Course> findAllCoursesByName(String name);

    String deleteCourseByName(String name);
}

