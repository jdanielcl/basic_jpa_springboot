package com.daniel.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daniel.demo.Model.Alien;
import com.daniel.demo.dao.AlienJPARepo;

@Controller
public class RestAPI {
	
	@Autowired
	AlienJPARepo repo;
	
	@RequestMapping("/aliens")
	@ResponseBody
	public List<Alien> getAliens() {
		return repo.findAll();
	}
	//@RequestMapping(path="/alien/{aid}", produces= {"application/xml"})
	@RequestMapping(path="/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
}
