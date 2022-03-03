package com.Projects.MedStore.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WelcomeController {
    @GetMapping("/login")    
	public ModelAndView showView()  {    
	ModelAndView modelAndView = new ModelAndView();    
	modelAndView.setViewName("LoginOrRegistration");        
	return modelAndView;    
	}
	@GetMapping("/registrationSuccess")    
	public ModelAndView successPage()  {    
	ModelAndView modelAndView = new ModelAndView();    
	modelAndView.setViewName("RegistrationSuccess");        
	return modelAndView;    
	}

	@GetMapping("/resource")    
	public String getLoggedUser()  {    
		  
		Object principal =SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 

	return ((UserDetails) principal).getUsername();    
	}

    
}
