package taproom.model.service;

import java.util.List;

import taproom.model.Order;

public interface OrderService {

	
boolean addOrder(Order order);
	boolean removeOrder(Order order);
	boolean addMultipleOrders(List<Order> orders);
}
