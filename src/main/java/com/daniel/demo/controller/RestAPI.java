package com.daniel.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.demo.Model.Alien;
import com.daniel.demo.dao.AlienJPARepo;
import com.daniel.demo.dao.RequestResponse;

/*
 * Avoid to use @ResponseBody Annotation and specify a rest API
 * */
@RestController
public class RestAPI {
	
	@Autowired
	AlienJPARepo repo;
	
	@GetMapping("/aliens")
	public List<Alien> getAliens() {
		return repo.findAll();
	}
	//@GetMapping(path="/alien/{aid}", produces= {"application/xml"})
	@GetMapping(path="/alien/{aid}")
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
	
	/*
	 * use (@RequestBody Alien alien) in case you want to receive data in JSON format
	 * */
	@PostMapping(path="/alien")
	public Alien addAlien(Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@DeleteMapping("/alien/{aid}")
	public RequestResponse deleteAlien(@PathVariable int aid) {
		
		RequestResponse response = new RequestResponse();
		try {
			Alien alien = repo.getOne(aid);
			repo.delete(alien);
			response.setStatus("200");
			response.setMessage("Was deleted");
		}catch (Exception e) {
			response.setStatus("400");
			response.setMessage("Doesn't exists");
		}
		
		return response;
	}
	
}
