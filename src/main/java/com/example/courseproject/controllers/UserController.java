package com.example.courseproject.controllers;

import com.example.courseproject.models.Student;
import com.example.courseproject.models.Teacher;
import com.example.courseproject.models.User;
import com.example.courseproject.models.UserRole;
import com.example.courseproject.services.StudentService;
import com.example.courseproject.services.TeacherService;
import com.example.courseproject.services.UserRoleService;
import com.example.courseproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserRoleService userRoleService;
@Autowired
    public UserController(UserService userService, StudentService studentService,UserRoleService userRoleService,TeacherService teacherService) {
        this.userService = userService;
        this.studentService=studentService;
        this.userRoleService=userRoleService;
        this.teacherService=teacherService;
    }
    @GetMapping("/sign-in")
    public String getSignInForm( User user, Model model){
    return "signInPage";
    }
    @GetMapping("/admin")
    public String getStudentMainPage(Model model){
        return "adminMainPage";
    }
    @PostMapping("/sign-in")
    public String sendSignInForm(User user){
    User user1 =userService.findUserByLogin(user.getLogin());

    if(user1!=null){
        UserRole userRole=userRoleService.findUserRoleByUserId(user1.getId_user());
        String roleName=userRole.getRole().getRole_name();
        if((user.getPassword().equals(user1.getPassword()))){
if(roleName.equals("admin")){
    return "redirect:/admin";
}
else if(roleName.equals("user")){
    Student student= studentService.findStudentByUserId(user1.getId_user());
    return "redirect:/student/"+Long.toString(student.getId_student());
}
else {
    Teacher teacher = teacherService.findTeacherByUserId(user1.getId_user());
    return "redirect:/teacher/"+Long.toString(teacher.getId_teacher());
}
        }
        else{
            return "redirect:/sign-in?fail";
        }
    }
    else {
        return "redirect:/sign-in?fail";
    }
    }
}
