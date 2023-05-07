package com.example.courseproject.controllers;

import com.example.courseproject.models.Student;
import com.example.courseproject.models.StudentGroup;
import com.example.courseproject.models.User;
import com.example.courseproject.models.UserRole;
import com.example.courseproject.services.StudentGroupService;
import com.example.courseproject.services.StudentService;
import com.example.courseproject.services.UserRoleService;
import com.example.courseproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentGroupController {
private final StudentGroupService studentGroupService;
private final StudentService studentService;
    private final UserRoleService userRoleService;
    private final UserService userService;
@Autowired
    public StudentGroupController(StudentGroupService studentGroupService, StudentService studentService, UserRoleService userRoleService, UserService userService){
    this.studentGroupService=studentGroupService;
    this.studentService = studentService;
    this.userRoleService = userRoleService;
    this.userService = userService;
}

@GetMapping("/groups")
    public String getStudentGroupsPage(Model model){
    List<StudentGroup> studentGroups=studentGroupService.findAllStudentGroups();
    model.addAttribute("studentGroups",studentGroups);
    return "studentGroupsPage";
}
    @GetMapping("/create-group")
    public String getStudentGroupCreatePage(StudentGroup studentGroup, Model model){
    List<StudentGroup> studentGroups=studentGroupService.findAllStudentGroups();
    Long id_group= studentGroups.get(studentGroups.size()-1).getId_group()+1;
    model.addAttribute("id_group1",id_group);
        return "studentGroupCreatePage";
    }
    @GetMapping("delete-group/{id}")
    public String deleteStudentGroupById(@PathVariable("id") Long id){
    StudentGroup studentGroup = studentGroupService.findStudentGroupById(id);
    List<Student> studentsOfTheGroup=studentService.findAllStudentsOfTheGroup(studentGroup);
        for (Student student: studentsOfTheGroup) {
            User user = student.getUser();
            List<UserRole> userRoles = user.getUser_roles();
            for(int i=0;i<userRoles.size();i++){                 //удалили все userRoles
                userRoleService.deleteUserRole(userRoles.get(i));
            }
            userService.deleteUser(user);

        }
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
    public String sendStudentGroupCreateForm(@RequestParam(name="groupId")Long id, StudentGroup studentGroup){
    //Long id=Long.parseLong(id_gr);
    studentGroup.setId_group(id);
    studentGroupService.saveStudentGroup(studentGroup);
    return "redirect:/groups";
}
@PostMapping("/update-group")
    public String sendStudentGroupUpdateForm(StudentGroup studentGroup){
    studentGroupService.saveStudentGroup(studentGroup);
    return "redirect:/groups";
}
}
