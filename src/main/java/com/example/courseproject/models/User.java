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
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<UserRole> user_roles;

//    @OneToOne(cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Student student;
     @OneToOne(mappedBy = "user")
//     @PrimaryKeyJoinColumn
     private Admin admin;

     @OneToOne(mappedBy = "user")
//     @PrimaryKeyJoinColumn
     private Student student;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="student_id",referencedColumnName = "id_student")
//    private Student student;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="admin_id",referencedColumnName = "id_admin")
//    private Admin admin;
    //@PrimaryKeyJoinColumn
    //private Admin admin;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Student student;
//    @OneToOne(cascade = CascadeType.ALL)
//    private Admin admin;
}
