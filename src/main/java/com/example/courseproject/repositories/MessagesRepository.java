package com.example.courseproject.repositories;

import com.example.courseproject.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Message,Long> {
}
