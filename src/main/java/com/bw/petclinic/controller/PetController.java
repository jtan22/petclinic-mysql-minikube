package com.bw.petclinic.controller;

import com.bw.petclinic.model.Owner;
import com.bw.petclinic.model.Pet;
import com.bw.petclinic.model.PetType;
import com.bw.petclinic.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

	private static final String PET_FORM = "views/petForm";

	private static final Logger LOG = LoggerFactory.getLogger(PetController.class);

	@Autowired
	private OwnerRepository ownerRepository;

	@ModelAttribute("petTypes")
	public Collection<PetType> getPetTypes() {
		LOG.info("ModelAttribute petType");
		return ownerRepository.findAllPetTypes();
	}

	@ModelAttribute("owner")
	public Owner getOwner(@PathVariable("ownerId") Integer ownerId) {
		LOG.info("ModelAttribute owner");
		return ownerRepository.findById(ownerId).orElseGet(Owner::new);
	}

	@ModelAttribute("pet")
	public Pet getPet(Owner owner, @PathVariable(value = "petId", required = false) Integer petId) {
		LOG.info("ModelAttribute pet");
		return petId == null ? new Pet() : owner.getPet(petId);
	}

	@InitBinder("pet")
	public void bindPet(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new PetValidator());
	}

	@GetMapping("/pets/new")
	public String getPetNew() {
		LOG.info("GET /pets/new");
		return PET_FORM;
	}

	@PostMapping("/pets/new")
	public String postPetNew(Owner owner, @Valid Pet pet, BindingResult bindingResult, Model model) {
		LOG.info("POST /pets/new");
		if (bindingResult.hasErrors()) {
			return PET_FORM;
		}
		owner.addPet(pet);
		ownerRepository.save(owner);
		return "redirect:/owners/{ownerId}";
	}

	@GetMapping("/pets/{petId}/edit")
	public String getPetEdit() {
		LOG.info("GET /pets/{petId}/edit");
		return PET_FORM;
	}

	@PostMapping("/pets/{petId}/edit")
	public String postPetEdit(Owner owner, @Valid Pet pet, BindingResult bindingResult) {
		LOG.info("POST /pets/{petId}/edit");
		if (bindingResult.hasErrors()) {
			return PET_FORM;
		}
		ownerRepository.save(owner);
		return "redirect:/owners/{ownerId}";
	}

}
