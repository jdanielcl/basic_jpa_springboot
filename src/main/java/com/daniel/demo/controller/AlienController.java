package com.daniel.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.daniel.demo.Model.Alien;
import com.daniel.demo.dao.AlienRepo;

@Controller
public class AlienController {
	/*
	 * To access to the database console you could use the following url
	 * http://localhost:8080/h2-console/
	 * */
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien){
		repo.save(alien);
		return "";
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam("aid") int aid){
		ModelAndView mv = new ModelAndView();
		Alien alien = repo.findById(aid).orElse(new Alien());
		mv.addObject("alien", alien);
		mv.setViewName("showAlien.jsp");
		return mv;
	}
	
	/*
	 * test url :
	 * /updateAlien?aid=103&aname=Felipe&tech=Java
	 * */
	@RequestMapping("/updateAlien")
	public ModelAndView updateAlien(Alien alien){
		ModelAndView mv = new ModelAndView();
		repo.save(alien);
		mv.addObject("alien", alien);
		mv.setViewName("showAlien.jsp");
		return mv;
	}
	
	@RequestMapping("/getAliens")
	public ModelAndView getAliens(){
		ArrayList<Alien> aliens = (ArrayList<Alien>)repo.findAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("aliens", aliens);
		mv.setViewName("aliens.jsp");
		return mv;
	}
	/*
	 * test url:
	 * /getAliensByTech?tech=Javascript
	 * */
	@RequestMapping("/getAliensByTech")
	public ModelAndView getAliensByTech(@RequestParam("tech") String tech){
		ArrayList<Alien> aliens = (ArrayList<Alien>)repo.findByTech(tech);
		ModelAndView mv = new ModelAndView();
		mv.addObject("aliens", aliens);
		mv.setViewName("aliens.jsp");
		return mv;
	}
	
	/*
	 * test url:
	 * /getAliensByAidGreaterThan?aid=102
	 * */
	@RequestMapping("/getAliensByAidGreaterThan")
	public ModelAndView getAliensByAidGreaterThan(@RequestParam("aid") int aid){
		ArrayList<Alien> aliens = (ArrayList<Alien>)repo.findByAidGreaterThan(aid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("aliens", aliens);
		mv.setViewName("aliens.jsp");
		return mv;
	}
	
	/*
	 * test url:
	 * /getAliensByTechSorted?tech=Python
	 * */
	@RequestMapping("/getAliensByTechSorted")
	public ModelAndView getAliensByTechSorted(@RequestParam("tech") String tech){
		ArrayList<Alien> aliens = (ArrayList<Alien>)repo.findByTechSorted(tech);
		ModelAndView mv = new ModelAndView();
		mv.addObject("aliens", aliens);
		mv.setViewName("aliens.jsp");
		return mv;
	}
	
}
