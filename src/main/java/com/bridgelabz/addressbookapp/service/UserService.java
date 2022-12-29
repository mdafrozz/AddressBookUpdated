package com.bridgelabz.addressbookapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.addressbookapp.dto.UserDTO;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.model.UserModel;
import com.bridgelabz.addressbookapp.repository.UserRepository;

@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository repo;

	public String addUser(UserDTO dto) {
        UserModel model = new UserModel(dto);
        repo.save(model);
        return "Registered successfully";
    }
	
	// Login check
		public UserModel loginUser(String email, String password) {
			UserModel userModel = repo.findByEmailAddress(email);
			if (userModel != null) {
				if (userModel.getPassword().equals(password)) {
					return userModel;
				} else
					throw new AddressBookException("Login Failed, Wrong Password!!!");
			} else
				throw new AddressBookException("Login Failed, Invalid emailId or password!!!");
		}
		
		// Get the user data by id
		public UserModel getUserDataById(int id) {
			UserModel userModel = repo.findById(id).orElse(null);
			if (userModel != null) {
				return userModel;
			} else
				throw new AddressBookException("UserID: " + id + ", does not exist");
		}

}
