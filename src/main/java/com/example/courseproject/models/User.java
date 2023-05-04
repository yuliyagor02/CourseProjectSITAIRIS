package com.example.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String surname;
    private String name;
    private String patronymic;
    private String phone_number;
    private String email;
    private String login;
    private String password;
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<UserRole> user_roles;
     @OneToOne(mappedBy = "user")
     private Teacher teacher;
     @OneToOne(mappedBy = "user")
     private Student student;
}
