package br.com.mastertech.mashie.repositories;

import br.com.mastertech.mashie.models.Statement;
import org.springframework.data.repository.CrudRepository;

public interface StatementRepository extends CrudRepository<Statement, Long> {
}
