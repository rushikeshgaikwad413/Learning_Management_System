package com.exam.Lms.Controller;

import com.exam.Lms.Dto.StudentDto;
import com.exam.Lms.Entity.Student;
import com.exam.Lms.Exception.CouponNotFoundException;
import com.exam.Lms.Exception.CourseNotFoundException;
import com.exam.Lms.Exception.StudentException.StudentAlreadyExistException;
import com.exam.Lms.Exception.StudentException.StudentNotFoundException;
import com.exam.Lms.Repository.StudentRepository;
import com.exam.Lms.Response.StudentResponse.StudentResponse;
import com.exam.Lms.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;


    @PostMapping("/AddStudent")
    public ResponseEntity<String> addStudent (@RequestBody StudentDto studentDto){
        try {
            String string = studentService.addStudent(studentDto);
            return ResponseEntity.status(HttpStatus.OK).body(string);
        } catch (StudentAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/GetStudentById")
    public ResponseEntity<?> getStudentById (@RequestParam int id){

        try {
            StudentResponse studentResponse = new StudentResponse("Successfull");
            studentResponse.setResponse(studentService.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
        } catch (StudentNotFoundException e) {
            StudentResponse studentResponse = new StudentResponse("Unsuccessfull");
            studentResponse.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(studentResponse);
        }
    }

    @GetMapping("/exportCsv/{studentId}")
    public ResponseEntity<byte[]> exportStudentToCsv(@PathVariable int studentId) {
        // Retrieve the student data by ID
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            // Handle student not found
            return ResponseEntity.notFound().build();
        }

        Student student = optionalStudent.get();

        // Convert student data to CSV format
        String csvData = convertStudentToCsv(student);

        // Prepare the CSV file for download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "student_data.csv");

        // Convert CSV data to a byte array for response
        byte[] csvBytes;
        try {
            csvBytes = csvData.getBytes("UTF-8");
        } catch (IOException e) {
            // Handle the exception
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvBytes);
    }

    private String convertStudentToCsv(Student student) {
        // Manually construct CSV data
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID,Name,Email,Mobile Number,Age\n");
        csvBuilder.append(student.getId()).append(",");
        csvBuilder.append(student.getName()).append(",");
        csvBuilder.append(student.getEmail()).append(",");
        csvBuilder.append(student.getMobileNumber()).append(",");
        csvBuilder.append(student.getAge()).append("\n");

        return csvBuilder.toString();
    }

    @PostMapping("/AddStudentWithDiscount")
    public ResponseEntity<String> addStudentWithDiscount(
            @RequestBody StudentDto studentDto,
            @RequestParam String couponCode) {
        try {
            String response = studentService.addStudentWithDiscount(studentDto, couponCode);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (StudentAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (CouponNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
