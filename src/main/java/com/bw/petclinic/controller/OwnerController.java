package com.bw.petclinic.controller;

import com.bw.petclinic.model.Owner;
import com.bw.petclinic.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class OwnerController {

	private static final String OWNER_FIND = "views/ownerFind";
	private static final String OWNER_LIST = "views/ownerList";
	private static final String OWNER_DETAILS = "views/ownerDetails";
	private static final String OWNER_FORM = "views/ownerForm";
	private static final int PAGE_SIZE = 5;

	private static final Logger LOG = LoggerFactory.getLogger(OwnerController.class);

	@Autowired
	private OwnerRepository ownerRepository;

	@ModelAttribute("owner")
	public Owner getOwnerAttribute(@PathVariable(value = "ownerId", required = false) Integer ownerId) {
		LOG.info("ModelAttribute owner");
		return ownerId == null ? new Owner() : ownerRepository.findById(ownerId).orElseGet(Owner::new);
	}

	@GetMapping("/owners/find")
	public String getOwnersFind() {
		LOG.info("GET /owners/find");
		return OWNER_FIND;
	}

	@GetMapping("/owners")
	public String getOwners(@RequestParam(value = "page", defaultValue = "1") int page,
							Owner owner, BindingResult bindingResult, Model model) {
		LOG.info("GET /owners");
		if (StringUtils.isBlank(owner.getLastName())) {
			owner.setLastName("");
		}
		Page<Owner> owners = ownerRepository.findByLastName(owner.getLastName(), PageRequest.of(page - 1, PAGE_SIZE));
		if (owners.isEmpty()) {
			bindingResult.rejectValue("lastName", "notFound", "Not Found");
			return OWNER_FIND;
		}
		if (owners.getTotalElements() == 1) {
			return "redirect:/owners/" + owners.iterator().next().getId();
		}
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", owners.getTotalPages());
		model.addAttribute("ownerList", owners.getContent());
		return OWNER_LIST;
	}

	@GetMapping("/owners/{ownerId}")
	public String getOwnerDetails() {
		LOG.info("GET /owners/{ownerId}");
		return OWNER_DETAILS;
	}

	@GetMapping("/owners/{ownerId}/edit")
	public String getOwnerEdit() {
		LOG.info("GET /owners/{ownerId}/edit");
		return OWNER_FORM;
	}

	@PostMapping("/owners/{ownerId}/edit")
	public String postOwnerEdit(@Valid Owner owner, BindingResult bindingResult) {
		LOG.info("POST /owners/{ownerId}/edit");
		if (bindingResult.hasErrors()) {
			return OWNER_FORM;
		}
		ownerRepository.save(owner);
		return "redirect:/owners/{ownerId}";
	}

	@GetMapping("/owners/new")
	public String getOwnerNew(Model model) {
		LOG.info("GET /owners/new");
		model.addAttribute("owner", new Owner());
		return OWNER_FORM;
	}

	@PostMapping("/owners/new")
	public String postOwnerNew(@Valid Owner owner, BindingResult bindingResult) {
		LOG.info("POST /owners/new");
		if (bindingResult.hasErrors()) {
			return OWNER_FORM;
		}
		ownerRepository.save(owner);
		return "redirect:/owners/" + owner.getId();
	}

}
