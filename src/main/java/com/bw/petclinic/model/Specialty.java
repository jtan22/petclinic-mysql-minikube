package com.bw.petclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity {

	@Override
	public String toString() {
		return getName();
	}
}
