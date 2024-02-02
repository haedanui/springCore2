package com.nhnacademy.edu.springframework.project.repository.impl;

import com.nhnacademy.edu.springframework.project.domain.Score;
import com.nhnacademy.edu.springframework.project.domain.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CsvStudents implements Students {

    List<Student> studentList = new ArrayList<>();

    /** TODO0 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    private static class SingletonHelper{
        private static final CsvStudents INSTANCE = new CsvStudents();
    }

    public static Students getInstance() {
        return  SingletonHelper.INSTANCE;
    }

    // TODO0 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        try(BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/data/student.csv"), StandardCharsets.UTF_8)){
            String line = "";
            while((line = br.readLine()) != null){
                String[] splitString = line.split(",");
                Student student = new Student(Integer.parseInt(splitString[0]), splitString[1]);
                studentList.add(student);
            }
        }catch (IOException e){
            e.getMessage();
        }
    }

    //TODO0 여기도 구현해야됨
    @Override
    public Collection<Student> findAll() {
        return studentList;
    }

    /**
     * TODO0 8 : students 데이터에 score 정보를 추가하세요.
     * @param scoreList
     */
    @Override
    public void merge(Collection<Score> scoreList) {
        List<Score> listScore = (List<Score>) scoreList;
        if(!scoreList.isEmpty()){
            for(int i = 0; i < studentList.size(); i++){
                studentList.get(i).setScore(listScore.get(i));
            }
        }
    }
}
