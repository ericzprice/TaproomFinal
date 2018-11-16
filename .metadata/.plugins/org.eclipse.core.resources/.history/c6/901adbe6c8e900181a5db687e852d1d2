package taproom.model.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import taproom.model.Order;
import taproom.model.dao.OrderDao;
import taproom.model.dao.OrderDaoInMemoryImp;

@Path("orders")
public class OrderServiceImplementation implements OrderService {

	
	OrderDao orderDao=new OrderDaoInMemoryImp();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
		public boolean addOrder( Order order)
		{
		return orderDao.addOrder(order);
			}

	@Override
	public boolean removeOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.removeOrder(order);
	}

	@Path("/multiple")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Override
	public boolean addMultipleOrders(List<Order> orders) {
	System.out.println("hello from mul ord");
		return orderDao.addMultipleOrders(orders);
		
	}

}
