package com.bw.petclinic.controller;

import com.bw.petclinic.model.PetType;
import com.bw.petclinic.repository.OwnerRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

	@Autowired
	private OwnerRepository ownerRepository;

	@Override
	@NotNull
	public PetType parse(@NotNull String text, @NotNull Locale locale) throws ParseException {
		Collection<PetType> petTypes = ownerRepository.findAllPetTypes();
		for (PetType pt : petTypes) {
			if (StringUtils.equals(pt.getName(), text)) {
				return pt;
			}
		}
		throw new ParseException("PetType not found [" + text + "]", 0);
	}

	@Override
	@NotNull
	public String print(PetType object, @NotNull Locale locale) {
		return object.getName();
	}
}
