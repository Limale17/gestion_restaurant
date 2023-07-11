package sn.niit.restauranManagementApplication.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
  
    
//    // handler method to handle user registration form request
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model){
//        // create model object to store form data
//        Customer customer = new Customer();
//        model.addAttribute("customer", customer);
//        return "appli/register";
//    }
//   
//
//    // handler method to handle user registration form submit request
//    @PostMapping( value = "/register/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String registration(@Valid @ModelAttribute("customer") Customer customer,
//                               BindingResult result,
//                               Model model){
//    	Customer existingUser = customerService.findCustomerByEmail(customer.getEmail());
//
//        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//        }
//
//        if(result.hasErrors()){
//            model.addAttribute("customer", customer);
//            return "appli/register";
//        }
//
//        customerService.saveCustomer(customer);
//        return "redirect:/register?success";
//    }
    
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