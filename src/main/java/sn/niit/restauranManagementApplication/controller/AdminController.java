package sn.niit.restauranManagementApplication.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController
{
	
	@GetMapping("/")
	public String dashboard()
	{
			return "admin/dashboard";
	}
		
	
	
//	 @GetMapping("/customer/list")
//	  public String listCustomer(Model model)
//	  {
//		   return showPaginatedPage(1, model);
//	  }
//	 
//	 @GetMapping("/customer/list/page/{pageNumber}")
//		public String showPaginatedPage(@PathVariable("pageNumber") int pageNumber, Model model)
//		{
//			int pageSize=5;
//			Page<Customer> page = customerService.findPaginated(pageNumber,pageSize);
//			List<Customer>  customerList = page.getContent();
//
//			model.addAttribute("pageCourente", pageNumber);
//			model.addAttribute("totalPages", page.getTotalPages());
//			model.addAttribute("totalItems", page.getTotalElements());
//			model.addAttribute("customerList", customerList);
//			model.addAttribute("page", page);
//
//			 return "admin/customer_list";
//
//		}
	 
}
