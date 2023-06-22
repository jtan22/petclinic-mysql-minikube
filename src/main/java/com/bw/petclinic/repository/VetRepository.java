package com.bw.petclinic.repository;

import com.bw.petclinic.model.Vet;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface VetRepository extends CrudRepository<Vet, Integer> {

	@NotNull
	Collection<Vet> findAll();

	Page<Vet> findAll(Pageable pageable);
}
