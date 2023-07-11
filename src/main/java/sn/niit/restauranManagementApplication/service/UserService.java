package sn.niit.restauranManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import sn.niit.restauranManagementApplication.domain.User;


public interface UserService {
	void saveUser(User user);
	void updateUser(Long id, User user);
	User findUserByEmail(String email);
	Page<User> findPaginated(int pageNumber, int pageSize);
	List<User> findAllUser();
	List<User> findAllEmployee();
	Optional<User> findUserById(Long id);
	void saveAdmin(User user);
	
}

