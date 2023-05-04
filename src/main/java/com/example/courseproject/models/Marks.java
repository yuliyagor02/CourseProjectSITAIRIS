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
public class Marks {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_mark;
    private Date date_of_receipt; //день, когда была выставлена отметка
    private int mark;
    @ManyToOne (fetch=FetchType.LAZY)
    private Subject subject;
    @ManyToOne (fetch=FetchType.LAZY)
    private Student student;
}
