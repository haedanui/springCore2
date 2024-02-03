package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.javaConfig;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.impl.CsvDataLoadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {javaConfig.class})
@ExtendWith(SpringExtension.class)
class DataLoadServiceTest {

    @Autowired
    Students students;
    @Autowired
    CsvDataLoadService csvDataLoadService;

    @Test
    void loadAndMerge() {

        csvDataLoadService.loadAndMerge();

        List<Student> studentList = (List<Student>) students.findAll();

        for (int i = 0; i < 3; i++) {
            assertNotNull(studentList.get(i).getScore());
        }
        assertNull(studentList.get(3).getScore());

    }
}