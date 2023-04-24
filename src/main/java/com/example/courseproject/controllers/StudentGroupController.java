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

@GetMapping("/studentGroups")
    public String getStudentGroupsPage(Model model){
    List<StudentGroup> studentGroups=studentGroupService.findAllStudentGroups();
    model.addAttribute("studentGroups",studentGroups);
    return "studentGroupsPage";
}
@PostMapping("/add-studentGroup")
    public String addStudentGroup(StudentGroup studentGroup){
    studentGroupService.saveStudentGroup(studentGroup);
    return "redirect:/studentGroups";
}
@GetMapping("/add-studentGroup")
    public String createStudentGroupForm(StudentGroup studentGroup){
    return "studentGroupCreatePage";
}
@GetMapping("delete-studentGroup/{id}")
    public String deleteStudentGroupById(@PathVariable("id") Long id){
    studentGroupService.deleteById(id);
    return"redirect:/studentGroups";
}
@GetMapping("/studentGroupUpdate/{id}")
    public String updateStudentGroupForm(@PathVariable("id") Long id, Model model){
    StudentGroup studentGroup = studentGroupService.findStudentGroupById(id);
    model.addAttribute("studentGroup",studentGroup);
    return "studentGroupUpdatePage";
}
@PostMapping("/studentGroupUpdate")
    public String updateStudentGroup(StudentGroup studentGroup){
    studentGroupService.saveStudentGroup(studentGroup);
    return "redirect:/studentGroups";
}
}
