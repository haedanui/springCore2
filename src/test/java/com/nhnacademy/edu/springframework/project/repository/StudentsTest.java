package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsTest {

    private Students students;
    private Scores scores;


    @BeforeEach
    void setUp(){
        students = CsvStudents.getInstance();
        scores = CsvScores.getInstance();
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