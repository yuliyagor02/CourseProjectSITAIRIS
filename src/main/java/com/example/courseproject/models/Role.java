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
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_role;
    private String role_name;

    @OneToMany(mappedBy = "role",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<UserRole> user_roles;
}
