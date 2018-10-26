package com.lead.asianpaints.caleadapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lead.asianpaints.caleadapp.dao.interfaces.ProfileDao;
import com.lead.asianpaints.caleadapp.model.Profile;

@RestController
public class LeadController {
  
	@Autowired
	private ProfileDao profileDao;
	@PostMapping("/profiles")
	public ResponseEntity<Object> createLead(@RequestBody Profile profile)
	{
		int id =profileDao.createProfile(profile);
		return new ResponseEntity<>(id+" success",HttpStatus.CREATED);
		
	}
	@GetMapping("/profiles")
	public ResponseEntity<Object> getLead()
	{
		final List<Profile>profileList=profileDao.getProfiles();
		return new ResponseEntity<>(profileList, HttpStatus.ACCEPTED);
		
	}
	
}
