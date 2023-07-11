package sn.niit.restauranManagementApplication.controller;

import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sn.niit.restauranManagementApplication.domain.CartItem;
import sn.niit.restauranManagementApplication.domain.Produit;
import sn.niit.restauranManagementApplication.domain.ShoppingCart;
import sn.niit.restauranManagementApplication.service.ProduitService;
import sn.niit.restauranManagementApplication.serviceImpl.ShoppingCartService;

@Controller
public class CartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ProduitService pService;
	

	@PostMapping("/addToCart")
	public String addToCart(HttpServletRequest request, Model model, @RequestParam("id") Long id,
			@RequestParam("quantity") int quantity) {

		// sessiontToken
		String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
		if (sessionToken == null) {

			sessionToken = UUID.randomUUID().toString();
			request.getSession().setAttribute("sessiontToken", sessionToken);
			shoppingCartService.addShoppingCartFirstTime(id, sessionToken, quantity);
		} else {
			shoppingCartService.addToExistingShoppingCart(id, sessionToken, quantity);
		}
		
		Produit p = pService.getProduitById(id);
		
		long idCategorie = p.getCategorie().getId();
		return "redirect:categorie/" + idCategorie;
	}
	
	@GetMapping("/shoppingCart")
	public String showShoopingCartView(HttpServletRequest request, Model model) {
		
		String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionTokent(sessionToken);
		
		
		if(shoppingCart == null) {
			return "appli/shopping_cart";
		}else {
			
			
			Set<CartItem> cartItems = shoppingCart.getItems();
		
			int nombre = cartItems.size();
		
			model.addAttribute("nombre", nombre);
			model.addAttribute("shoppingCart", shoppingCart);
			model.addAttribute("items", cartItems);
			return "appli/shoppingCart";
		}
		
		
		
	}

	@PostMapping("/updateShoppingCart")
	public String updateCartItem(@RequestParam("item_id") Long id,
			@RequestParam("quantity") int quantity) {
		
		shoppingCartService.updateShoppingCartItem(id,quantity);
		return "redirect:shoppingCart";
	}
	@GetMapping("/removeCartItem/{id}")
	public String removeItem(@PathVariable("id") Long id, HttpServletRequest request) {
		String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
		System.out.println("got here ");
		shoppingCartService.removeCartIemFromShoppingCart(id,sessionToken);
		return "redirect:/shoppingCart";
	}
	
	@GetMapping("/clearShoppingCart")
	public String clearShoopiString(HttpServletRequest request) {
		String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
		request.getSession(false).removeAttribute("sessiontToken");
		shoppingCartService.clearShoppingCart(sessionToken);
		return "redirect:/shoppingCart";
	}
}


