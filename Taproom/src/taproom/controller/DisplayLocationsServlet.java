package taproom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import taproom.model.Location;
import taproom.model.service.LocationService;
import taproom.model.service.LocationServiceImplementation;

/**
 * Servlet implementation class DisplayLocationsServlet
 */
@WebServlet("/DisplayLocationsServlet")
public class DisplayLocationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	L = long data type

    /**
     * Default constructor. 
     */
    public DisplayLocationsServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LocationService locationService=new LocationServiceImplementation();
		List<Location> locations=locationService.getAllLocations();
		System.out.println("from Loc"+locations.get(0));
	
	    request.getSession().setAttribute("locations",locations);  //jsp java server pages
	    System.out.println("from Loc session locations"+request.getSession().getAttribute("locations"));
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("displayLocations.jsp");
		requestDispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);//calling the doGet method. 
	}

}
