package com.exam.Lms.ServiceImpl;

import com.exam.Lms.Dto.StudentDto;
import com.exam.Lms.Entity.Coupon;
import com.exam.Lms.Entity.Course;
import com.exam.Lms.Entity.Student;
import com.exam.Lms.Exception.CouponNotFoundException;
import com.exam.Lms.Exception.CourseNotFoundException;
import com.exam.Lms.Exception.StudentException.StudentAlreadyExistException;
import com.exam.Lms.Exception.StudentException.StudentNotFoundException;
import com.exam.Lms.Repository.CourseRepository;
import com.exam.Lms.Repository.StudentRepository;
import com.exam.Lms.Service.CouponService;
import com.exam.Lms.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CouponService couponService;



    @Override
    public String addStudent(StudentDto studentDto) {
        Student byEmail = studentRepository.findByEmail(studentDto.getEmail());
        if (byEmail == null){
            Student student = new Student(studentDto);
            studentRepository.save(student);
            return "Student Added Susseccfully";
        }else {
            throw new StudentAlreadyExistException("Student Already Exist");
        }
    }

    @Override
    public Student findById(int id) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isEmpty()){
            throw new StudentNotFoundException("Student Not Found");
        }return byId.get();
    }

    @Override
    public String addStudentWithDiscount(StudentDto studentDto, String couponCode) {
        Student byEmail = studentRepository.findByEmail(studentDto.getEmail());
        if (byEmail != null) {
            throw new StudentAlreadyExistException("Student Already Exists");
        }

        // Retrieve the course by name
        Optional<Course> courseOptional = courseRepository.findCourseByName(studentDto.getCourseName());

        if (courseOptional.isEmpty()) {
            throw new CourseNotFoundException("Course Not Found");
        }

        Course course = courseOptional.get(); // Get the Course entity from the Optional

        // Check if the coupon code is valid
        Coupon coupon = couponService.findByCode(couponCode);

        if (coupon == null) {
            throw new CouponNotFoundException("Coupon Not Found");
        }

        // Apply the discount to the course price
        double discountPercentage = coupon.getDiscountPercentage();
        double coursePrice = Double.parseDouble(course.getCoursePrice());
        double discountAmount = (discountPercentage / 100.0) * coursePrice;
        double discountedPrice = coursePrice - discountAmount;

        // Create a new student with the course price after discount
        Student student = new Student(studentDto);
        student.setCoursePriceAfterDiscount(String.valueOf(discountedPrice));

        // Save the student
        studentRepository.save(student);

        return "Student Added Successfully with Discount";
    }


}
