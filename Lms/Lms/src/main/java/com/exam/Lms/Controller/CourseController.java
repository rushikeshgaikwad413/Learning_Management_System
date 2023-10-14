package com.exam.Lms.Controller;

import com.exam.Lms.Dto.CourseDto;
import com.exam.Lms.Entity.Course;
import com.exam.Lms.Exception.CourseNotFoundException;
import com.exam.Lms.Exception.CourseNotFoundException1;
import com.exam.Lms.Exception.CourseUpdateNotFoundException;
import com.exam.Lms.Exception.SomethingWentWrongException;
import com.exam.Lms.Response.CourseDeleteResponse;
import com.exam.Lms.Response.CourseNameResponse;
import com.exam.Lms.Response.CourseUpdateResponse;
import com.exam.Lms.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/AddCourse")
    public ResponseEntity<?> addCourse(@RequestBody CourseDto courseDto){
        try{
            courseService.addCourse(courseDto);
            return ResponseEntity.status(HttpStatus.OK).body("Course Added Successfully");
        } catch (SomethingWentWrongException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsucessfull");
        }
    }

    @GetMapping("/getCourse")
    public ResponseEntity<?> getCourse(@RequestParam int id){
        try {
            CourseNameResponse courseNameResponse = new CourseNameResponse("success");
            courseNameResponse.setResponse(courseService.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(courseNameResponse);
        } catch (CourseNotFoundException e) {
            CourseNameResponse courseNameResponse = new CourseNameResponse("Unsuccessfull");
            courseNameResponse.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courseNameResponse);

        }
    }

    @GetMapping("/FindCourseByName")
    public ResponseEntity<?> findCourseByName(@RequestParam String name){
       
        try {
            Course courseByName = courseService.findCourseByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(courseByName);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("FindAllCourseByName")
    public ResponseEntity<?> findAllCourseByName(@RequestParam String name){
        try {
            List<Course> allCoursesByName = courseService.findAllCoursesByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(allCoursesByName);
        } catch (CourseNotFoundException1 e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//    @GetMapping("/GetAllCourse")
//    public ResponseEntity<GetAllCoursesResponse> getAllCourses(@RequestParam int pageNo){
//        courseService.findAllCourses(pageNo);
//    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<Page<CourseDto>> getAllCourses(@RequestParam(defaultValue = "0") int pageNo) {
        try {
            Pageable pageable = PageRequest.of(pageNo, 10);
            Page<CourseDto> courses = courseService.findAllCourses(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        } catch (SomethingWentWrongException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/UpdateCourse")
    public ResponseEntity<?> updateCourse(@RequestBody CourseDto courseDto){
        courseService.updateCourse(courseDto);
        try {
            CourseDto courseDto1 = courseService.updateCourse(courseDto);
            CourseUpdateResponse courseUpdateResponse = new CourseUpdateResponse("sucessfull");
            courseUpdateResponse.setResponse(courseDto1);
            courseUpdateResponse.setStatus("Successfully Course Updated");
            return ResponseEntity.status(HttpStatus.OK).body(courseUpdateResponse);
        } catch (CourseUpdateNotFoundException e) {
            CourseUpdateResponse courseUpdateResponse = new CourseUpdateResponse("Unsuccessfull");
            courseUpdateResponse.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(courseUpdateResponse);
        }
    }

    @DeleteMapping("/DeleteCourse")
    public ResponseEntity<?> deleteCourse(@RequestParam int id){
        try {
            String string = courseService.deleteCourseById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CourseDeleteResponse("Successfull",string));
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CourseDeleteResponse("Unsuccessfull","Course Not Found"));
        }
    }

    @DeleteMapping("/DeleteCourseByName")
    public ResponseEntity<?> deleteCourseByName(@RequestParam String name) {
        try {
            String string = courseService.deleteCourseByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(string);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/DeleteAllCourse")
    public String deleteAllCourse(){
        courseService.deleteAllCourse();

        return "All Course Deleted";
    }

}
