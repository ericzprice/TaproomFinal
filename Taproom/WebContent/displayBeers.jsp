<%-- Syntax of JSP Directive
     <%@ directive attribute="value" %>   
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
    <%@ page  import="taproom.model.Beer,taproom.model.Location,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>Active Taps</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">
   
    		function makeAjaxCall()
    		{
	            loc=$("#location").val();
	           //alert(loc);
	            var favorite = [];
	            $.each($("input[name='itemToBuy']:checked"), function()
	            {            
	                favorite.push($(this).val());
	            });
	            var ounces=[];
	            var noOfItems=[];
	            var orders=[];
	            var order;
	            $.each(favorite,function( index, value ) 
	            {
	            	var name="ounces"+value.substring(0,4);
	            	//alert(name);
	            	var que="select[name='"+name+"']";
	            	//alert("que"+que)
	            	var ouncesVal=$(que).val();
	            	//alert(ouncesVal);
	            	 ounces.push(ouncesVal);
	            	 // alert( index +": " + value );
	            	 que="select[name='"+"noOfItems"+value.substring(0,4)+"']";
	            	// alert("que"+que)
	            	 var itemsCount=$(que).val();
	            	// alert(itemsCount)
	            	 order={beerStyle:value,quantity:itemsCount,size:ouncesVal,location:loc};
	            	 orders.push(order);
	            });
	           //alert("items to buy are: " + favorite.join(","));
	           
	            var rootURL="http://localhost:8085/Taproom/webapi/orders/multiple";
	          //  var rootURLHttps="https://localhost:9998/Taproom/webapi/orders/multiple";
	            var newURL = $(location).attr("href");
	           // alert(newURL);
	            var first5=newURL.substring(0,5);
	            //alert(first5);
	            if(first5=="https")
	            	rootURL=newURL.substring(0,22)+"/Taproom/webapi/orders/multiple";//"https://localhost:9998/;
	            else
	            	rootURL=newURL.substring(0,21)+"/Taproom/webapi/orders/multiple";//"http://localhost:9998/Taproom/webapi/orders/multiple";
	            //alert("rootURL is"+rootURL)
	           $.ajax({type: 'POST',
	            	contentType:'application/json',
	            	url:rootURL,
	            	dataType: "json",
	            	data:JSON.stringify(orders),
	            	success: function(data,textStatus, jqXHR)
	            	{
	            	    $("#result").html('Orders placed successfully');
	            	  },
	            	  error: function(jqXHR, textStatus,	errorThrown)
	            	  {
	            	        $("#result").html('ordering beers failed');
	            	   }});
	          return false;
	
    	}
    		</script>
       
	<body><!-- method=post action="PurchaseServlet" onsubmit="return validateForm()" -->
		<form method=post action="PurchaseServlet" onsubmit="return makeAjaxCall()">
			<% 
				String locationSelected=request.getQueryString().split("=")[1];
				List<Location> locations=(List<Location>)session.getAttribute("locations");
				String locationStr="";
				for(Location location:locations)
				{
					locationStr=location.getName();
			%>
				<input type=hidden id='location' value=<%=locationSelected %> />
				<a href=DisplayBeersServlet?location=<%= locationStr%>> <%= locationStr %></a>
			   <%}%>
			<table class="table table-hover table-bordered " >
				<thead>
					<tr>
						<th scope="col">
							Available Beers in <%=locationSelected %>
						</th>
						<th scope="col">
							Choose the ounces 
						</th>
						<th scope="col">
							Select the number of items 
						</th>
						<th scope="col" >
							Select the  items you want to buy
						</th>
					</tr>
				</thead>
					<% 
						request.setAttribute("location", locationSelected);
						List<Beer> beers=(List<Beer>)request.getAttribute("beers"); 
						
						for(int i=0;i<beers.size(); i++)
						{
					%>
				<tbody>
				<tr>
				<td>
					<a href='DisplayBeersServlet'> 
						<%
							out.println(beers.get(i).getStyle()+"<br/>");
							out.println("Description: "+beers.get(i).getDescription()+"<br/>");
							out.println("ABV: "+beers.get(i).getABV()+"<br/>");
							out.println("IBU: "+beers.get(i).getIBU()+"<br/>");
							out.println("$ "+beers.get(i).getPrice1()+" |  ");
							out.println(beers.get(i).getPrice2()+"<br/>  ");
						%>
					</a>
				</td>
				<td>
					
					  <select   name=ounces<%= beers.get(i).getStyle().substring(0,4)%>>
			 			 <option value='6'>6</option>
			  			 <option value='16'>16</option>
			  		  </select>
			  		
			  	</td>
			  	<td> 
			  	
			  			<select  name=noOfItems<%=beers.get(i).getStyle().substring(0,4)%>> 
			  			<% 
			  				for (int j=0;j<10;j++)
			  				{ 
			  			%>
							  			<option value='<%= j%>'>
							  				<%= j%>
							  			</option>
							  			<%} %>
						</select>
						<input type=hidden name='location' value='<%= locationSelected%>' />
					
				</td>
				<td>
				
						<input type='checkbox'  value='<%= beers.get(i).getStyle()%>'  name='itemToBuy'  >
				
				</td>
				</tr>
						<%} %>
						</tbody>
			</table>
		<button class="btn btn-default" id="submit" value='submit'>submit </button>
		</form>
		<div id='result'>
		</div>
		</body>
		
		<!--I need some comments about the jquery on 110...The jQuery get() and post() methods are used to request data from the server with an HTTP GET or POST request.. https://www.w3schools.com/jquery/jquery_ajax_get_post.asp  -->
</html>