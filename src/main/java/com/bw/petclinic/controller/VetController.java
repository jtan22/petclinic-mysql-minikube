package com.bw.petclinic.controller;

import com.bw.petclinic.model.Vet;
import com.bw.petclinic.repository.VetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VetController {

	private static final String VET_LIST = "views/vetList";
	private static final int PAGE_SIZE = 5;

	private static final Logger LOG = LoggerFactory.getLogger(VetController.class);

	@Autowired
	private VetRepository vetRepository;

	@GetMapping("/vets/list")
	public String getVetList(@RequestParam(defaultValue = "1") int page, Model model) {
		LOG.info("GET /vets/list");
		Page<Vet> vets = vetRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE));
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", vets.getTotalPages());
		model.addAttribute("vetList", vets.getContent());
		return VET_LIST;
	}

}
