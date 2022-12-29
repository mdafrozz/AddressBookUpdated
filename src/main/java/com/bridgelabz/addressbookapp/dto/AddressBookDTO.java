package com.bridgelabz.addressbookapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressBookDTO {

	@NotBlank(message = "Contact name cannot be empty")
	private String fullName;
	
	@NotBlank(message = "Address cannot be blank")
	private String address;
	
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Please enter a valid mobile number")
	private String phoneNumber;
	
	@NotEmpty(message = "city name cannot be empty")
	private String city;
	
	@NotEmpty(message = "state name cannot be empty")
	private String state;
	
	@Pattern(regexp = "^[1-9]{1}[0-9]{2}{0,1}[0-9]{3}$", message = "Please enter a valid pincode")
	private String zipCode;
	
	@Pattern(regexp = "^[a-z0-9]{1,20}([_.+-][a-z0-9]+)?@[a-z0-9]+.[a-z]{2,3}(.[a-z]{2})?$", message = "Enter a valid Email-id")
	private String email;
}
