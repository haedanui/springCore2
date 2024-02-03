package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.javaConfig;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {javaConfig.class})
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsTest {
    @Autowired
    private Students students;
    @Autowired
    private Scores scores;


    @BeforeEach
    void setUp(){
        students.load();
        scores.load();
    }

    @Test
    @Order(1)
    void load() {
        List<Student> studentList = (List<Student>) students.findAll();
        assertEquals(4, studentList.size());
    }

    @Test
    @Order(2)
    void findAll() {
        Student containStudent = new Student(1, "A");
        Student containStudent2 = new Student(2, "B");
        Student containStudent3 = new Student(3, "A");
        Student containStudent4 = new Student(4, "D");
        Student notContainStudent = new Student(1972, "K");

        List<Student> studentList = (List<Student>) students.findAll();

        assertTrue(studentList.contains(containStudent));
        assertTrue(studentList.contains(containStudent2));
        assertTrue(studentList.contains(containStudent3));
        assertTrue(studentList.contains(containStudent4));
        assertFalse(studentList.contains(notContainStudent));
    }

    @Test
    @Order(3)
    void merge() {
        List<Student> studentList = (List<Student>) students.findAll();
        List<Score> scoreList = scores.findAll();
        students.merge(scoreList);
        for (int i = 0; i < scoreList.size(); i++) {
            assertNotNull(studentList.get(i).getScore());
        }

        assertNull(studentList.get(3).getScore());
    }
}