package com.gov.appDemo.controller;

import com.gov.appDemo.domain.Student;
import com.gov.appDemo.repository.JpaStudentRepository;
import com.gov.appDemo.service.ParsingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final JpaStudentRepository studentRepository;

    @PutMapping("/register")
    public String registerStudent(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "graduation") String graduation,
        @RequestParam(value = "degree") String degree) {

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setGraduation(graduation);
        student.setDegree(degree);

        if (studentRepository.hasStudent(student)) {
            return "Already registered";
        }

        studentRepository.save(student);
        return "Registration successful";
    }

    @GetMapping("/degree")
    public String getStudentDegreeByName(@RequestParam(value = "name") String name) {
        List<Student> students = studentRepository.findStudentByName(name);

        if (students.isEmpty()) {
            return "No such student";
        }

        if (students.size() > 1) {
            return "There are multiple students with the same name. Please provide an email address instead.";
        }

        return name + " : " + students.get(0).getDegree();
    }

    @GetMapping("/email")
    public String getStudentEmailByName(@RequestParam(value = "name") String name) {
        List<Student> students = studentRepository.findStudentByName(name);

        if (students.isEmpty()) {
            return "No such student";
        }

        if (students.size() > 1) {
            return "There are multiple students with the same name. Please provide an email address instead.";
        }

        return name + " : " + students.get(0).getEmail();
    }

    @GetMapping("/stat")
    public String getTheNumberOfStudentByDegree(@RequestParam(value = "degree") String degree) {
        String degreeString = degree.substring(0, 1).toUpperCase() + degree.substring(1);
        return "Number of " + degreeString + "'s student : "
            + studentRepository.countStudentByDegree(degree);
    }
}
