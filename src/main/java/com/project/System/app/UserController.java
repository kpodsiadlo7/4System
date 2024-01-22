package com.project.System.app;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import jakarta.servlet.http.HttpServletRequest;

@Controller
class UserController {
	
	private final UserService userService;
	
	UserController(final UserService service)
	{
		this.userService = service;
	}
	
	@GetMapping
	String getHome()
	{
		return "home";
	}
	
	@GetMapping("/display")
	String sortUsers(Model model,
	                        @RequestParam(defaultValue = "0") int page,
	                        @RequestParam(defaultValue = "10") int size,
	                        @RequestParam(required = false, name = "searchTerm") String searchTerm,
	                        @RequestParam(defaultValue = "name") String sortField,
	                        @RequestParam(defaultValue = "asc") String sortDirection) {
		return userService.sortData(model,page,size,searchTerm,sortField,sortDirection);
	}

	
	@GetMapping("/upload")
	String getUpload()
	{
		return "upload";
	}
	
	
	@PostMapping("/upload")
	String handleJson(@RequestPart(name = "file", required = false) List<UserDto> jsonXMLData, Model model, HttpServletRequest request) {
	    return userService.validateBeforeSave(jsonXMLData,model,request);
	}
}
