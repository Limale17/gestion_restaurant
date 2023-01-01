package sn.niit.restauranManagementApplication.service;

import java.util.List;

import sn.niit.restauranManagementApplication.domain.User;


public interface UserService  
{
	void saveUser(User user);
	User getUserByEmail(String email);
	List<User>getAllCategorie();

}

