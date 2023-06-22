package com.bw.petclinic.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedEntity extends BaseEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NamedEntity{" + super.toString() +
				", name='" + name + '\'' +
				'}';
	}
}
