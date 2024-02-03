package com.nhnacademy.edu.springframework.project.service.impl;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultGradeQueryService implements GradeQueryService {

    @Override
    public List<Score> getScoreByStudentName(String name) {
        return CsvStudents.getInstance().findAll().stream()
                .filter(s -> s.getName().equals(name))
                .map(Student::getScore).collect(Collectors.toList());

    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        return CsvStudents.getInstance().findAll().stream()
                .filter(s -> s.getScore().getStudentSeq()==seq)
                .findFirst()
                .map(Student::getScore).orElse(null);
    }
}
