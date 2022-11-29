package com.gov.appDemo.service;

import com.gov.appDemo.domain.Student;
import com.gov.appDemo.repository.JpaStudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitDBService implements CommandLineRunner {

    private final ParsingService parsingService;
    private final JpaStudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Student> students = parsingService.getStudentsList();
        studentRepository.saveAll(students);
    }
}
