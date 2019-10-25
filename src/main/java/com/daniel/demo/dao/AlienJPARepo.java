package com.daniel.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.demo.Model.Alien;

public interface AlienJPARepo extends JpaRepository<Alien, Integer>{

}
