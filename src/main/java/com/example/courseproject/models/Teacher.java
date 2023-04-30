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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_teacher;
    private String position;
    @OneToMany(mappedBy = "teacher",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<TeacherSubject> teacher_subjects;
    @OneToOne
    private User user;
}
