package com.example.courseproject.services;

import com.example.courseproject.models.StudentGroup;
import com.example.courseproject.repositories.StudentGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentGroupService {
    private final StudentGroupRepository studentGroupRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
@Autowired
    public StudentGroupService(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }
    public StudentGroupService(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        studentGroupRepository=context.getBean(StudentGroupRepository.class);
    }
    public List<StudentGroup> findAllStudentGroups(){
              return studentGroupRepository.findAll();
    }
    public StudentGroup findStudentGroupById(Long id){
               return studentGroupRepository.findById(id).orElse(null);
    }
    public StudentGroup  saveStudentGroup(StudentGroup studentGroup){
                return studentGroupRepository.save(studentGroup);
    }
    public void deleteStudentGroupById(Long id){
               studentGroupRepository.deleteById(id);
    }
    public int getAllFirstStageStudents(String stage){
    List<StudentGroup> allStudentGroups=studentGroupRepository.findAll();
    List<StudentGroup> firstStageGroups=new ArrayList<>();
    for(StudentGroup group: allStudentGroups){
        if(group.getStage_of_education().equals(stage)){
            firstStageGroups.add(group);
        }
    }
    int quantityOfStudents=0;
    for(StudentGroup group:firstStageGroups){
        quantityOfStudents+=group.getStudents().size();
    }
    return quantityOfStudents;
    }

}
