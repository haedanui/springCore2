package com.nhnacademy.edu.springframework.project.service.impl;

import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    Scores scores;
    Students students;
    DefaultStudentService defaultStudentService;

    @BeforeEach
    void setUp(){
        scores = CsvScores.getInstance();
        students = CsvStudents.getInstance();
        defaultStudentService = new DefaultStudentService();
        scores.load();
        students.load();
        students.merge(scores.findAll());
    }
    @Test
    void getPassedStudents() {
//        List<Student> passStudent = (List<Student>) defaultStudentService.getPassedStudents();
    }

    @Test
    void getStudentsOrderByScore() {

    }
}