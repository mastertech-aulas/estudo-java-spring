package br.com.mastertech.mashie.services;

import br.com.mastertech.mashie.data.models.Statement;
import br.com.mastertech.mashie.data.repositories.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatementService {
    @Autowired
    private StatementRepository statementRepository;

    public List<Statement> saveAll(List<Statement> statements){
        Iterable<Statement> savedStatements = statementRepository.saveAll(statements);
        List<Statement> statementList = new ArrayList<>();

        savedStatements.forEach(statement -> {
            statementList.add(statement);
        });

        return statementList;
    }

    public List<Statement> loadStatementsById(Iterable<Long> ids){
        Iterable<Statement> statements = statementRepository.findAllById(ids);

        List<Statement> statementList = new ArrayList<>();

        for(Statement statement : statements){
            statementList.add(statement);
        }

        return statementList;
    }
}
