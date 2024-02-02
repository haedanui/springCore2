package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import com.nhnacademy.edu.springframework.project.service.impl.DefaultStudentService;
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
        List<Student> passStudent = (List<Student>) defaultStudentService.getPassedStudents();
        assertEquals(passStudent.size(), 2);
    }

    @Test
    void getStudentsOrderByScore() {
        List<Student> sortedStudent = (List<Student>) defaultStudentService.getStudentsOrderByScore();
        for(int i = 1; i<sortedStudent.size();i++){
            assertTrue(sortedStudent.get(i).getScore().getScore() < sortedStudent.get(i-1).getScore().getScore());
        }
    }
}