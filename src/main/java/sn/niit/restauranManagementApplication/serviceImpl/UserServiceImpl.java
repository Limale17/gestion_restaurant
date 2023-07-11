package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sn.niit.restauranManagementApplication.domain.Role;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.repository.RoleRepository;
import sn.niit.restauranManagementApplication.repository.UserRepository;
import sn.niit.restauranManagementApplication.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
    		BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        User myUser = new User();
        myUser.setNom(user.getNom());
        myUser.setPrenom(user.getPrenom());
        myUser.setEmail(user.getEmail());
        myUser.setAdresse(user.getAdresse());
        myUser.setTelephone(user.getTelephone());
        // encrypt the password using spring security
        myUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        myUser.setRoles(roles);
        userRepository.save(myUser);
    }

    @Override
    public void updateUser(Long id, User user) {
        User myUser = findUserById(id).get();
        myUser.setNom(user.getNom());
        myUser.setPrenom(user.getPrenom());
        myUser.setEmail(user.getEmail());
        // encrypt the password using spring security
        myUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllEmployee() {
        return userRepository.findAll();
    }
    
    @Override
	public Page<User> findPaginated(int pageNumber, int pageSize)
	{
		PageRequest pageable = PageRequest.of(pageNumber-1, pageSize);

		return userRepository.findAll(pageable);
	}

	@Override
	public Optional<User> findUserById(Long id) {
		
		return userRepository.findById(id);
	}

	@Override
	public void saveAdmin(User user) {
		// TODO Auto-generated method stub
		
	}

	

	
}
