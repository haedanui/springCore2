package com.nhnacademy.edu.springframework.project.service.impl;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultGradeQueryService implements GradeQueryService {

    private final Students students;
    private final Scores scores;

    @Autowired
    public DefaultGradeQueryService(Students students, Scores scores){
        this.students = students;
        this.scores = scores;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
        List<Student>studentList = (List<Student>) students.findAll();
        return studentList.stream().filter(s -> s.getName().equals(name)).map(Student::getScore).collect(Collectors.toList());

    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        List<Score>scoreList = scores.findAll();
        return scoreList.stream().filter(s -> s.getStudentSeq()==seq).findFirst().orElse(null);
    }
}
