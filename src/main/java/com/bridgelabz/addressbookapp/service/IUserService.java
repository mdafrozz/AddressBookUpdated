package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.UserDTO;
import com.bridgelabz.addressbookapp.model.UserModel;

public interface IUserService {
	public String addUser(UserDTO dto);
	public UserModel loginUser(String email, String password);
	public UserModel getUserDataById(int id);
}
