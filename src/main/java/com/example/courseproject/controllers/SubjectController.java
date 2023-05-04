package com.example.courseproject.controllers;

import com.example.courseproject.models.StudentGroup;
import com.example.courseproject.models.Subject;
import com.example.courseproject.services.StudentGroupService;
import com.example.courseproject.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SubjectController {
    private final SubjectService subjectService;
    private final StudentGroupService studentGroupService;
@Autowired
    public SubjectController(SubjectService subjectService,StudentGroupService studentGroupService) {
        this.subjectService = subjectService;
        this.studentGroupService=studentGroupService;
    }
    @GetMapping("/create-subject/{id_group}")
    public String getCreateSubjectPage(@PathVariable("id_group")String id,StudentGroup studentGroup, Subject subject){

    return "createSubjectPage";
    }
    @PostMapping("/create-subject")
//    @RequestParam(name="id_gr") String id_group
    public String sendCreateSubjectForm(StudentGroup studentGroup,Subject subject,Model model){
        //model.addAttribute("id_gr",id_group);
       // Long id = Long.parseLong(id_group);
        StudentGroup studentGroup1= studentGroupService.findStudentGroupById(studentGroup.getId_group());
        subject.setStudent_group(studentGroup1);
        subjectService.saveOrUpdateSubject(subject);
        return "redirect:/control-group-subjects/"+Long.toString(studentGroup.getId_group());
    }
    @GetMapping("/control-group-subjects/{id}")
    public String getControlGroupSubjectsPage(@PathVariable("id")String id, Model model){
//    StudentGroup studentGroup1 = studentGroupService.findStudentGroupById();
        System.out.println(" группа "+ id);
        Long id_group = Long.parseLong(id);

        StudentGroup studentGroup = studentGroupService.findStudentGroupById(id_group);
    List<Subject> subjects = subjectService.findSubjectsByGroup(studentGroup);
    model.addAttribute("subjects",subjects);
    //String id_group1=Long.toString(id_group);
    model.addAttribute("id_group",id_group);
    return "controlGroupSubjectsPage";
    }
    @GetMapping("/find-group-subjects")
    public String getFindGroupSubjectsPage(Model model){
    model.addAttribute("id_group",new String());
    return "findGroupSubjectsPage";
    }
    @GetMapping("/delete-subject/{id}")
    public String deleteSubjectById(@PathVariable("id") Long id){
    Subject subject=subjectService.findSubjectById(id);
    Long id_group = subject.getStudent_group().getId_group();
    subjectService.deleteSubjectById(id);
    return "redirect:/control-group-subjects/"+Long.toString(id_group);
    }
    @PostMapping("/find-group-subjects")
    public String sendFindGroupSubjectsForm(@RequestParam(name="id_group") String id_group,Model model){
    Long id = Long.parseLong(id_group);
        System.out.println("айди группы "+ id_group);
    StudentGroup studentGroup = studentGroupService.findStudentGroupById(id);
    return "redirect:/control-group-subjects/"+id_group;
    }
}
