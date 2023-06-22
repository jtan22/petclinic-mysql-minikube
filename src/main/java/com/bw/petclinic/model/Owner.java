package com.bw.petclinic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import org.thymeleaf.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "owners")
public class Owner extends Person {

	@NotEmpty
	private String address;

	@NotEmpty
	private String city;

	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id")
	@OrderBy("name")
	private Set<Pet> pets = new LinkedHashSet<>();

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}

	public void addPet(Pet pet) {
		pets.add(pet);
	}

	public Pet getPet(Integer petId) {
		for (Pet pet : pets) {
			if (pet.getId().equals(petId)) {
				return pet;
			}
		}
		return null;
	}

	public Pet getPet(String name) {
		for (Pet pet : pets) {
			if (StringUtils.equals(pet.getName(), name)) {
				return pet;
			}
		}
		return null;
	}

	public String getPetNames() {
		return pets.stream().map(Pet::getName).collect(Collectors.joining(", "));
	}

	public void addVisit(Integer petId, Visit visit) {
		getPet(petId).addVisit(visit);
	}

	@Override
	public String toString() {
		return "Owner{" + super.toString() +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", telephone='" + telephone + '\'' +
				", pets=" + pets +
				'}';
	}
}
