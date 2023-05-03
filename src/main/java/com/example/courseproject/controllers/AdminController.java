package com.example.courseproject.controllers;

import com.example.courseproject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final StudentService studentService;
@Autowired
    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/student-search")
    public String getStudentSearchPage(){
    return "studentSearchPage";
    }
    @GetMapping("/student-delete")
    public String getStudentDeletePage(){
    return "studentDeletePage";
    }
    @GetMapping("/student-send-message")
    public String getStudentSendMessagePage(){
    return "studentSendMessagePage";
    }
    @GetMapping("/student-check-statements")
    public String getStudentCheckStatementsPage(){
    return "studentCheckStatementsPage";
    }
    @GetMapping("/control-group-subjects")
    public String getControlGroupSubjectsPage(){
    return "controlGroupSubjectsPage";
    }
    @GetMapping("/control-teachers")
    public String getControlTeachersPage(){
    return "controlTeachersPage";
    }
    @GetMapping("/students-geography")
    public String getStudentGeographyPage(){
        return "studentGeographyPage";
    }

    @GetMapping("/faculties-popularity")
    public String getFacultiesPopularityPage(){
        return "facultiesPopularityPage";
    }
}
