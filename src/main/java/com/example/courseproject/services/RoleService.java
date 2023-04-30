package com.example.courseproject.services;

import com.example.courseproject.models.Role;
import com.example.courseproject.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public RoleService(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        roleRepository=context.getBean(RoleRepository.class);
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }
    public Role findRoleById (Long id) { return roleRepository.findById(id).orElse(null); }
}
