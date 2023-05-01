package com.example.courseproject.services;

import com.example.courseproject.models.StudentGroup;
import com.example.courseproject.models.Subject;
import com.example.courseproject.repositories.StudentGroupRepository;
import com.example.courseproject.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
@Autowired
    public SubjectService(SubjectRepository subjectRepository,StudentGroupRepository studentGroupRepository) {
        this.subjectRepository = subjectRepository;
    }
    public SubjectService(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        subjectRepository=context.getBean(SubjectRepository.class);
    }
    public List<Subject> findSubjectsByGroup(StudentGroup studentGroup){
    return studentGroup.getSubjects();
    }
}
