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

import javax.swing.plaf.nimbus.State;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final StudentGroupService studentGroupService;
    private final StatementService statementService;

@Autowired
    public StudentController(StudentService studentService, UserService userService, UserRoleService userRoleService, RoleService roleService, StudentGroupService studentGroupService,StatementService statementService) {
        this.studentService = studentService;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService=roleService;
        this.studentGroupService=studentGroupService;
        this.statementService=statementService;
    }
    @GetMapping("/student-registration")
//    Student student, User user, StudentGroup studentGroup
    public String getStudentRegistrationPage(Model model,Student student, User user, StudentGroup studentGroup){
    List<StudentGroup> allGroups=studentGroupService.findAllStudentGroups();
    model.addAttribute("allGroups",allGroups);
    List<Student> students=studentService.findAll();
    Long id_stud=students.get(students.size()-1).getId_student()+1; //номер зачётной книжки=логин
        model.addAttribute("id_stud",id_stud);
    return "studentRegistrationPage";
    }


    @GetMapping("/student-visa-and-temporary-registration/{id}")
    public String getStudentVisaAndTemporaryRegistrationPage(@PathVariable("id") Long id,Model model){
        model.addAttribute("id",id);
        Student student = studentService.findStudentById(id);

        Date visa_validity = student.getVisa_validity();
        model.addAttribute("visa_validity",visa_validity);
        Date actual_date = Date.valueOf(LocalDate.now());
        model.addAttribute("actual_date",actual_date);
        long difference1= visa_validity.getTime()-actual_date.getTime();
        difference1 = TimeUnit.DAYS.convert(difference1,TimeUnit.MILLISECONDS);
        model.addAttribute("days_left1",difference1);

        Date registration_validity = student.getTemporary_registration_validity();
        model.addAttribute("registration_validity",registration_validity);
        long difference2 = registration_validity.getTime()-actual_date.getTime();
        difference2 = TimeUnit.DAYS.convert(difference2,TimeUnit.MILLISECONDS);
        model.addAttribute("days_left2",difference2);
        return "studentVisaAndTemporaryRegistrationPage";
    }
    @GetMapping("/student-health-insurance/{id}")
    public String getStudentHealthInsurancePage(@PathVariable("id") Long id,Model model){
        model.addAttribute("id",id);
        Student student = studentService.findStudentById(id);
        Date validity = student.getHealth_insurance_validity();
        model.addAttribute("insurance_validity",validity);
        Date actual_date= Date.valueOf(LocalDate.now());
        model.addAttribute("actual_date",actual_date);
        long difference= validity.getTime()-actual_date.getTime();
        difference = TimeUnit.DAYS.convert(difference,TimeUnit.MILLISECONDS);
        //System.out.println("Осталось дней: "+difference);
        model.addAttribute("days_left",difference);
        return "studentHealthInsurancePage";
    }
    @GetMapping("/student-statements/{id}")
    public String getStudentStatementsPage(@PathVariable("id") Long id, Model model, Statement statement){
        model.addAttribute("id",id);
        Student student = studentService.findStudentById(id);
        List<Statement> statements=student.getStatements();
        model.addAttribute("statements",statements);
        return "studentStatementsPage";
    }
    @PostMapping("/student-statements")
    public String sendStudentStatementsForm(@RequestParam(name="id") String id,Model model,Statement statement){
//        model.addAttribute("id",id);
        Long id_stud=Long.parseLong(id);
        byte a =0;
        System.out.println("id "+id);
        Student student = studentService.findStudentById(id_stud);
        statement.setStudent(student);
        statement.setIsPrinted(a);
        statementService.saveOrUpdateStatement(statement);

        return "redirect:/student-statements/"+Long.toString(id_stud);
    }

    @GetMapping("/university")
    public String getUniversityPage(Model model){
        //model.addAttribute("id",id);
        return "universityPage";
    }
//    @GetMapping("/student-marks/{id}")
//    public String getStudentMarksPage(@PathVariable("id") Long id,Model model){
//        model.addAttribute("id",id);
//        return "studentMarksPage";
//    }

    @GetMapping("/student/{id}")
    public String getStudentMainPage(@PathVariable("id") Long id,Model model){
        model.addAttribute("id",id);
        return "studentMainPage";
    }
    @PostMapping("/student-registration")
    public String sendStudentRegistrationForm(@RequestParam(name="studentId")Long id_st,@RequestParam(name="allGroups")String id, Student student, User user, UserRole userRole, StudentGroup studentGroup){
Long id_gr=Long.parseLong(id);
    student.setId_student(id_st);
    user.setLogin(Long.toString(id_st));
    userService.saveUser(user);
    student.setUser(user);
    userRole.setUser(user);
    Role role = roleService.findRoleById(2L);
    StudentGroup stGr = studentGroupService.findStudentGroupById(id_gr);//studentGroup.getId_group()
    student.setGroup(stGr);
    byte a = 1;
    student.setIs_health_insurance(a);
    studentService.saveStudent(student);
    userRole.setRole(role);
    userRoleService.saveUserRole(userRole);
    return "redirect:/sign-in";
    }

    @GetMapping("/update-student/{id}")
    public String getStudentUpdatePage(@PathVariable("id") Long id,Model model){
        model.addAttribute("id",id);
        Student student = studentService.findStudentById(id);
        model.addAttribute("student",student);
        StudentGroup studentGroup = student.getGroup();
        User user = student.getUser();
        model.addAttribute("studentGroup",studentGroup);
        model.addAttribute("user",user);
        return "studentUpdatePage";
    }
    @PostMapping("/update-student")
    public String sendStudentUpdateForm(@RequestParam(name="id_stud")Long id_stud, Student student, StudentGroup studentGroup, User user){
        System.out.println("hobbie "+student.getHobbies());
        System.out.println("skills "+student.getSkills());
        Student student1 = studentService.findStudentById(id_stud);
        student1.setHobbies(student.getHobbies());
        student1.setSkills(student.getSkills());

        studentService.saveStudent(student1);

    return "redirect:/student/"+Long.toString(student1.getId_student());
    }
    @GetMapping("/student-search/{id}")
    public String getStudentFoundPage(@PathVariable("id") Long id, Model model){
    model.addAttribute("flag",0);
    if(id==0) model.addAttribute("flag",1);
    else{
        Student student=studentService.findStudentById(id);
        User user = student.getUser();
        StudentGroup studentGroup=student.getGroup();
        model.addAttribute("student",student);
        model.addAttribute("user",user);
        model.addAttribute("stud_group",studentGroup);
    }
        return "studentFoundPage";
    }
    @GetMapping("/student-search")
    public String getStudentSearchPage(){
        return "studentSearchPage";
    }
    @PostMapping("/student-search")
    public String sendStudentSearchForm(@RequestParam(name="id_student") String id,Model model){
    Long id_stud=Long.parseLong(id);
    Student student = studentService.findStudentById(id_stud);
    User user = new User();
    StudentGroup studentGroup=new StudentGroup();
    if(student==null) {
        id="0";
    }
    else{
        user=student.getUser();
        studentGroup=student.getGroup();
        System.out.println("id группы "+studentGroup.getId_group());
        model.addAttribute("student",student);
        model.addAttribute("user",user);
        model.addAttribute("stud_group",studentGroup);
    }

        return "redirect:/student-search/"+id;
    }

}
