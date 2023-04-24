package com.example.courseproject.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRole {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_user_role;
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private User user;
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Role role;
}
