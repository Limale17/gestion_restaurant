package sn.niit.restauranManagementApplication.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sn.niit.restauranManagementApplication.domain.Commande;
import sn.niit.restauranManagementApplication.domain.State;
import sn.niit.restauranManagementApplication.service.CommandeService;
import sn.niit.restauranManagementApplication.serviceImpl.EmailSenderService;

@Controller
@RequestMapping("/admin/commande")
public class CommandeController 
{
	@Autowired
	CommandeService commandeService;
	
	@Autowired
	EmailSenderService sendService;
	
	@GetMapping("/list")
	public String showAllCommande(Model model)
	{
		return showPaginatedPage(1, model);
	}
	
	@GetMapping("/list/{date}")
	public String showCommande(@Param(value = "date") String date, Model model)
	{
		LocalDate localDate = LocalDate.parse(date);
		
			List<Commande> commandeList = commandeService.listCommandeParDate(localDate);
			 model.addAttribute("commandeList", commandeList);
			 model.addAttribute("date", date);
			 return "admin/commande-list";
		
		
	}
	
	@GetMapping("/pending")
	public String showCommandeByState(Model model)
	{
		List<Commande> commandeList = commandeService.listCommandeParState();
		model.addAttribute("commandeList", commandeList);
		return "admin/commande-list";
	}
	
	
	  @GetMapping("/list/page/{pageNumber}") 
	  public String
	  showPaginatedPage(@PathVariable("pageNumber") int pageNumber, Model model) 
	  {
		  int pageSize=5; 
		  Page<Commande> page = commandeService.findPaginated(pageNumber,pageSize);
		  List<Commande>  commandeList = page.getContent();
		  
		  model.addAttribute("pageCourente", pageNumber);
		  model.addAttribute("totalPages", page.getTotalPages());
		  model.addAttribute("totalItems", page.getTotalElements());
		  model.addAttribute("commandeList", commandeList);
		  model.addAttribute("page", page);
		  
		  return "admin/commande-list";
	  
	  }
	 
    @GetMapping("/{id}")
    public String getCommande(@PathVariable("id") Long id, Model model) {
    	Commande commande = commandeService.getCommandeById(id).get();
    	
    	model.addAttribute("commande", commande);
    	model.addAttribute("customer", commande.getUser());
    	model.addAttribute("items", commande.getPaiement().getShoppingCart().getItems());
    	return "admin/detail-commande";
    }
   
	@PostMapping("/changerEtat")
	public String changerEtat(@RequestParam("id") Long id) {
		
		Commande commande = commandeService.getCommandeById(id).get();
  
    		commande.setState(State.delievery);
    		commandeService.saveCommande(commande);
    		
    		String toEmail = commande.getUser().getEmail();
    		sendService.sendMail(toEmail);
   
    	return "redirect:/admin/commande/" + id;
	}
	
	
	
}
