package br.com.mastertech.mashie.services;

import br.com.mastertech.mashie.data.models.Mashie;
import br.com.mastertech.mashie.data.models.Statement;
import br.com.mastertech.mashie.data.repositories.MashieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MashieService {
    @Autowired
    private MashieRepository mashieRepository;

    @Autowired
    private StatementService statementService;

    public Mashie create(Mashie mashie){
        List<Statement> savedStatements = statementService.saveAll(mashie.getStatements());

        mashie.setStatements(savedStatements);

        return mashieRepository.save(mashie);
    }

    public Optional<Mashie> load(String id){
        return mashieRepository.findById(id);
    }
}
