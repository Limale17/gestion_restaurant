package sn.niit.restauranManagementApplication.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sn.niit.restauranManagementApplication.domain.Commande;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.security.CustomUserDetails;
import sn.niit.restauranManagementApplication.service.CommandeService;
import sn.niit.restauranManagementApplication.service.UserService;
 
@Controller
public class CustomerController {
	
	@Autowired
	UserService userService; 
 
	@Autowired
	CommandeService commandeService; 
  
	@GetMapping("/login")
	public String login()
	{
		return "admin/login";
	}
	
	 // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        User user = new User();
        model.addAttribute("user", user);
        return "appli/register";
    }
   

    // handler method to handle user registration form submit request
    @PostMapping( value = "/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String registration(@Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model){
    	User existingUser = userService.findUserByEmail(user.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
          model.addAttribute("user", user);
          return "appli/register";
      }

      userService.saveUser(user);
      return "redirect:/register?success";
  }
    
	@GetMapping("/403")
	public String errore403() {
		return "appli/403";
	}
	
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
    	User user = userService.findUserByEmail(userDetails.getUsername());
    	List<Commande> commandes = commandeService.listCommandeParUser(user);
    	model.addAttribute("user", user);
    	model.addAttribute("commandes", commandes);
    	
    	for(Commande commande : commandes) {
    		double montant = commande.getPaiement().getShoppingCart().getTotalPrice();
        	model.addAttribute("montant", montant);
    	}
    	
        return "appli/profile";
    }
    
    @GetMapping("/commande/{id}")
    public String getCommande(@PathVariable("id") Long id, Model model) {
    	Commande commande = commandeService.getCommandeById(id).get();
    	
    	model.addAttribute("commande", commande);
    	model.addAttribute("customer", commande.getUser());
    	model.addAttribute("items", commande.getPaiement().getShoppingCart().getItems());
    	double montant = commande.getPaiement().getShoppingCart().getTotalPrice();
    	model.addAttribute("montant", montant);
    	System.out.println(montant);
    	return "appli/detail-commande";
    }
    
    
    @GetMapping("/user/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model)
	{
		User user = userService.findUserById(id).get();
		
		model.addAttribute("user", user);
		 return "appli/customer-edit";
	}
	
	@PostMapping("/customer/update/{id}")
	public String updateCustomer(@PathVariable("id") Long id,
			@ModelAttribute("user") User user){
		
		userService.updateUser(id, user);
		return "redirect:/profile";
	}
    

   
}