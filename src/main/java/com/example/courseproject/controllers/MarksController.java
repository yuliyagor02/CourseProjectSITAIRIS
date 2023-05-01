package com.example.courseproject.controllers;

import com.example.courseproject.models.Marks;
import com.example.courseproject.models.Student;
import com.example.courseproject.models.StudentGroup;
import com.example.courseproject.models.Subject;
import com.example.courseproject.services.MarksService;
import com.example.courseproject.services.StudentGroupService;
import com.example.courseproject.services.StudentService;
import com.example.courseproject.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MarksController {
    private final StudentService studentService;
    private final MarksService marksService;
    private final StudentGroupService studentGroupService;
    private final SubjectService subjectService;
@Autowired
    public MarksController(StudentService studentService, MarksService marksService,StudentGroupService studentGroupService,SubjectService subjectService) {
        this.studentService = studentService;
        this.marksService = marksService;
        this.studentGroupService=studentGroupService;
        this.subjectService=subjectService;
    }
    @GetMapping("/student-marks/{id}")
    public String getStudentMarksPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("id",id);
        Student student = studentService.findStudentById(id);
        StudentGroup studentGroup = student.getGroup();
//        Long group_id= student.getGroup().getId_group();
//        StudentGroup studentGroup = studentGroupService.findStudentGroupById(group_id);
        //List<Subject> subjects = subjectService.findSubjectsByGroup(studentGroup);
        List<Subject> subjects = studentGroup.getSubjects();
        model.addAttribute("subjects",subjects); //отправили список предметов этого студента
        List<Marks> marks = marksService.findMarksByStudentId(id);
        model.addAttribute("marks",marks); //отправили список отметок

        List<String> marks1 = new ArrayList<>();
        String str=new String();
       for(int j=0;j<subjects.size();j++){
           for(int i=0;i<marks.size();i++){
               if(marks.get(i).getSubject().getId_subject()==subjects.get(j).getId_subject()){
                   str+=marks.get(i).getMark()+" ";
               }
           }
           if(str.equals("")) str="нет отметок";
           marks1.add(str);
           str="";
       }


        System.out.println(marks1.size());
        model.addAttribute("marks1",marks1); //отправили список отметок
        return "studentMarksPage";
    }
}
