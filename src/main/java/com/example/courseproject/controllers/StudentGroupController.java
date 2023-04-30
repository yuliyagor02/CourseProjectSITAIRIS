package com.example.courseproject.controllers;

import com.example.courseproject.models.StudentGroup;
import com.example.courseproject.services.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentGroupController {
private final StudentGroupService studentGroupService;
@Autowired
    public StudentGroupController(StudentGroupService studentGroupService){
    this.studentGroupService=studentGroupService;
}

@GetMapping("/groups")
    public String getStudentGroupsPage(Model model){
    List<StudentGroup> studentGroups=studentGroupService.findAllStudentGroups();
    model.addAttribute("studentGroups",studentGroups);
    return "studentGroupsPage";
}
    @GetMapping("/create-group")
    public String getStudentGroupCreatePage(StudentGroup studentGroup){
        return "studentGroupCreatePage";
    }
    @GetMapping("delete-group/{id}")
    public String deleteStudentGroupById(@PathVariable("id") Long id){
        studentGroupService.deleteStudentGroupById(id);
        return"redirect:/groups";
    }
    @GetMapping("/update-group/{id}")
    public String getStudentGroupUpdatePage(@PathVariable("id") Long id, Model model){
        StudentGroup studentGroup = studentGroupService.findStudentGroupById(id);
        model.addAttribute("studentGroup",studentGroup);
        return "studentGroupUpdatePage";
    }
@PostMapping("/create-group")
    public String sendStudentGroupCreateForm(StudentGroup studentGroup){
    studentGroupService.saveStudentGroup(studentGroup);
    return "redirect:/groups";
}
@PostMapping("/update-group")
    public String sendStudentGroupUpdateForm(StudentGroup studentGroup){
    studentGroupService.saveStudentGroup(studentGroup);
    return "redirect:/groups";
}
}
