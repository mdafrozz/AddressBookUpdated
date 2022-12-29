package com.bridgelabz.addressbookapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.addressbookapp.model.AddressBookModel;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookModel, Integer>{
	@Query(value = "select * from addressbook where email = :email",nativeQuery = true)
	AddressBookModel findByEmail(String email);
	@Query(value = "select * from addressbook where  phone_number = :phoneNumber",nativeQuery = true)
	AddressBookModel findByPhone(String phoneNumber);
	@Query(value = "SELECT * FROM addressbook WHERE full_name LIKE %:name% OR city LIKE %:name% OR state LIKE %:name% "
			+ "OR phone_number LIKE %:name%", nativeQuery = true)
    List<AddressBookModel> findByName(String name);

}
