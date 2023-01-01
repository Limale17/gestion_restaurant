package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Order;
import sn.niit.restauranManagementApplication.repository.OrderRepository;

import sn.niit.restauranManagementApplication.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService
{
	@Autowired
	OrderRepository orderRepository ;

	@Override
	public void saveOrUpdateCategorie(Order order) 
	{
		orderRepository.save(order);
	}

	@Override
	public Order getOrderById(Long id) 
	{
		Order order =null;
		Optional<Order> optionalOrder =orderRepository.findById(id);
		if(!optionalOrder.isEmpty())
		{
			order= optionalOrder.get();
		}
		else 
		{
			throw new RuntimeException("Aucune commande trouve");
		}
		return order;
	}

	@Override
	public List<Order> getAllOrder() 
	{
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long Id) 
	{
		orderRepository.deleteById(Id);		
	}

	

}
