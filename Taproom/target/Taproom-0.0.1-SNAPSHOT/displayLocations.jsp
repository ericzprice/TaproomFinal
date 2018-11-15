<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List,taproom.model.Location"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Locations</title>
</head>
<body>
<table class="table table-hover" border=1>
	<thead>
		<tr>
			<th scope="col">
				Locations
			</th>
		</tr>
	</thead>
<% List<Location> locations=(List<Location> )session.getAttribute("locations"); 
for(int i=0;i<locations.size(); i++)
{
%>
<tbody>
<tr>
	<td>
		<a href=DisplayBeersServlet?location=<%=locations.get(i).getName() %>> 
			<%
				out.println(locations.get(i).getName());
			}%>
		</a>
	</td>
</tr>
</tbody>
</table>
</body>
</html>