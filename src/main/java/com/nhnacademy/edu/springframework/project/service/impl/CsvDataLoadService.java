package com.nhnacademy.edu.springframework.project.service.impl;

import com.nhnacademy.edu.springframework.project.repository.impl.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;

public class CsvDataLoadService implements DataLoadService {
    @Override
    public void loadAndMerge() {
        Scores scores = CsvScores.getInstance();
        scores.load();

        Students students = CsvStudents.getInstance();
        students.load();
        students.merge(scores.findAll());
    }
}
