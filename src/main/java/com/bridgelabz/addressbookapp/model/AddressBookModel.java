package com.bridgelabz.addressbookapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="addressbook")
@NoArgsConstructor
public class AddressBookModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fullName;
	private String address;
	private String phoneNumber;
	private String city;
	private String state;
	private String zipCode;
	private String email;
	private String token;
	
	public AddressBookModel(AddressBookDTO dto ) {
		
		this.fullName = dto.getFullName();
		this.address = dto.getAddress();
		this.phoneNumber = dto.getPhoneNumber();
		this.city = dto.getCity();
		this.state = dto.getState();
		this.zipCode = dto.getZipCode();
		this.email = dto.getEmail();
	}
	
	
}
