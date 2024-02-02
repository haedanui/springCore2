package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    Scores scores;
    Students students;

    @BeforeEach
    void setUp() {
        students = CsvStudents.getInstance();
        scores = CsvScores.getInstance();
        scores.load();
        students.load();
    }

    @Test
    void loadAndMerge() {
        List<Student> studentList = (List<Student>) students.findAll();
        List<Score> scoreList = scores.findAll();
        for (Student student : studentList) {
            assertNull(student.getScore());
        }

        students.merge(scoreList);

        studentList = (List<Student>) students.findAll();

        for (int i = 0; i < scoreList.size(); i++) {
            assertNotNull(studentList.get(i).getScore());
        }
        assertNull(studentList.get(3).getScore());

    }
}