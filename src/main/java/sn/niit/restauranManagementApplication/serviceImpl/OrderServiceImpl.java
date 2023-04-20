package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.OrderItems;
import sn.niit.restauranManagementApplication.domain.State;
import sn.niit.restauranManagementApplication.repository.OrderRepository;

import sn.niit.restauranManagementApplication.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService
{
	@Autowired
	OrderRepository orderRepository ;

	@Override
	public void saveOrUpdateOrder(OrderItems order) 
	{
		orderRepository.save(order);
	}

	@Override
	public OrderItems getOrderById(Long id) 
	{
		OrderItems order =null;
		Optional<OrderItems> optionalOrder =orderRepository.findById(id);
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
	public List<OrderItems> getAllOrder() 
	{
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long Id) 
	{
		orderRepository.deleteById(Id);		
	}

	@Override
	public String getOrderState(OrderItems order) 
	{
		return order.getState() ? String.valueOf(State.delievery) : String.valueOf(State.pending);
	}

	

}
