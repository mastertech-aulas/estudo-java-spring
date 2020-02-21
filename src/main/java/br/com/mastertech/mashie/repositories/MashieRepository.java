package br.com.mastertech.mashie.repositories;

import br.com.mastertech.mashie.models.Mashie;
import org.springframework.data.repository.CrudRepository;

public interface MashieRepository extends CrudRepository<Mashie, String> {
}
