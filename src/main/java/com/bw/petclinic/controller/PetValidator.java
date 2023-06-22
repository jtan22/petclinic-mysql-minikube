package com.bw.petclinic.controller;

import com.bw.petclinic.model.Pet;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PetValidator implements Validator {

	@Override
	public boolean supports(@NotNull Class<?> clazz) {
		return Pet.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(@NotNull Object object, @NotNull Errors errors) {
		Pet pet = (Pet) object;
		if (StringUtils.isBlank(pet.getName())) {
			errors.rejectValue("name", "required", "Required");
		}
		if (pet.getBirthDate() == null) {
			errors.rejectValue("birthDate", "required", "Required");
		}
	}
}
