package taproom.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taproom.model.Beer;
import taproom.model.Location;
import taproom.model.service.BeerService;
import taproom.model.service.BeerServiceImplementation;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] usersWish=request.getParameterValues("itemToBuy");
		System.out.println("items to buy");
		for(String s:usersWish)
		{
			System.out.println("users wish"+s);
		}
		Enumeration<String>  params=request.getParameterNames();
		System.out.println("params are ");
		while(params.hasMoreElements())
		{
			System.out.println(params.nextElement());
		}
		int i=0;
		String locationSelected=(String)request.getParameter("location");
		System.out.println("pur ser"+ locationSelected);
		Beer beer=null;
		System.out.println("user choice");
		for(String style:usersWish)
		{
			System.out.println(style);
			String ouncesValue=request.getParameter("ounces"+style.substring(0,4));
			System.out.println("ounces "+ ouncesValue);
			String noOfItems=request.getParameter("noOfItems"+style.substring(0,4));
			System.out.println("size "+ noOfItems);
			Location location=new Location(null,0,locationSelected);
		    beer=new Beer( style, "",0.0,0.0,0,0, 0,0, Integer.parseInt(noOfItems)*Integer.parseInt(ouncesValue),location);
		    
		}
		BeerService beerService=new BeerServiceImplementation();
		
		beerService.updateBeer(beer);
		
		//doGet(request, response);
	}

}

