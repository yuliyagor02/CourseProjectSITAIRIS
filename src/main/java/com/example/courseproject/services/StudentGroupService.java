package com.example.courseproject.services;

import com.example.courseproject.models.Student;
import com.example.courseproject.models.StudentGroup;
import com.example.courseproject.repositories.StudentGroupRepository;
import com.example.courseproject.repositories.StudentRepository;
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

    public int[] countStudentsByFaculties(String [] faculties){
        List<StudentGroup> allGroups=studentGroupRepository.findAll();
        List<StudentGroup> groups1=new ArrayList<>();
        List<StudentGroup> groups2=new ArrayList<>();
        List<StudentGroup> groups3=new ArrayList<>();
        List<StudentGroup> groups4=new ArrayList<>();
        List<StudentGroup> groups5=new ArrayList<>();

        int[] array=new int[5];
        for (StudentGroup stGroup: allGroups) {
            if(stGroup.getFaculty().equals(faculties[0])){
                groups1.add(stGroup);
                array[0]+=stGroup.getStudents().size();
            }
        }
        for (StudentGroup stGroup: allGroups) {
            if(stGroup.getFaculty().equals(faculties[1])){
                groups2.add(stGroup);
                array[1]+=stGroup.getStudents().size();
            }
        }
        for (StudentGroup stGroup: allGroups) {
            if(stGroup.getFaculty().equals(faculties[2])){
                groups3.add(stGroup);
                array[2]+=stGroup.getStudents().size();
            }
        }
        for (StudentGroup stGroup: allGroups) {
            if(stGroup.getFaculty().equals(faculties[3])){
                groups4.add(stGroup);
                array[3]+=stGroup.getStudents().size();
            }
        }
        for (StudentGroup stGroup: allGroups) {
            if(stGroup.getFaculty().equals(faculties[4])){
                groups5.add(stGroup);
                array[4]+=stGroup.getStudents().size();
            }
        }
        return array;
    }
}
