package com.example.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdminSubject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_admin_subject;
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Admin admin;
    @ManyToOne (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Subject subject;
}
