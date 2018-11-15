package taproom.model.dao;

import java.util.List;

import taproom.model.Order;

public interface OrderDao {
	
	boolean addOrder(Order order);
	boolean removeOrder(Order order);
	boolean addMultipleOrders(List<Order> orders);
	

}
