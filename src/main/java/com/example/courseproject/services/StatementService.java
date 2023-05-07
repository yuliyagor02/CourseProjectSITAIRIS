package com.example.courseproject.services;

import com.example.courseproject.models.Statement;
import com.example.courseproject.repositories.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Service
public class StatementService {
    private final StatementRepository statementRepository;
    private static final String BASE_PACKAGE="com.example.courseproject";
@Autowired
    public StatementService(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }
    public Statement findStatementById(Long id){
    return statementRepository.findById(id).orElse(null);
    }
    public Statement saveOrUpdateStatement(Statement statement){
    return statementRepository.save(statement);
    }
    public List<Statement> findAllStatements(){
    return statementRepository.findAll();
    }
}

