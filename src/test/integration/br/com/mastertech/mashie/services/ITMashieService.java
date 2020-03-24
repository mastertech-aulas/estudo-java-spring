package br.com.mastertech.mashie.services;

import br.com.mastertech.mashie.data.models.Mashie;
import br.com.mastertech.mashie.data.models.Statement;
import br.com.mastertech.mashie.data.repositories.StatementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class ITMashieService {
    @Autowired
    MashieService mashieService;

    @Autowired
    StatementRepository statementRepository;

    List<Statement> statements;

    @BeforeEach
    public void prepare(){
        Statement statement = new Statement();
        statement.setText("Prefiro ver o filme do Pelé");
        statementRepository.save(statement);

        statements = new ArrayList<>();
        statements.add(statement);
    }

    @Test
    public void shouldCreateMashie(){
        //given
        Mashie mashie = new Mashie();
        mashie.setId("citi");
        mashie.setDescription("description");
        mashie.setStatements(statements);

        //when
        Mashie createdMashie = mashieService.create(mashie);

        //then
        Assertions.assertNotNull(createdMashie);
        Assertions.assertEquals(1, mashie.getStatements().size());
    }
}
