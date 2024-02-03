package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.javaConfig;
import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.impl.DefaultGradeQueryService;
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
class GradeQueryServiceTest {
    @Autowired
    Students students;
    @Autowired
    Scores scores;
    @Autowired
    DataLoadService dataLoadService;
    @Autowired
    DefaultGradeQueryService defaultGradeQueryService;
    @BeforeEach
    void setUp() {
        dataLoadService.loadAndMerge();
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