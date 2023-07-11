package sn.niit.restauranManagementApplication.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.repository.RoleRepository;
import sn.niit.restauranManagementApplication.service.CommandeService;
import sn.niit.restauranManagementApplication.service.UserService;
 
@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired
	UserService userService; 
 
	@Autowired
	RoleRepository roleRepo; 
	
	@Autowired
	CommandeService commandeService; 
  
	@GetMapping("/list")
	public String showAllUser(Model model)
	{
		return showPaginatedPage(1, model);
	}
	
	 @GetMapping("/list/page/{pageNumber}") 
	  public String showPaginatedPage(@PathVariable("pageNumber") int pageNumber, Model model) 
	  {
		  int pageSize=5; 
		  Page<User> page = userService.findPaginated(pageNumber,pageSize);
		  List<User>  userList = page.getContent();
		  
		  model.addAttribute("pageCourente", pageNumber);
		  model.addAttribute("totalPages", page.getTotalPages());
		  model.addAttribute("totalItems", page.getTotalElements());
		  model.addAttribute("userList", userList);
		  model.addAttribute("page", page);
		  
		  return "admin/user-list";
	  
	  }
    // handler method to handle user registration form request
    @GetMapping("/new")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        User user = new User();
        model.addAttribute("user", user);
        return "admin/user_new";
    }
   

    // handler method to handle user registration form submit request
    @PostMapping( value = "/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String registration(@Valid @ModelAttribute("customer") User user,
            BindingResult result,
            Model model){
    	User existingUser = userService.findUserByEmail(user.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "redirect:/admin/user/new";
        }

       
        userService.saveUser(user);
        return "redirect:/admin/user/list";
    }
    
    @GetMapping("/faireAdmin/{id}")
	public String faireAdmin(@PathVariable("id") Long id){
		User user = userService.findUserById(id).get();
		
		userService.saveAdmin(user);
		return "redirect:/admin/user/list";
	}
    
    
//    @GetMapping("/profile")
//    public String profile(@AuthenticationPrincipal CustomerUserDetails userDetails, Model model) {
//    		if(userDetails == null) {
//    			return "redirect:login";
//    		}
//    	Customer customer = customerService.findCustomerByEmail(userDetails.getUsername());
//    	List<Commande> commandes = commandeService.listCommandeParCustomer(customer);
//    	model.addAttribute("customer", customer);
//    	model.addAttribute("commandes", commandes);
//    	
//    	for(Commande commande : commandes) {
//    		double montant = commande.getPaiement().getShoppingCart().getTotalPrice();
//        	model.addAttribute("montant", montant);
//    	}
//    	
//        return "appli/profile";
//    }
//    
//    @GetMapping("/commande/{id}")
//    public String getCommande(@PathVariable("id") Long id, Model model) {
//    	Commande commande = commandeService.getCommandeById(id).get();
//    	
//    	model.addAttribute("commande", commande);
//    	model.addAttribute("customer", commande.getCustomer());
//    	model.addAttribute("items", commande.getPaiement().getShoppingCart().getItems());
//    	double montant = commande.getPaiement().getShoppingCart().getTotalPrice();
//    	model.addAttribute("montant", montant);
//    	System.out.println(montant);
//    	return "appli/detail-commande";
//    }
//    
//    
//    @GetMapping("/customer/edit/{id}")
//	public String showEditForm(@PathVariable("id") Long id, Model model)
//	{
//		Customer customer = customerService.findCustomerById(id).get();
//		
//		model.addAttribute("customer", customer);
//		 return "appli/customer-edit";
//	}
//	
//	@PostMapping("/customer/update/{id}")
//	public String updateCustomer(@PathVariable("id") Long id,
//			@ModelAttribute("customer") Customer customer){
//		
//		customerService.updateCustomer(id, customer);
//		return "redirect:/profile";
//	}
//    
//
//    @GetMapping("/admin/customer/commande/{id}")
//    public String commandesParClient(@PathVariable("id") Long id, Model model) {
//    	Customer customer = customerService.findCustomerById(id).get();
//    	List<Commande> commandeList = commandeService.listCommandeParCustomer(customer);
//    	model.addAttribute("commandeList", commandeList);
//    	model.addAttribute("customer", customer);
//
//    	return "admin/commandes-client";
//    }
}