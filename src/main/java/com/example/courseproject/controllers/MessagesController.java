package com.example.courseproject.controllers;

import com.example.courseproject.models.Message;
import com.example.courseproject.models.Student;
import com.example.courseproject.services.MessagesService;
import com.example.courseproject.services.StudentService;
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
import java.util.Collections;
import java.util.List;

@Controller
public class MessagesController {
    private final MessagesService messagesService;
    private final StudentService studentService;
@Autowired
    public MessagesController(MessagesService messagesService,StudentService studentService) {
        this.messagesService = messagesService;
        this.studentService = studentService;
    }
    @GetMapping("/student-messages/{id}")
    public String getStudentMessagesPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("id",id);
        List<Message> messages = messagesService.findMessagesByStudentId(id);
        model.addAttribute("messages",messages);
        return "studentMessagesPage";
    }
    @PostMapping("/update-messages-status")
    public String sendUpdateMessagesStatusForm(@RequestParam(name="id") Long id,@RequestParam(name="id_mess") Long id_mess, Message message){
    Student student = studentService.findStudentById(id);
    Message message1=messagesService.findMessageById(id_mess);
    byte a =1;
    message1.setIsRead(a);
    messagesService.saveMessage(message1);
    return"redirect:/student-messages/"+Long.toString(student.getId_student());
    }
    @GetMapping("/student-send-message")
    public String getStudentSendMessagePage(Model model, Student student, Message message){
    List<Student> allStudents=studentService.findAll();
    model.addAttribute("allStudents",allStudents);
        return "studentSendMessagePage";
    }
//    @RequestParam(name="id_stud") Long id_stud,
    @PostMapping("/student-send-message")
    public String sendStudentSendMessageForm(@RequestParam(name="allStudents") Long id,Message message,Student student){
//    Student student1 = studentService.findStudentById(student.getId_student());
        Student student1 = studentService.findStudentById(id);
    List<Message> messagesOfStudent = messagesService.findMessagesByStudentId(student.getId_student());
    if(messagesOfStudent==null) messagesOfStudent=new ArrayList<>();
    byte a=0;
    message.setIsRead(a);
    Date actual_date = Date.valueOf(LocalDate.now());
    message.setSent_on(actual_date);
    message.setStudent(student1);
    messagesOfStudent.add(message);
    messagesService.saveMessage(message);
    student1.setMessages(messagesOfStudent);
        return "redirect:/student-send-message";
    }
}
