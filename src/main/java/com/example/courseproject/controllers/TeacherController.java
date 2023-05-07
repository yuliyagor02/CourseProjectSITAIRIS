package com.example.courseproject.controllers;

import com.example.courseproject.models.*;
import com.example.courseproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {
    private final StudentService studentService;
    private final StudentGroupService studentGroupService;
    private final SubjectService subjectService;
    private final MarksService marksService;
    private final UserService userService;
@Autowired
    public TeacherController(StudentService studentService, StudentGroupService studentGroupService, SubjectService subjectService, MarksService marksService, UserService userService) {
        this.studentService = studentService;
        this.studentGroupService=studentGroupService;
    this.subjectService = subjectService;
    this.marksService = marksService;
    this.userService = userService;
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
    @GetMapping("/marks-control/{id_group}/{id_student}")
    public String getEstimatingPage(@PathVariable("id_group") Long id_group,@PathVariable("id_student") Long id_student,Model model){
    StudentGroup group = studentGroupService.findStudentGroupById(id_group);
    model.addAttribute("group",group);
    Student student = studentService.findStudentById(id_student);
    model.addAttribute("student",student);
    List<Subject> subjectsOfStudent=student.getGroup().getSubjects();
    model.addAttribute("subjects",subjectsOfStudent);
    User user = student.getUser();
    model.addAttribute("user",user);
        int[] array ={1,2,3,4,5,6,7,8,9,10};
        model.addAttribute("marks",array);
    return "estimatingPage";
    }
    @GetMapping("/marks-control/{id}")
    public String getMarksControlPage(@PathVariable("id") String id,Model model){
        Long id_group = Long.parseLong(id);
        StudentGroup studentGroup = studentGroupService.findStudentGroupById(id_group);
        List<Student> studentsOfGroup = studentGroup.getStudents();
        List<User> users = new ArrayList<>();
        for (Student student:studentsOfGroup) {
            users.add(student.getUser());
        }
        Long id_st=Long.parseLong(id);
        model.addAttribute("id_st",id_st);
        model.addAttribute("users",users);
        model.addAttribute("students",studentsOfGroup);
        List<Subject> subjects = studentGroup.getSubjects();
        model.addAttribute("subjects",subjects);
        int[] array ={1,2,3,4,5,6,7,8,9,10};
        model.addAttribute("marks",array);
        //List<StudentGroup> allGroups=studentGroupService.findAllStudentGroups();
        //model.addAttribute("allGroups",allGroups);
        //List<Student> studentsOfTheGroup=studentService.findAllStudentsOfTheGroup();
        return "marksGroupControlPage";
    }
    @GetMapping("/marks-control")
    public String getMarksControlPage(Model model){
    List<StudentGroup> allGroups=studentGroupService.findAllStudentGroups();
    model.addAttribute("allGroups",allGroups);
    //List<Student> studentsOfTheGroup=studentService.findAllStudentsOfTheGroup();
        return "marksControlPage";
    }
    @PostMapping("/marks-control")
    public String sendMarksControlForm(@RequestParam(name="allGroups") String id){
        return "redirect:/marks-control/"+id;
    }
    @PostMapping("/marks-student-control")
    public String sendMarksGroupControlForm(@RequestParam(name="subjectMarked") String subject,@RequestParam(name="markedNumb") int mark,@RequestParam(name="id_stud") String id){
    Long id_student=Long.parseLong(id);
        Student student =studentService.findStudentById(id_student);
        Marks mark1 = new Marks();
        StudentGroup studentGroup=student.getGroup();
        mark1.setMark(mark);
        Subject subject1 = subjectService.findSubjectByName(subject);
        mark1.setSubject(subject1);
        mark1.setStudent(student);
        Date actual_date= Date.valueOf(LocalDate.now());
        mark1.setDate_of_receipt(actual_date);
        marksService.saveMark(mark1);
        System.out.println("отметочка добавлена!!");
        return "redirect:/marks-control/"+Long.toString(studentGroup.getId_group())+"/"+Long.toString(id_student);
    }
    @GetMapping("/students-by-groups")
    public String getStudentsByGroupsPage(){
        return "studentsByGroupsPage";
    }
}
