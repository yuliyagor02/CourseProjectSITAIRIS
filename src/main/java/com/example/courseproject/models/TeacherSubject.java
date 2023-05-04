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
    @ManyToOne(fetch=FetchType.LAZY)
    private Teacher teacher;
    @ManyToOne (fetch=FetchType.LAZY)
    private Subject subject;
}
