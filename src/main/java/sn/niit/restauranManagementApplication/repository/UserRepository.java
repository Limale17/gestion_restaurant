package sn.niit.restauranManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.niit.restauranManagementApplication.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{
	public User findByEmail(String email);
	

}
