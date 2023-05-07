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
    public List<Marks> findMarksBySubject(Subject subject){
    Long id=subject.getId_subject();
        List<Marks> marks=marksRepository.findAll();
        List<Marks> marksOfSubject=new ArrayList<Marks>();
        for(Marks mark:marks){
            if(mark.getSubject().getId_subject()==id){
                marksOfSubject.add(mark);
            }
        }
        return  marksOfSubject;
    }
public Marks saveMark(Marks mark){
    return marksRepository.save(mark);
}
public void deleteMarksOfSubject(Subject subject){
    Long id_subject= subject.getId_subject();
    List<Marks> marks=marksRepository.findAll();
    for (Marks mark:marks) {
        if(mark.getSubject().getId_subject()==id_subject){
            marksRepository.deleteById(mark.getId_mark());
        }
    }
}

//public List<String> findMarksBySubjectNames(List<Subject> subjects, List<Marks> marks){
//
//
//    return
//
//}

}
