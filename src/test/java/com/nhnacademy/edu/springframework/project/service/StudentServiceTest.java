package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.javaConfig;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.impl.DefaultStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {javaConfig.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @Autowired
    Scores scores;
    @Autowired

    Students students;
    @Autowired

    DataLoadService dataLoadService;
    @Autowired

    DefaultStudentService defaultStudentService;

    @BeforeEach
    void setUp(){
        dataLoadService.loadAndMerge();
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