package com.bw.petclinic.controller;

import com.bw.petclinic.model.Owner;
import com.bw.petclinic.model.Pet;
import com.bw.petclinic.model.Visit;
import com.bw.petclinic.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}")
public class VisitController {

	private static final String VISIT_FORM = "views/visitForm";

	private static final Logger LOG = LoggerFactory.getLogger(VisitController.class);

	@Autowired
	private OwnerRepository ownerRepository;

	@ModelAttribute("owner")
	public Owner getOwner(@PathVariable("ownerId") Integer ownerId) {
		LOG.info("ModeAttribute owner");
		return ownerRepository.findById(ownerId).orElseGet(Owner::new);
	}

	@ModelAttribute("pet")
	public Pet getPet(Owner owner, @PathVariable("petId") Integer petId) {
		LOG.info("ModeAttribute pet");
		return owner.getPet(petId);
	}

	@GetMapping("/visits/new")
	public String getVisitNew(Model model) {
		LOG.info("GET /visits/new");
		model.addAttribute("visit", new Visit());
		return VISIT_FORM;
	}

	@PostMapping("/visits/new")
	public String postVisitNew(Owner owner, Pet pet, @Valid Visit visit, BindingResult bindingResult) {
		LOG.info("POST /visits/new");
		if (bindingResult.hasErrors()) {
			return VISIT_FORM;
		}
		owner.addVisit(pet.getId(), visit);
		ownerRepository.save(owner);
		return "redirect:/owners/{ownerId}";
	}

}
