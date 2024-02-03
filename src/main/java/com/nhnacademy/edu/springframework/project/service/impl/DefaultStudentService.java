package com.nhnacademy.edu.springframework.project.service.impl;

import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DefaultStudentService implements StudentService {

    private final Students studentRepository;

    @Autowired
    public DefaultStudentService(Students studentRepository){
        this.studentRepository = studentRepository;
    }
    @Override
    public Collection<Student> getPassedStudents() {
        return studentRepository.findAll().stream().filter(s -> Objects.nonNull(s.getScore()) &&!s.getScore().isFail()).collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        return studentRepository.findAll().stream().filter(s ->Objects.nonNull(s.getScore())).sorted(Comparator.comparing(Student::getScore).reversed()).collect(Collectors.toList());
    }

}
