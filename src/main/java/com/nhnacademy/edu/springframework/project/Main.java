package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.service.impl.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.impl.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.domain.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    // TODO 9 - 성공적으로 실행되어야 합니다.
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project");
        DataLoadService dataLoadService = context.getBean("csvDataLoadService", CsvDataLoadService.class);
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = context.getBean("defaultStudentService", DefaultStudentService.class);
        List<Student> passStudents = (List<Student>) studentService.getPassedStudents();
        System.out.println(passStudents);

        List<Student> orderedStudents = (List<Student>) studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);
//        DataLoadService dataLoadService = new CsvDataLoadService();
//        dataLoadService.loadAndMerge();
//
//        DefaultStudentService studentService = new DefaultStudentService();
//        Collection<Student> passedStudents = studentService.getPassedStudents();
//        System.out.println(passedStudents);
//
//        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
//        System.out.println(orderedStudents);
    }
}
