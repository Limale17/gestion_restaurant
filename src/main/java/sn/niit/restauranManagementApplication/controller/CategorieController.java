package sn.niit.restauranManagementApplication.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import sn.niit.restauranManagementApplication.service.CategorieService;

@Controller
@RequestMapping("/admin/categorie")
public class CategorieController 
{
	@Autowired
	CategorieService catService;
	
	@GetMapping("/list")
	public String showAllCategorie(Model model)
	{
		return showPaginatedPage(1, model);
	}
	
	
	@GetMapping("/new")
	public String showForm( Categorie categorie )
	{
		return "admin/categorie-new";
	}
	
	//@RolesAllowed("ROLE_DMIN")
	@PostMapping("/save")
	public String saveCategorie(@RequestParam("image") MultipartFile file,
			@RequestParam("nom") String nom, @RequestParam("description") String description)
	{
		Categorie categorie = new Categorie();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.contains("..")) {
			System.out.println("file invalid");
		}
		
		try {
			categorie.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		categorie.setDescription(description);
		categorie.setNom(nom);
		catService.saveOrUpdateCategorie(categorie);
		 return "redirect:/admin/categorie/list";
		
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model)
	{
		Categorie categorie = catService.getCategorieById(id).get();
		
		model.addAttribute("categorie", categorie);
		 return "admin/categorie-edit";
	}
	
	@PostMapping("/update/{id}")
	public String updateCategorie(@PathVariable("id") Long id, @RequestParam("image") MultipartFile file,
			@RequestParam("nom") String nom, @RequestParam("description") String description)
	{
		Categorie categorie = catService.getCategorieById(id).get();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.contains("..")) {
			System.out.println("file invalid");
		}
		
		try {
			categorie.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		categorie.setDescription(description);
		categorie.setNom(nom);
		catService.saveOrUpdateCategorie(categorie);
		return "redirect:/admin/categorie/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCategorie(@PathVariable("id") Long id)
	{ 
		catService.deleteCategorie(id);
		return "redirect:/admin/categorie/list";
	}
	
	
	  @GetMapping("/list/page/{pageNumber}") 
	  public String showPaginatedPage(@PathVariable("pageNumber") int pageNumber, Model model) 
	  {
		  int pageSize=5; 
		  Page<Categorie> page = catService.findPaginated(pageNumber,pageSize);
		  List<Categorie>  categorieList = page.getContent();
		  
		  model.addAttribute("pageCourente", pageNumber);
		  model.addAttribute("totalPages", page.getTotalPages());
		  model.addAttribute("totalItems", page.getTotalElements());
		  model.addAttribute("categorieList", categorieList);
		  model.addAttribute("page", page);
		  
		  return "admin/categorie-list";
	  
	  }
	 
    

}
