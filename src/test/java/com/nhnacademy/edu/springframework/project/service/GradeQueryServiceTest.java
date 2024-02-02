package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import com.nhnacademy.edu.springframework.project.service.impl.DefaultGradeQueryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeQueryServiceTest {

    Students students;
    Scores scores;
    DefaultGradeQueryService defaultGradeQueryService;
    @BeforeEach
    void setUp() {
        students = CsvStudents.getInstance();
        scores = CsvScores.getInstance();
        defaultGradeQueryService = new DefaultGradeQueryService();
        students.load();
        scores.load();
        students.merge(scores.findAll());
    }

    @Test
    void getScoreByStudentName() {
        List<Score> resultAScoreList = defaultGradeQueryService.getScoreByStudentName("A");
        assertEquals(resultAScoreList.size(), 2);
        assertEquals(resultAScoreList.get(0).getScore(), 30);
        assertEquals(resultAScoreList.get(1).getScore(), 70);
        resultAScoreList = defaultGradeQueryService.getScoreByStudentName("B");
        assertEquals(resultAScoreList.size(), 1);
        assertEquals(resultAScoreList.get(0).getScore(), 80);
        resultAScoreList = defaultGradeQueryService.getScoreByStudentName("C");
        assertEquals(resultAScoreList.size(), 0);
    }

    @Test
    void getScoreByStudentSeq() {
        List<Score> scoreList = scores.findAll();
        students.merge(scoreList);
        Score resultScore = defaultGradeQueryService.getScoreByStudentSeq(1);
        assertEquals(resultScore.getScore(), 30);
        resultScore = defaultGradeQueryService.getScoreByStudentSeq(2);
        assertEquals(resultScore.getScore(), 80);
        resultScore = defaultGradeQueryService.getScoreByStudentSeq(3);
        assertEquals(resultScore.getScore(), 70);
    }
}