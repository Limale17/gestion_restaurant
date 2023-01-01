package sn.niit.restauranManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.niit.restauranManagementApplication.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController 
{
	@Autowired
	OrderService orderService;
	
	@GetMapping("/list")
	public String showOrderList(Model model) 
	{
		model.addAttribute("orderList", orderService.getAllOrder());
		return"admin/order-list";
	}

}
