package com.example.courseproject.controllers;

import com.example.courseproject.models.Student;
import com.example.courseproject.services.StudentGroupService;
import com.example.courseproject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TeacherController {
    private final StudentService studentService;
    private final StudentGroupService studentGroupService;
@Autowired
    public TeacherController(StudentService studentService,StudentGroupService studentGroupService) {
        this.studentService = studentService;
        this.studentGroupService=studentGroupService;
    }

    @GetMapping("/teacher/{id}")
    public String getTeacherMainPage(@PathVariable("id") String login, Model model){
        model.addAttribute("login",login);
        return "teacherMainPage";
    }
    @GetMapping("/stages")
    public String getStagesPage(Model model){
        int firstStageStudents = studentGroupService.getAllFirstStageStudents("бакалавриат");
        int secondStageStudents = studentGroupService.getAllFirstStageStudents("магистратура");
        int thirdStageStudents = studentGroupService.getAllFirstStageStudents("аспирантура");
        model.addAttribute("first",firstStageStudents);
        model.addAttribute("second",secondStageStudents);
        model.addAttribute("third",thirdStageStudents);
        return "stagesPage";
    }
    @GetMapping("/marks-control")
    public String getMarksControlPage(){
        return "marksControlPage";
    }
    @GetMapping("/students-by-groups")
    public String getStudentsByGroupsPage(){
        return "studentsByGroupsPage";
    }
}
