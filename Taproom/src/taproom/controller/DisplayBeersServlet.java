package taproom.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taproom.model.Beer;
import taproom.model.service.BeerService;
import taproom.model.service.BeerServiceImplementation;

/**
 * Servlet implementation class DisplayBeersServlet
 */
@WebServlet("/DisplayBeersServlet")
public class DisplayBeersServlet extends HttpServlet {
//	extends = inherits from parent class
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DisplayBeersServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String locationStr=(String)request.getQueryString();
		System.out.println("display beers"+ locationStr);
		BeerService beerService=new BeerServiceImplementation();
		
		List<Beer> listOfbeers=beerService.getAllBeers(locationStr.split("=")[1]);
		System.out.println("in servlet"+ listOfbeers);
		List<Beer> listOfNonKickedBeers=new ArrayList<Beer>();
		for( Beer beer:listOfbeers)
		{
			if(beer.isKicked()==false)
			{
				listOfNonKickedBeers.add(beer);
			}
		}
		request.setAttribute("beers", listOfNonKickedBeers);
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("displayBeers.jsp");
		requestDispatcher.forward(request,response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
