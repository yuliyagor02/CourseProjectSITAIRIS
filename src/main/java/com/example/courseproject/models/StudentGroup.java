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
public class StudentGroup {
    @Id
    private Long id_group; //номер группы
    private String faculty;
    private String speciality;
    private String stage_of_education; //бакалавриат, магистратура...
    @OneToMany(mappedBy = "group",fetch=FetchType.LAZY)
    private List<Student> students;
    @OneToMany(mappedBy="student_group",fetch=FetchType.LAZY)
    private List<Subject> subjects;
}
