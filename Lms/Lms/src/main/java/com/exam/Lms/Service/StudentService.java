package com.exam.Lms.Service;

import com.exam.Lms.Dto.StudentDto;
import com.exam.Lms.Entity.Student;

public interface StudentService {
    public String addStudent(StudentDto studentDto);

    Student findById(int id);

    String addStudentWithDiscount(StudentDto studentDto, String couponCode);
}
