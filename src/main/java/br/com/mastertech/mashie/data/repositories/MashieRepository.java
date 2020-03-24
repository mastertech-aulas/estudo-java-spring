package br.com.mastertech.mashie.data.repositories;

import br.com.mastertech.mashie.data.models.Mashie;
import org.springframework.data.repository.CrudRepository;

public interface MashieRepository extends CrudRepository<Mashie, String> {
}
