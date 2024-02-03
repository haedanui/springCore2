package com.nhnacademy.edu.springframework.project.service.impl;

import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.impl.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.StudentService;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    @Override
    public Collection<Student> getPassedStudents() {
        Students studentRepository = CsvStudents.getInstance();
        return studentRepository.findAll().stream().filter(s -> Objects.nonNull(s.getScore()) &&!s.getScore().isFail()).collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        Students studentRepository = CsvStudents.getInstance();
        return studentRepository.findAll().stream().filter(s ->Objects.nonNull(s.getScore())).sorted(Comparator.comparing(Student::getScore).reversed()).collect(Collectors.toList());
    }

}
