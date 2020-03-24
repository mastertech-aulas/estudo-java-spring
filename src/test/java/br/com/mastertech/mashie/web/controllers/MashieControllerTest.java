package br.com.mastertech.mashie.web.controllers;

import br.com.mastertech.mashie.data.models.Mashie;
import br.com.mastertech.mashie.services.MashieService;
import br.com.mastertech.mashie.web.dto.MashieRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MashieControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MashieService mashieService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCreateAMashie() throws Exception {
        String mashieId = "mashieId";
        String description = "A description";
        boolean isActive = true;
        String statementText = "A statement";

        MashieRequest mashieRequest = new MashieRequest();
        mashieRequest.setId(mashieId);
        mashieRequest.setDescription(description);
        mashieRequest.setActive(isActive);
        mashieRequest.addStatement(statementText);

        String requestJson = mapper.writeValueAsString(mashieRequest);

        Mockito.when(mashieService.create(Mockito.any(Mashie.class))).thenReturn(mashieRequest.toEntity());

        RequestBuilder builder = post("/mashie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        mockMvc.perform(builder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(mashieId))
                .andExpect(jsonPath("active").value(isActive))
                .andExpect(jsonPath("description").value(description))
                .andExpect(jsonPath("statements[0].text").value(statementText));
    }
}
