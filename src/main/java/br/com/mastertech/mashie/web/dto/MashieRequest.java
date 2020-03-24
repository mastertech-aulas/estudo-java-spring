package br.com.mastertech.mashie.web.dto;

import br.com.mastertech.mashie.data.models.Mashie;
import br.com.mastertech.mashie.data.models.Statement;

import java.util.ArrayList;
import java.util.List;

public class MashieRequest {
    private String id;
    private List<StatementRequest> statements = new ArrayList<>();
    private String description;
    private boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<StatementRequest> getStatements() {
        return statements;
    }

    public void setStatements(List<StatementRequest> statements) {
        this.statements = statements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Mashie toEntity() {
        Mashie mashie = new Mashie();

        mashie.setId(id);
        mashie.setDescription(description);
        mashie.setActive(isActive);
        mashie.setStatements(new ArrayList<>());

        statements.forEach(statement -> {
            mashie.getStatements().add(statement.toEntity());
        });

        return mashie;
    }

    public void addStatement(String text){
        StatementRequest statementRequest = new MashieRequest.StatementRequest();
        statementRequest.setText(text);

        statements.add(statementRequest);
    }

    public static class StatementRequest {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Statement toEntity(){
            Statement statement = new Statement();
            statement.setText(text);

            return statement;
        }
    }
}
