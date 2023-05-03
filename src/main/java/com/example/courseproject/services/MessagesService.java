package com.example.courseproject.services;

import com.example.courseproject.models.Message;
import com.example.courseproject.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {
    private final MessagesRepository messagesRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
@Autowired
    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }
    public MessagesService(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        messagesRepository=context.getBean(MessagesRepository.class);
    }
    public Message findMessageById(Long id){
    return messagesRepository.findById(id).orElse(null);
    }
    public Message saveMessage(Message message){
    return messagesRepository.save(message);
    }
    public List<Message>  findMessagesByStudentId(Long id){
//        System.out.println("привеееет");
//        System.out.println("id студента: "+id);
    List<Message> messages = messagesRepository.findAll();
        System.out.println("всего сообщений в бд: "+messages.size());
    List<Message> messagesOfStudent=new ArrayList<>();
    int flag=0;
    for(Message message:messages){
       // System.out.println("id студента в форе в списке: "+message.getStudent().getId_student());
        if(message.getStudent().getId_student().equals(id)){
            messagesOfStudent.add(message);
            flag++;
           // System.out.println("бай");
        }
    }
    if(flag==0) messagesOfStudent=null;
       // System.out.println("сообщений для данного пользователя: "+messagesOfStudent.size());
    return messagesOfStudent;
    }
}
