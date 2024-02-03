package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.service.impl.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.impl.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.domain.Student;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = new DefaultStudentService();
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);
    }
}
