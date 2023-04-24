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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne (cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    private StudentGroup group;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Message> messages;
    @OneToMany(mappedBy = "student", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Statement> statements;
    @OneToMany(mappedBy = "student",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Marks> marks;
    @OneToOne
//    @MapsId
//    @JoinColumn(name="id_student")
    private User user;
}
