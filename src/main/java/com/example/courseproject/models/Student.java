package com.example.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private Long id_student; //номер студенческого
    private Date date_of_birth;
    private String passport_series;
    private String passport_number;
    private String home_country;
    private String home_city;
    private String temporary_registration_address;
    private byte is_health_insurance;
    private Date health_insurance_validity;
    private byte is_dormitory;
    private Date visa_validity;
    private Date temporary_registration_validity;
    private String skills;
    private String hobbies;
    @ManyToOne (fetch=FetchType.LAZY)
    private StudentGroup group;
    @OneToMany(mappedBy = "student", fetch=FetchType.LAZY)
    private List<Message> messages;
    @OneToMany(mappedBy = "student",  fetch=FetchType.LAZY)
    private List<Statement> statements;
    @OneToMany(mappedBy = "student",fetch=FetchType.LAZY)
    private List<Marks> marks;
    @OneToOne
    private User user;
}
