package br.com.mastertech.mashie.web.dto;

import br.com.mastertech.mashie.data.models.Mashie;
import br.com.mastertech.mashie.data.models.Statement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MashieRequestTest {

    @Test
    public void shouldAddNewStatement(){
        String statementText = "A statement";

        MashieRequest mashieRequest = new MashieRequest();
        mashieRequest.addStatement(statementText);

        assertEquals(1, mashieRequest.getStatements().size());
        assertEquals(statementText, mashieRequest.getStatements().get(0).getText());
    }

    @Test
    public void shouldConvertToEntity(){
        String statementText = "A statement";

        MashieRequest mashieRequest = new MashieRequest();
        mashieRequest.setActive(true);
        mashieRequest.setDescription("A description");
        mashieRequest.setId("mashieId");
        mashieRequest.setStatements(new ArrayList<>());

        mashieRequest.addStatement(statementText);

        Mashie mashie = mashieRequest.toEntity();
        Statement statement = mashie.getStatements().get(0);

        assertEquals(mashieRequest.getId(), mashie.getId());
        assertEquals(mashieRequest.getDescription(), mashie.getDescription());
        assertEquals(mashieRequest.isActive(), mashie.isActive());
        assertEquals(mashieRequest.getStatements().size(), mashie.getStatements().size());

        assertEquals(statementText, statement.getText());
    }
}
