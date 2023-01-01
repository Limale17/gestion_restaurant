package sn.niit.restauranManagementApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.niit.restauranManagementApplication.domain.Produit;
import sn.niit.restauranManagementApplication.service.CategorieService;
import sn.niit.restauranManagementApplication.service.ProduitService;

@Controller
@RequestMapping("/produit")
public class ProduitController 
{
	    @Autowired 
	    ProduitService produitService;
	     
	    @Autowired
	    CategorieService categorieService;
	    
		@GetMapping("/list")
		public String showAllplat(Model model) 
		{
			model.addAttribute("produitList", produitService.getAllProduit());
			return"admin/produit-list";
		}
		
		@GetMapping("/new")
		public String showForm(Produit produit,  Model model)
		{
			model.addAttribute("categories", categorieService.getAllCategorie());
			return"admin/produit-new";
		}
		
		@PostMapping("/save")
		public String saveOrUpdate( @Valid Produit produit,BindingResult bindindResult )
		{
			if(bindindResult.hasErrors()) 
			{
				return "admin/departement-new";
			}
			else
		   {
			
			produitService.saveOrUpdateProduit(produit);
			return "redirect:/produit/list";
			}
		    
	    }
		
		@GetMapping("/edit/{id}")
		public String showEditForm(@PathVariable("id") Long id ,Model model) 
		{
			model.addAttribute("produit", produitService.getProduitById(id));
			model.addAttribute("categorie", categorieService.getAllCategorie());
			
			return "admin/produit-edit";
		}
		
		@PostMapping("/update/{id}")
		public String SaveOrUpdateProduct(@PathVariable("id") Long id, Produit produit)
		{
			produitService.saveOrUpdateProduit(produit);
			return "redirect:/produit/list";
		}
		
		@GetMapping("/delete/{id}")
		public String deleteProduit(@PathVariable("id") Long id) 
		{
			produitService.deleteProduit(id);
			return "redirect:/produit/list";
		}

}
