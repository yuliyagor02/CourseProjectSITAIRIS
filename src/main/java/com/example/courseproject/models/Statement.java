package com.example.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Statement {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_statement;
    private String academic_discipline;
    private String statement_type;
    private String teacher_fio;
    private String reason;
    private Date missing_class_date;
    private byte isPrinted; //напечатана/не напечатана
    @ManyToOne(fetch=FetchType.LAZY)
    private Student student;
}
