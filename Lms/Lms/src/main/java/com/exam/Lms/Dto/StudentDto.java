package com.exam.Lms.Dto;

import com.exam.Lms.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private int id;

    private String name;
    private String email;
    private String mobileNumber;
    private String age;
    private String courseName;
    private String couponCode;
    private String coursePriceAfterDiscount;

    public StudentDto(Student student) {
        this.name= student.getName();
        this.email= student.getEmail();
        this.mobileNumber= student.getMobileNumber();
        this.age= student.getAge();
        this.coursePriceAfterDiscount= student.getCoursePriceAfterDiscount();
        this.courseName= student.getCourseName();
        this.couponCode=student.getCouponCode();
    }
}
