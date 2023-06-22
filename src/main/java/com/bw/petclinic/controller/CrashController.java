package com.bw.petclinic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrashController {

	private static final Logger LOG = LoggerFactory.getLogger(CrashController.class);

	@GetMapping("/oops")
	public String error() {
		LOG.info("GET /oops");
		throw new RuntimeException("Expected Exception");
	}
}
