package sn.niit.restauranManagementApplication.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sn.niit.restauranManagementApplication.domain.CartItem;
import sn.niit.restauranManagementApplication.domain.Commande;
import sn.niit.restauranManagementApplication.domain.Paiement;
import sn.niit.restauranManagementApplication.domain.ShoppingCart;
import sn.niit.restauranManagementApplication.domain.State;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.security.CustomUserDetails;
import sn.niit.restauranManagementApplication.service.CommandeService;
import sn.niit.restauranManagementApplication.service.PaiementService;
import sn.niit.restauranManagementApplication.service.UserService;
import sn.niit.restauranManagementApplication.serviceImpl.ShoppingCartService;

@Controller
public class PaimentController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private PaiementService paiementService;
	
	@Autowired
	private CommandeService commandeService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/checkout")
	public String checkout(@AuthenticationPrincipal CustomUserDetails userDetails,
			HttpServletRequest request, Model model) {
		
		User user = userService.findUserByEmail(userDetails.getUsername());

		String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionTokent(sessionToken);
		
		Set<CartItem> cartItems = shoppingCart.getItems();
		
		int nombre = cartItems.size();
		model.addAttribute("nombre", nombre);
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("items", cartItems);
		model.addAttribute("user", user);
		return "appli/paiement";
	}
	
	@PostMapping("/confirm")
	public String confirm(@AuthenticationPrincipal CustomUserDetails userDetails,
			HttpServletRequest request, Model model, 
			@Valid @ModelAttribute("paiement") Paiement paiement) {
		
		User user = userService.findUserByEmail(userDetails.getUsername());

		String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionTokent(sessionToken);
		paiement.setShoppingCart(shoppingCart);
		paiement.setUser(user);		
		paiementService.savePaiment(paiement);	
		
		Commande commande = new Commande();
		commande.setPaiement(paiement);
		commande.setState(State.pending);
		commandeService.saveCommande(commande);
		return "appli/conf";
	}
	
}


