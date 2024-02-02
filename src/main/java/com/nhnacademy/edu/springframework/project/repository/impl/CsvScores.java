package com.nhnacademy.edu.springframework.project.repository.impl;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {

    List<Score> scoreList = new ArrayList<>();

    /**
     * TODO0 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/

    private static class SingletonHelper {
        private static final CsvScores INSTANCE = new CsvScores();
    }

    public static Scores getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // TODO0 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/data/score.csv"), StandardCharsets.UTF_8)) {
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

    //TODO0 여기도 구현해야됨
    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
