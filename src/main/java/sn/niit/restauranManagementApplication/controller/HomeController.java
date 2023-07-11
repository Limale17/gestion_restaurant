package sn.niit.restauranManagementApplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.domain.Produit;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.service.CategorieService;
import sn.niit.restauranManagementApplication.service.ProduitService;
import sn.niit.restauranManagementApplication.service.UserService;

@Controller 
public class HomeController 
{
	@Autowired
	CategorieService categorieService;
	
	@Autowired
	ProduitService produitService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String showHome(Model model)
	{
		List<Categorie> listCategorie = categorieService.getAllCategorie();
		model.addAttribute("categorieList", listCategorie);
		
		return "appli/home";
	}
	@GetMapping("/menu")
	public String showMenu(Model model)
	{
		List<Categorie> listCategorie = categorieService.getAllCategorie();
		model.addAttribute("categorieList", listCategorie);
		
		return "appli/menu";
	}
	
	@GetMapping("/about-us")
	public String showAbout()
	{
		return "appli/about-us";
	}
	@GetMapping("/contact")
	public String showContact()
	{
		return "appli/contact";
	}
	
	@GetMapping("/categorie/{id}")
	public String showProduit(@PathVariable("id") Long id, Model model)
	{
		Categorie categorie = categorieService.getCategorieById(id).get();
		List<Produit> listeProduit = produitService.listeProduitParCategorie(categorie);
		
		model.addAttribute("produits", listeProduit);
		model.addAttribute("categorie", categorie);
		return "appli/produit";
	}


	@GetMapping("/detail/{id}")
	public String showIndex(@PathVariable("id") Long id, Model model) {
		Produit produit = produitService.getProduitById(id);
		model.addAttribute("produit", produit);
	
		return "appli/detail_produit";
	}
	
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
		return "403";
	}
	
	
}
