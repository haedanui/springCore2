package com.nhnacademy.edu.springframework.project.repository.impl;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {

    List<Score> scoreList = new ArrayList<>();

    private static class SingletonHelper {
        private static final CsvScores INSTANCE = new CsvScores();
    }

    public static Scores getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public void load() {
        scoreList.clear();
        try (InputStream is = CsvScores.class.getResourceAsStream("/data/score.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] splitString = line.split(",");
                Score score = new Score(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]));
                scoreList.add(score);
            }
        } catch (IOException e) {
            e.getMessage();
        }

    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
