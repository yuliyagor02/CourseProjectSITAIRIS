package com.example.courseproject.services;

import com.example.courseproject.models.User;
import com.example.courseproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserService(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        userRepository=context.getBean(UserRepository.class);
    }
    public User saveUser(User user){
    return userRepository.save(user);
    }
    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
   public User findUserByLogin(String login){
   List<User> users=userRepository.findAll();
   for(int i=0;i<users.size();i++){
       if(users.get(i).getLogin().equals(login)) return users.get(i);
   }
   return null;
   }
}
