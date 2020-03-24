package br.com.mastertech.mashie.data.repositories;

import br.com.mastertech.mashie.data.models.Statement;
import org.springframework.data.repository.CrudRepository;

public interface StatementRepository extends CrudRepository<Statement, Long> {
}
