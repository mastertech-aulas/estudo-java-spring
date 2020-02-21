package br.com.mastertech.mashie.controllers;

import br.com.mastertech.mashie.models.Statement;
import br.com.mastertech.mashie.services.StatementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class StatementControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StatementService statementService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCreateStatement() throws Exception {
        Statement statement = new Statement();
        statement.setText("Eu sempre gosto da bagaça");

        String requestJson = mapper.writeValueAsString(statement);

        statement.setId(1);
        String responseJson = mapper.writeValueAsString(statement);

        Mockito.when(statementService.create(Mockito.any(Statement.class))).thenReturn(statement);

        RequestBuilder builder = MockMvcRequestBuilders
                .post("/statement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);
        mockMvc.perform(builder).andExpect(MockMvcResultMatchers.content().json(responseJson));
    }
}
