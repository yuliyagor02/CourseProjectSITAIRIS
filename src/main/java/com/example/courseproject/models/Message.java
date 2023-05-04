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
public class Message {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_message;
    private String theme;
    private String message_content;
    private Date sent_on; //дата отправки сообщения
    private byte isRead; //статус (просмотрено/не просмотрено)
    @ManyToOne(fetch=FetchType.LAZY)
    private Student student;
}
