package br.com.mastertech.mashie.services;

import br.com.mastertech.mashie.models.Mashie;
import br.com.mastertech.mashie.models.Statement;
import br.com.mastertech.mashie.repositories.MashieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MashieService {
    @Autowired
    private MashieRepository mashieRepository;

    @Autowired
    private StatementService statementService;

    public Mashie create(Mashie mashie){
        List<Long> ids = new ArrayList<>();

        for(Statement statement : mashie.getStatements()){
            ids.add(statement.getId());
        }

        mashie.setStatements(statementService.loadStatementsById(ids));

        return mashieRepository.save(mashie);
    }

    public Optional<Mashie> load(String id){
        return mashieRepository.findById(id);
    }
}
