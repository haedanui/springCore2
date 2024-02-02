package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvScores;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScoresTest {
    private Scores scores;

    @BeforeEach
    void setUp(){
        scores = CsvScores.getInstance();
        scores.load();
    }

    @Test
    @Order(1)
    void load() {
        List<Score> scoreList = scores.findAll();
        assertEquals(3, scoreList.size());
    }

    @Test
    @Order(2)
    void findAll() {
        Score containScore = new Score(1, 30);
        Score containScore2 = new Score(2, 80);
        Score containScore3 = new Score(3, 70);
        Score notContainScore = new Score(1972, 1121);

        List<Score> scoreList = scores.findAll();

        assertEquals(3, scoreList.size());
        assertTrue(scoreList.contains(containScore));
        assertTrue(scoreList.contains(containScore2));
        assertTrue(scoreList.contains(containScore3));
        assertFalse(scoreList.contains(notContainScore));
    }
}