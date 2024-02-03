package com.nhnacademy.edu.springframework.project.repository.impl;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class CsvStudents implements Students {

    List<Student> studentList = new ArrayList<>();

    @Override
    public void load() {
        studentList.clear();
        try (InputStream is = CsvStudents.class.getResourceAsStream("/data/student.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] splitString = line.split(",");
                Student student = new Student(Integer.parseInt(splitString[0]), splitString[1]);
                studentList.add(student);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentList;
    }


    @Override
    public void merge(Collection<Score> scoreList) {
        List<Score> listScore = (List<Score>) scoreList;
        if (!listScore.isEmpty()) {
            for (int i = 0; i < listScore.size(); i++) {

                studentList.get(i).setScore(listScore.get(i));
            }
        }
    }
}
