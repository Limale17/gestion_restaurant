package sn.niit.restauranManagementApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.repository.UserRepository;
import sn.niit.restauranManagementApplication.security.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository repo;
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given email");
        }
         
        return new CustomUserDetails(user);
    }
}
