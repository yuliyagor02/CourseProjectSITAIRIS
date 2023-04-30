package com.example.courseproject.services;

import com.example.courseproject.models.Teacher;
import com.example.courseproject.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
@Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public TeacherService(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        teacherRepository=context.getBean(TeacherRepository.class);
    }
    public Teacher saveTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    public Teacher findTeacherById(Long id){
        return teacherRepository.findById(id).orElse(null);
    }
    public Teacher findTeacherByUserId(Long id){
        List<Teacher> teachers= teacherRepository.findAll();
        for (Teacher teacher: teachers) {
            if(teacher.getUser().getId_user()==id) return teacher;
        }
        return null;
    }
}
