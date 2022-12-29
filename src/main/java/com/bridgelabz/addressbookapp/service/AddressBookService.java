package com.bridgelabz.addressbookapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.model.AddressBookModel;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import com.bridgelabz.addressbookapp.util.EmailSenderService;
import com.bridgelabz.addressbookapp.util.TokenUtil;

@Service
public class AddressBookService implements IAddressBookService {
	
	@Autowired
	AddressBookRepository repo;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	EmailSenderService sender;
	
	public String getData() {
        return "Welcome to AddressBook App..!!";
    }

	public AddressBookModel findById(String token) {
		int id = tokenUtil.decodeToken(token);
        return repo.findById(id).stream()
                .filter(data -> data.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AddressBookException("Contact Not Found/ Incorrect Token"));
    }
	
	public List<AddressBookModel> getAll(){
		try{
			return repo.findAll();
		}
		catch(AddressBookException e){
			throw new AddressBookException("Incorrect Token");
		}
	}
	
	public String addContact(AddressBookDTO dto) {
		Optional<AddressBookModel> email = Optional.ofNullable(repo.findByEmail(dto.getEmail()));
		Optional<AddressBookModel> phone = Optional.ofNullable(repo.findByPhone(dto.getPhoneNumber()));
		if (email.isPresent()) {
			return "Email already exists";
		} else if (phone.isPresent()) {
			return "Phone Number already exists";
		}else {
        AddressBookModel model = new AddressBookModel(dto);
        repo.save(model);
        String token = tokenUtil.createToken(model.getId());
        model.setToken(token);
        repo.save(model);
        //email body
        String data = "Hi "+model.getFullName()+" \uD83D\uDE00,\n\nThank you for choosing AddressBook App \uD83E\uDD1D\n\n"
        		+"Your contact is successfully saved. Please find the details below\n\n"+
        "CONTACT DETAILS: \n"+"\uD83D\uDC71 Full Name: "+model.getFullName()+"\n"+
        		"\uD83C\uDFE0 Address: "+model.getAddress()+"\n"+"\uD83C\uDF06 City: "+model.getCity()
        		+"\n"+"\uD83C\uDFD9 State: "+model.getState()+"\n"+"\uD83D\uDCCC Zip Code: "
        		+model.getZipCode()+"\n"+"\uD83D\uDCDE Phone Number: "+model.getPhoneNumber()+"\n"
        		+"\uD83D\uDCE7 Email Address: "+model.getEmail()+"\n\uD83E\uDE99 Token :"+token
        		+"\n\nRegards \uD83D\uDE4F,\nAddressBook Team";
        //sender.sendEmail(model.getEmail(),"New Contact Created",data);
        return token;
		}
    }

    public String editContact(String token, AddressBookDTO dto) {
       	AddressBookModel model = new AddressBookModel(dto);
   	 	model = this.findById(token);
    	if(model.equals(model)) {
    	model.setFullName(dto.getFullName());
        model.setAddress(dto.getAddress());
        model.setPhoneNumber(dto.getPhoneNumber());
        model.setCity(dto.getCity());
        model.setState(dto.getState());
        model.setZipCode(dto.getZipCode());
        model.setEmail(dto.getEmail());
      //Email Body
        String msg = "Hi "+model.getFullName()+" \uD83D\uDE07"+",\n\nYour contact details are successfully updated \uD83D\uDC4D\n\n"
        		+ "UPDATED DETAILS: \n"+"\uD83D\uDC71 Full Name: "+model.getFullName()+"\n"+"\uD83C\uDFE0 Address: "+model.getAddress()+"\n"
                +"\uD83C\uDF06 City: "+model.getCity()+"\n"+"\uD83C\uDFD9 State: "+model.getState()+"\n"+"\uD83D\uDCCC Zip Code: "+model.getZipCode()+"\n"+
                "\uD83D\uDCDE Phone Number: "+model.getPhoneNumber()+"\n"+"\uD83D\uDCE7 Email Address: "+model.getEmail()+
                "\n\nRegards \uD83D\uDE4F,\nAddressBook Team";
        //sending email
        //sender.sendEmail(model.getEmail(),"Contact Updated", msg);
        repo.save(model);
        return token;}
    	else {
    		throw new AddressBookException("Incorrect Token/ Contact not found");
    	}
    	
    }

    public String deleteContact(String token){
    	int id = tokenUtil.decodeToken(token);
    	 Optional<AddressBookModel> data = repo.findById(id);
         if(data.isPresent()) {
         	repo.deleteById(id);
           	String msg = "Hey "+data.get().getFullName()+" \uD83D\uDE07,\n\n Your contact has been successfully removed"
           			+ " from AddressBook App..!!\n"
           			+ "Hope to see you again soon \uD83D\uDE1E!\n\n"
           			+ "Thank you,\n"
           			+ "AddressBook Team";
         	 //sender.sendEmail(data.get().getEmail(), "Contact Deleted..!!", msg);
         	return token;
         	}
         else
         	throw new AddressBookException("Incorrect Token/ Contact not found");
    }
    
    public AddressBookModel getByEmail(String email) {
        return repo.findByEmail(email);
    }
    
 // Sorting : City
 	public List<AddressBookModel> sortCity() {
 		List<AddressBookModel> city = repo.findAll();
 		if (city.isEmpty()) {
 			throw new AddressBookException("No data available!!!");
 		} else
 			return repo.findAll(Sort.by(Sort.Direction.ASC, "city"));
 	}
 	
 // Sorting : State
 	public List<AddressBookModel> sortState() {
 		List<AddressBookModel> state = repo.findAll();
 		if (state.isEmpty()) {
 			throw new AddressBookException("No data available!!!");
 		} else
 			return repo.findAll(Sort.by(Sort.Direction.ASC, "state"));
 	}

	// Get Book Data by Book Name
	public List<AddressBookModel> searchbyName(String name) {
		List<AddressBookModel> Model = repo.findAll();
		if (Model != null) {
			return repo.findByName(name);
		} else
			throw new AddressBookException("Person: " + name + " is not available");
	}
   
}
