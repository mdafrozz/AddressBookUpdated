package com.bridgelabz.addressbookapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.model.AddressBookModel;
import com.bridgelabz.addressbookapp.service.IAddressBookService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

	@Autowired
	IAddressBookService iservice;
    
	@GetMapping(value = {"/get"})
    public ResponseEntity<ResponseDTO> getmsg() {
		String data = iservice.getData();
		ResponseDTO responseDTO = new ResponseDTO("GET Call Success..!!!",data);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
	
	@GetMapping(value = {"/getbyid/{token}"}) 
	public ResponseEntity<ResponseDTO> getById(@PathVariable String token) { 
	  AddressBookModel data = iservice.findById(token); 
	  ResponseDTO responseDTO = new ResponseDTO("GET Call for ID Successfull!!",data); 
	  return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);}
	 
    
    @GetMapping(value = {"/getbyemail/{email}"})
    public ResponseEntity<ResponseDTO> getByEmail(@PathVariable String email) {
       AddressBookModel DataList = iservice.getByEmail(email);
       ResponseDTO responseDTO = new ResponseDTO("Success call for email..!!", DataList);
       return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    
    @GetMapping("/getall")
    public List<AddressBookModel> showAll()throws AddressBookException {
        List<AddressBookModel> response = iservice.getAll();
        return response;
    }
    
    @PostMapping(value = {"/add"})
    public ResponseEntity<ResponseDTO> AddContact(@Valid @RequestBody AddressBookDTO addressBookDTO){
        String token = iservice.addContact(addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Contact Saved Successfully!!!",token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = {"/edit/{token}"})
    public ResponseEntity<ResponseDTO> EditContact(@Valid @RequestBody AddressBookDTO addressBookDTO,
    		@PathVariable String token){
    	String Token = iservice.editContact(token,addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Contact Updated Successfully!!!",Token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = {"/delete/{token}"})
    public ResponseEntity<ResponseDTO> DeleteContact(@PathVariable String token) {
    	 String Token = iservice.deleteContact(token);
        ResponseDTO responseDTO = new ResponseDTO("Contact Deleted Successfully!!!",
                Token + " DELETED!!!");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
    }
    
 // Sorting in Ascending order by city
 	@GetMapping("/sortbycity")
 	public ResponseEntity<ResponseDTO> sortCity() {
 		List<AddressBookModel> city = iservice.sortCity();
 		ResponseDTO responseDTO = new ResponseDTO("Sorted in Ascending order", city);
 		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
 	}
 	
 // Sorting in Ascending order by state
 	@GetMapping("/sortbystate")
 	public ResponseEntity<ResponseDTO> sortState() {
 		List<AddressBookModel> state = iservice.sortState();
 		ResponseDTO responseDTO = new ResponseDTO("Sorted in Ascending order", state);
 		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
 	}
 	

 	 // Search by name
 	 	@GetMapping("/searchby/{name}")
 	 	public ResponseEntity<ResponseDTO> SearchByName(@PathVariable String name) {
 	 		List<AddressBookModel> person = iservice.searchbyName(name);
 	 		ResponseDTO responseDTO = new ResponseDTO("Number of persons: " + person.size(), person);
 	 		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
 	 	}

}