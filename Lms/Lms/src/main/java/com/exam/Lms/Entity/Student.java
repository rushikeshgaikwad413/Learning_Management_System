package com.exam.Lms.Entity;

import com.exam.Lms.Dto.StudentDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue( strategy =GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String mobileNumber;
    private String age;
    private String courseName;
    private String couponCode;
    private String coursePriceAfterDiscount;

    public Student() {
    }




    public Student(StudentDto studentDto) {
        this.name= studentDto.getName();
        this.email= studentDto.getEmail();
        this.mobileNumber=studentDto.getMobileNumber();
        this.age=studentDto.getAge();
        this.id= studentDto.getId();
        this.coursePriceAfterDiscount= studentDto.getCoursePriceAfterDiscount();
        this.courseName= studentDto.getCourseName();
        this.couponCode=studentDto.getCouponCode();
    }
}
