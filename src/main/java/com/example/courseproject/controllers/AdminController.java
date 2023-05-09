package com.example.courseproject.controllers;

import com.example.courseproject.models.Statement;
import com.example.courseproject.services.StatementService;
import com.example.courseproject.services.StudentGroupService;
import com.example.courseproject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private final StudentService studentService;
    private final StudentGroupService studentGroupService;
    private final StatementService statementService;
@Autowired
    public AdminController(StudentService studentService, StudentGroupService studentGroupService, StatementService statementService) {
        this.studentService = studentService;
    this.studentGroupService = studentGroupService;
    this.statementService = statementService;
}

    @GetMapping("/student-delete")
    public String getStudentDeletePage(){
    return "studentDeletePage";
    }
//    @GetMapping("/student-send-message")
//    public String getStudentSendMessagePage(){
//    return "studentSendMessagePage";
//    }
    @GetMapping("/student-check-statements")
    public String getStudentCheckStatementsPage(Model model){
    List<Statement> statements = statementService.findAllStatements(); //all statements
        System.out.println("all"+statements.size());
        List<Statement> unprintedStats=new ArrayList<>();
        for (Statement statement:statements) {
            if(statement.getIsPrinted()==0)
                unprintedStats.add(statement);
        }
        System.out.println("unprinted"+unprintedStats.size());
        if(unprintedStats.size()==0) unprintedStats=null;
        model.addAttribute("stats",unprintedStats);
    return "studentCheckStatementsPage";
    }

    @GetMapping("/control-teachers")
    public String getControlTeachersPage(){
    return "controlTeachersPage";
    }
    @GetMapping("/students-geography")
    public String getStudentGeographyPage(Model model){
    String[] countries={"Россия","Казахстан", "Узбекистан", "Китай", "Другое"};
    int array[]=studentService.countStudentsByCountries(countries);
        System.out.println("Количество по странам");
    for(int i=0;i<5;i++){
        System.out.println(array[i]);
    }
    model.addAttribute("country1", array[0]);
    model.addAttribute("country2", array[1]);
    model.addAttribute("country3", array[2]);
    model.addAttribute("country4", array[3]);
    model.addAttribute("country5", array[4]);
    return "studentGeographyPage";
    }
    @PostMapping("/student-check-statements")
    public String CheckStatements(@RequestParam(name="id") Long id){
    //Long id=Long.parseLong(id_stat);
    Statement statement=statementService.findStatementById(id);
    byte a=1;
    statement.setIsPrinted(a);
    statementService.saveOrUpdateStatement(statement);
    return "redirect:/student-check-statements";
    }

    @GetMapping("/faculties-popularity")
    public String getFacultiesPopularityPage(Model model){
        String[] faculties={"ИЭФ","ФИБ", "КСИС", "ФРЭ", "ФИТУ"};
        int array[]=studentGroupService.countStudentsByFaculties(faculties);
        System.out.println("Количество по факультетам");
        for(int i=0;i<5;i++){
            System.out.println(array[i]);
        }
        model.addAttribute("fac1", array[0]);
        model.addAttribute("fac2", array[1]);
        model.addAttribute("fac3", array[2]);
        model.addAttribute("fac4", array[3]);
        model.addAttribute("fac5", array[4]);
        return "facultiesPopularityPage";
    }
}
