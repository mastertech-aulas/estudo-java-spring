package br.com.mastertech.mashie.services;

import br.com.mastertech.mashie.data.models.Statement;
import br.com.mastertech.mashie.data.repositories.StatementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StatementServiceTest {
    @Autowired
    StatementService statementService;

    @MockBean
    StatementRepository statementRepository;

    @Test
    public void shouldCreateAStatement(){
        //given
        String text = "Prefiro comer banana";
        Statement statement = new Statement();
        statement.setText(text);

        List<Statement> statements = new ArrayList<>();
        statements.add(statement);

        when(statementRepository.saveAll(statements)).thenReturn(statements);

        //when
        List<Statement> savedStatements = statementService.saveAll(statements);

        //then
        assertNotNull(savedStatements);
        assertEquals(text, savedStatements.get(0).getText());
    }

    @Test
    public void shouldLoadStatementsById(){
        //given
        List<Long> ids = new ArrayList<>();

        ids.add(1L);
        ids.add(2L);

        Statement statement1 = new Statement();
        Statement statement2 = new Statement();

        List<Statement> foundStatements = new ArrayList<>();
        foundStatements.add(statement1);
        foundStatements.add(statement2);

        when(statementRepository.findAllById(ids)).thenReturn(foundStatements);

        //when
        List<Statement> statements = statementService.loadStatementsById(ids);

        //then
        assertEquals(2, statements.size());
    }
}
