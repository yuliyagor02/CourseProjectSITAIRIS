package com.example.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TeacherSubject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_teacher_subject;
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Teacher teacher;
    @ManyToOne (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Subject subject;
}
