package sn.niit.restauranManagementApplication.service;

import java.util.List;

import sn.niit.restauranManagementApplication.domain.Order;


public interface OrderService 
{
	void saveOrUpdateCategorie(Order order);
	Order getOrderById(Long id);
	void deleteOrder(Long Id);
	List<Order> getAllOrder();

}
