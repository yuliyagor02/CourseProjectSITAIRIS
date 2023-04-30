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
public class Subject {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_subject; //код предмета
    private String subject_name;
    private int study_hours;
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private StudentGroup student_group;
    @OneToMany(mappedBy = "subject",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Marks> marks;
    @OneToMany(mappedBy = "subject",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<TeacherSubject> teacher_subjects;
}
