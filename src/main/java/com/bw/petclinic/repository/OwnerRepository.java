package com.bw.petclinic.repository;

import com.bw.petclinic.model.Owner;
import com.bw.petclinic.model.PetType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {

	@NotNull
	Collection<Owner> findAll();

	@Query("select owner from Owner owner where owner.lastName like :lastName%")
	Page<Owner> findByLastName(String lastName, Pageable pageable);

	@Query("select petType from PetType petType order by petType.name")
	Collection<PetType> findAllPetTypes();
}
