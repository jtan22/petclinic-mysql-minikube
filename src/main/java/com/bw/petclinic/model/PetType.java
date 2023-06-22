package com.bw.petclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_types")
public class PetType extends NamedEntity {

	@Override
	public String toString() {
		return getName();
	}
}
