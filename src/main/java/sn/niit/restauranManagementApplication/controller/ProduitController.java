package sn.niit.restauranManagementApplication.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.domain.Produit;
import sn.niit.restauranManagementApplication.service.CategorieService;
import sn.niit.restauranManagementApplication.service.ProduitService;

@Controller
@RequestMapping("/admin/produit")
public class ProduitController 
{
	    @Autowired 
	    ProduitService produitService;
	  
	    @Autowired
	    CategorieService categorieService;

	@GetMapping("/list")
	public String showAllProduit(@Param(value = "keyword") String keyword, Model model)
	{
		List<Produit> listProduit = produitService.getAllProduit(keyword);
		model.addAttribute("produitList", listProduit);
		

		return showPaginatedPage(keyword, 1, model);
	}
		
		@GetMapping("/new")
		public String showForm(Produit produit,  Model model)
		{
			model.addAttribute("categories", categorieService.getAllCategorie());
			return"admin/produit-new";
		}
		
		@PostMapping("/save")
		public String saveOrUpdate( @RequestParam("image") MultipartFile multipartFile,
				@RequestParam("name") String name,
				@RequestParam("description") String description,
				@RequestParam("price") double price,
				@RequestParam("categorie") Categorie categorie) throws Exception
		{
			Produit produit = new Produit();
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			if(fileName.contains("..")) {
				System.out.println("file invalid");
			}
			
			try {
				produit.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			produit.setCategorie(categorie);
			produit.setPrice(price);
			produit.setName(name);
			produit.setDescription(description);
			produitService.saveOrUpdateProduit(produit);
			return "redirect:/admin/produit/list";
			
	    }
		
		@GetMapping("/edit/{id}")
		public String showEditForm(@PathVariable("id") Long id ,Model model) 
		{
			model.addAttribute("produit", produitService.getProduitById(id));
			model.addAttribute("categorie", categorieService.getAllCategorie());
			
			return "admin/produit-edit";
		}
		
		@PostMapping("/update/{id}")
		public String SaveOrUpdateProduct(@PathVariable("id") Long id, @RequestParam("image") MultipartFile multipartFile,
				@RequestParam("name") String name,
				@RequestParam("description") String description,
				@RequestParam("price") double price,
				@RequestParam("categorie") Categorie categorie) throws Exception
		{
			Produit produit = produitService.getProduitById(id);
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			if(fileName.contains("..")) {
				System.out.println("file invalid");
			}
			
			try {
				produit.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			produit.setCategorie(categorie);
			produit.setPrice(price);
			produit.setName(name);
			produit.setDescription(description);
			produitService.saveOrUpdateProduit(produit);
			return "redirect:/admin/produit/list";
		}
		
		@GetMapping("/delete/{id}")
		public String deleteProduit(@PathVariable("id") Long id) 
		{
			produitService.deleteProduit(id);
			return "redirect:/admin/produit/list";
		}

	@GetMapping("/list/page/{pageNumber}")
	public String showPaginatedPage(@Param(value = "keyword") String keyword, @PathVariable("pageNumber") int pageNumber, Model model)
	{
		int pageSize=5;
		Page<Produit> page = produitService.findPaginated(keyword, pageNumber,pageSize);
		List<Produit>  produitList = page.getContent();

		model.addAttribute("pageCourente", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("produitList", produitList);
		model.addAttribute("page", page);

		return "admin/produit-list";

	}

}
