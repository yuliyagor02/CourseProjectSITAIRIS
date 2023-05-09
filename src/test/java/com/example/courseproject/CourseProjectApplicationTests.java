package com.example.courseproject;

import com.example.courseproject.models.Student;
import com.example.courseproject.services.StudentService;
//import org.assertj.core.api.Assert;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.Assert;

@SpringBootTest
class CourseProjectApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
    }
    @Test
    void testFindingStudent(){
        Student student = studentService.findStudentById(1000101L);
        Assert.assertEquals((long)student.getId_student(),1000101L);
    }

}
