package com.example.courseproject.controllers;

import com.example.courseproject.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//    @GetMapping("/student-send-message")
//    public String getStudentSendMessagePage(){
//    return "studentSendMessagePage";
//    }
    @GetMapping("/student-check-statements")
    public String getStudentCheckStatementsPage(){
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

    @GetMapping("/faculties-popularity")
    public String getFacultiesPopularityPage(){
        return "facultiesPopularityPage";
    }
}
