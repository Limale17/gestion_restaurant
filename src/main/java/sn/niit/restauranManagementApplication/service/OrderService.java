package sn.niit.restauranManagementApplication.service;

import java.util.List;

import sn.niit.restauranManagementApplication.domain.OrderItems;


public interface OrderService 
{
	void saveOrUpdateOrder(OrderItems orderItems);
	OrderItems getOrderById(Long id);
	 String getOrderState(OrderItems orderItems);
	void deleteOrder(Long Id);
	List<OrderItems> getAllOrder();

}
