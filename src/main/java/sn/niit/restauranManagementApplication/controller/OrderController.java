package sn.niit.restauranManagementApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.domain.OrderItems;
import sn.niit.restauranManagementApplication.service.OrderService;
import sn.niit.restauranManagementApplication.service.ProduitService;

@Controller
@RequestMapping("/order")
public class OrderController 
{
	@Autowired
	OrderService orderService;

	@Autowired
	ProduitService produitService;
	
	@GetMapping("/list")
	public String showOrderList(Model model) 
	{
		model.addAttribute("orderService", orderService);
		return"admin/order-list";
	}
	
	@GetMapping("/new")
	public String showForm(OrderItems order )
	{
		return "admin/order-new";
	}
	
	@PostMapping("/save")
	public String saveOrdere(@Valid OrderItems order, BindingResult bindindResul) 
	{
		if(bindindResul.hasErrors()) 
		{
			return "admin/order-new";
		}
		else 
		{
		 orderService.saveOrUpdateOrder(order);
		 return "redirect:/order/list";
		}
	}

}
