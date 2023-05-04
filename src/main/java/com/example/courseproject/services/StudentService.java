package com.example.courseproject.services;

import com.example.courseproject.models.Student;
import com.example.courseproject.models.StudentGroup;
import com.example.courseproject.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Student> findAllStudentsOfTheGroup(StudentGroup group){
    Long group_id = group.getId_group();
    List<Student> allStudents=studentRepository.findAll(); //все иностранные студенты
        List<Student> studentsOfTheGroup=new ArrayList<>();
        for(Student student:allStudents){
            if(student.getGroup().getId_group()==group_id){
                studentsOfTheGroup.add(student);
            }
        }
        return studentsOfTheGroup;
    }
    public int[] countStudentsByCountries(String [] countries){
    List<Student> allStudents=studentRepository.findAll();
    List<Student> students1=new ArrayList<>();
    List<Student> students2=new ArrayList<>();
    List<Student> students3=new ArrayList<>();
    List<Student> students4=new ArrayList<>();
    List<Student> students5=new ArrayList<>();
    int[] array=new int[5];
        for (Student student: allStudents) {
if(student.getHome_country().equals(countries[0])){
    students1.add(student);
}
        }
        for (Student student: allStudents) {
            if(student.getHome_country().equals(countries[1])){
                students2.add(student);
            }
        }
        for (Student student: allStudents) {
            if(student.getHome_country().equals(countries[2])){
                students3.add(student);
            }
        }
        for (Student student: allStudents) {
            if(student.getHome_country().equals(countries[3])){
                System.out.println("flag +1 China");
                students4.add(student);
            }
        }

        for (Student student: allStudents) {
            if((!student.getHome_country().equals(countries[3]))&&(!student.getHome_country().equals(countries[2]))){
                if((!student.getHome_country().equals(countries[1]))&&(!student.getHome_country().equals(countries[0]))){
                    students5.add(student);
                }
            }
        }
        array[0]=students1.size();
        array[1]=students2.size();
        array[2]=students3.size();
        array[3]=students4.size();
        array[4]=students5.size();
        return array;
    }
}
