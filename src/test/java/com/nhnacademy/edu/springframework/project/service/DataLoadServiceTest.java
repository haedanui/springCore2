package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import com.nhnacademy.edu.springframework.project.service.impl.CsvDataLoadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    Students students;

    CsvDataLoadService csvDataLoadService;
    @BeforeEach
    void setUp() {
        students = CsvStudents.getInstance();
        csvDataLoadService = new CsvDataLoadService();
    }

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