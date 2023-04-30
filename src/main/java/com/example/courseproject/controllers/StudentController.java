package com.example.courseproject.controllers;

import com.example.courseproject.models.*;
import com.example.courseproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final StudentGroupService studentGroupService;

@Autowired
    public StudentController(StudentService studentService, UserService userService, UserRoleService userRoleService, RoleService roleService, StudentGroupService studentGroupService) {
        this.studentService = studentService;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService=roleService;
        this.studentGroupService=studentGroupService;
    }
    @GetMapping("/student-registration")
    public String createStudentForm(Student student, User user, StudentGroup studentGroup){
    return "studentRegistrationPage";
    }
    @GetMapping("/student/{id}")
    public String getStudentMainPage(@PathVariable("id") String login,Model model){
    model.addAttribute("login",login);
return "studentMainPage";
    }
    @PostMapping("/student-registration")
    public String addStudent(Student student, User user, UserRole userRole, StudentGroup studentGroup){
    userService.saveUser(user);
    student.setUser(user);
    userRole.setUser(user);
    Role role = roleService.findRoleById(2L);
    StudentGroup stGr = studentGroupService.findStudentGroupById(studentGroup.getId_group());
    student.setGroup(stGr);
    studentService.saveStudent(student);
    userRole.setRole(role);
    userRoleService.saveUserRole(userRole);
    return "redirect:/sign-in";
    }

}
