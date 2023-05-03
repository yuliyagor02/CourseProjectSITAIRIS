package com.example.courseproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TeacherController {
    @GetMapping("/teacher/{id}")
    public String getTeacherMainPage(@PathVariable("id") String login, Model model){
        model.addAttribute("login",login);
        return "teacherMainPage";
    }
    @GetMapping("/stages")
    public String getStagesPage(){
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
