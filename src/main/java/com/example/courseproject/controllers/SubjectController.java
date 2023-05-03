package com.example.courseproject.controllers;

import com.example.courseproject.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SubjectController {
    private final SubjectService subjectService;
@Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
}
