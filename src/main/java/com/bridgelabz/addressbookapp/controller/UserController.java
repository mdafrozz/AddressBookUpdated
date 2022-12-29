package com.bridgelabz.addressbookapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.dto.UserDTO;
import com.bridgelabz.addressbookapp.model.UserModel;
import com.bridgelabz.addressbookapp.service.IUserService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService iservice;
	 
	//Register new user
    @PostMapping(value = {"/register"})
    public ResponseEntity<ResponseDTO> AddUser(@Valid @RequestBody UserDTO userdto){
    	String response = iservice.addUser(userdto);
        ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully!!!",response);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }
    
 // Login check
 	@PostMapping("/login/{email}/{password}")
 	public ResponseEntity<ResponseDTO> loginUser(@PathVariable String email, @PathVariable String password) {
 		UserModel response = iservice.loginUser(email,password);
 		ResponseDTO responseDTO = new ResponseDTO("Login Status:", response);
 		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
 	}
 	
 // Get User Data by ID
 	@GetMapping("/getbyid/{id}")
 	public ResponseEntity<ResponseDTO> getUserById(@PathVariable int id) {
 		UserModel userModel = iservice.getUserDataById(id);
 		ResponseDTO responseDTO = new ResponseDTO("User Details with ID: " + id, userModel);
 		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
 	}

}
