package com.exam.Lms.ServiceImpl;

import com.exam.Lms.Dto.CourseDto;
import com.exam.Lms.Entity.Course;
import com.exam.Lms.Exception.CourseNotFoundException;
import com.exam.Lms.Exception.CourseNotFoundException1;
import com.exam.Lms.Exception.CourseUpdateNotFoundException;
import com.exam.Lms.Exception.SomethingWentWrongException;
import com.exam.Lms.Repository.CourseRepository;
import com.exam.Lms.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;


    @Override
    public void addCourse(CourseDto courseDto) {
        if (courseDto.getName() != null){
            Course course = new Course(courseDto);
            courseRepository.save(course);
        }else {
            throw new SomethingWentWrongException("Something Went Wrong");
        }
    }

    @Override
    public Course findById(int id) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isEmpty()){
            throw new CourseNotFoundException("Course Not Found");
        }
        return byId.get();
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto) {
        Course byId = courseRepository.findById(courseDto.getId())
                .orElseThrow(()-> new CourseUpdateNotFoundException("Course Not Found", HttpStatus.NOT_FOUND));

        if (courseDto.getName() != null){
            byId.setName(courseDto.getName());
        }
        if (courseDto.getDescription() != null){
            byId.setDescription(courseDto.getDescription());
        }
        if (courseDto.getNumberOfLession() != null){
            byId.setNumberOfLession(courseDto.getNumberOfLession());
        }

        courseRepository.save(byId);
        return courseDto;
    }

    @Override
    public String deleteCourseById(int id) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId == null){
            throw new CourseNotFoundException("Course Not Found");
        }
        courseRepository.deleteById(id);
        return "course Deleted Successfully" + id;
    }

    @Override
    public String deleteAllCourse() {
        courseRepository.deleteAll();
        return "All Data Deleted";
    }

    @Override
    public Page<CourseDto> findAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return courses.map(CourseDto::new);
    }

    @Override
    public Course findCourseByName(String name) {
        Optional<Course> byName = courseRepository.findByName(name);
        if (byName.isEmpty()) {
            throw new CourseNotFoundException("Course"+ name + "not found");
        }
        return byName.get();
    }

    @Override
    public List<Course> findAllCoursesByName(String name) {
        List<Course> allByName = courseRepository.findAllByName(name);
        if (allByName.isEmpty()){
            throw new CourseNotFoundException1("Course"+":" + name + " "+ "Not Found");
        }
        return allByName;
    }

    @Override
    public String deleteCourseByName(String name) {
        List<Course> allByName = courseRepository.findAllByName(name);
        if (allByName.isEmpty()) {
            throw new CourseNotFoundException("Course '" + name + "' not found");
        }
        courseRepository.deleteAll(allByName);
        return "Course '" + name + "' deleted successfully";
    }


//    @Override
//    public List<CourseDto> findAllCourses(int pageNo) {
//        List<Course> all = courseRepository.findAll();
//
//        if ((pageNo*10)> all.size()-1){
//            throw new pageNotFoundException("Page Not Found");
//        }
//
//        if (all.size()<=0){
//            throw new CourseNotFoundException("Course Not Found");
//        }
//
//        List<CourseDto> objects = new ArrayList<>();
//
//        int pageStart=pageNo*10;
//        int pageEnd=pageStart+10;
//        int diff=(all.size()) - pageStart;
//        List<>
//    }


}
