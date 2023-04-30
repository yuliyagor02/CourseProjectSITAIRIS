package com.example.courseproject.services;

import com.example.courseproject.models.Student;
import com.example.courseproject.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public StudentService(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        studentRepository=context.getBean(StudentRepository.class);
    }
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
    public Student findStudentById(Long id){
        return studentRepository.findById(id).orElse(null);
    }
    public Student findStudentByUserId(Long id){
        List<Student> students= studentRepository.findAll();
        for (Student student: students) {
            if(student.getUser().getId_user()==id) return student;
        }
        return null;
    }
}
