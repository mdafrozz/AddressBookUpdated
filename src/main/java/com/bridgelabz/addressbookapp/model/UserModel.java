package com.bridgelabz.addressbookapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.addressbookapp.dto.UserDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="user")
@NoArgsConstructor
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userName;
	private String email;
	private String password;
		
	public UserModel(UserDTO dto ) {
		
		this.userName = dto.getUserName();
		this.email = dto.getEmail();
		this.password = dto.getPassword();
	}
}
