package com.example.courseproject.services;

import com.example.courseproject.models.Marks;
import com.example.courseproject.models.Subject;
import com.example.courseproject.repositories.MarksRepository;
import com.example.courseproject.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarksService {
    private final MarksRepository marksRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
@Autowired
    public MarksService(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }
    public MarksService(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        marksRepository=context.getBean(MarksRepository.class);
    }
public List<Marks> findMarksByStudentId(Long id){
    List<Marks> marks=marksRepository.findAll();
    List<Marks> marksOfStudent=new ArrayList<Marks>();
    for(Marks mark:marks){
        if(mark.getStudent().getId_student()==id){
            marksOfStudent.add(mark);
        }
    }
    return marksOfStudent;
}

//public List<String> findMarksBySubjectNames(List<Subject> subjects, List<Marks> marks){
//
//
//    return
//
//}

}
