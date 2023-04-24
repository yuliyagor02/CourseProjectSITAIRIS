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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_admin;
    private String position;
    @OneToMany(mappedBy = "admin",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<AdminSubject> admin_subjects;
    @OneToOne
//    @MapsId
//    @JoinColumn(name="id_admin")
    private User user;
}
