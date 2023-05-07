package com.example.courseproject.services;

import com.example.courseproject.models.UserRole;
import com.example.courseproject.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
@Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
    public UserRoleService(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        userRoleRepository=context.getBean(UserRoleRepository.class);
    }
    public UserRole saveUserRole(UserRole userRole){
        return userRoleRepository.save(userRole);
    }
    public UserRole findUserRoleById(Long id){
        return userRoleRepository.findById(id).orElse(null);
    }
    public UserRole findUserRoleByUserId(Long id){
        List<UserRole> userRoles = userRoleRepository.findAll();
        for(UserRole userRole:userRoles){
            if(userRole.getUser().getId_user()==id) return userRole;
        }
        return null;
    }
    public void deleteUserRole(UserRole userRole){
    userRoleRepository.delete(userRole);
    }
}
