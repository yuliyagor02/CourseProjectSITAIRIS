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
}
