package com.bridgelabz.addressbookapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	
	@NotBlank(message = "user name cannot be empty")
	private String userName;
	
	@Pattern(regexp = "^[a-z0-9]{1,20}([_.+-][a-z0-9]+)?@[a-z0-9]+.[a-z]{2,3}(.[a-z]{2})?$", message = "Enter a valid Email-id")
	private String email;
	
	@NotBlank(message = "password cannot be empty")
	private String password;
	
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Please enter a valid mobile number")
	private String phoneNumber;

}
