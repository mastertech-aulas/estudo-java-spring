package br.com.mastertech.mashie.services;

import br.com.mastertech.mashie.models.Mashie;
import br.com.mastertech.mashie.models.Statement;
import br.com.mastertech.mashie.repositories.MashieRepository;
import br.com.mastertech.mashie.repositories.StatementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MashieServiceTest {
    @Autowired
    MashieService mashieService;

    @MockBean
    MashieRepository mashieRepository;

    @MockBean
    StatementService statementService;

    Mashie mashie;
    List<Statement> statements;

    @BeforeEach
    public void prepare(){
        Statement statement = new Statement();
        statement.setId(1);
        statement.setText("Prefiro ver o filme do Pelé");

        statements = new ArrayList<>();
        statements.add(statement);

        mashie = new Mashie();
        mashie.setId("citi");
        mashie.setStatements(statements);
    }

    @Test
    public void shouldCreateMashie(){
        //given
        Mockito.when(statementService.loadStatementsById(Mockito.anyList()))
                .thenReturn(statements);
        Mockito.when(mashieRepository.save(mashie)).thenReturn(mashie);

        //when
        Mashie createdMashie = mashieService.create(mashie);

        //then
        Assertions.assertNotNull(createdMashie);
        Assertions.assertEquals(1, mashie.getStatements().size());
    }

    @Test
    public void shouldLoadMashieById(){
        //given
        Mockito.when(mashieService.load(mashie.getId())).thenReturn(Optional.of(mashie));

        //when
        Optional<Mashie> mashieOptional = mashieService.load(mashie.getId());

        //then
        Assertions.assertTrue(mashieOptional.isPresent());
        Assertions.assertEquals(mashie, mashieOptional.get());
    }
}
