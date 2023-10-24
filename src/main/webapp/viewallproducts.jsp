<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"   import="java.sql.*,java.util.List"  %>
<%@page import="com.klef.ep.services.AdminService"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.models.Employee"%>
<%@page import="com.klef.ep.models.Product"%>
<%@page import="com.klef.ep.models.Admin"%>
<!DOCTYPE html>
<html>
<head>
<title>EP PROJECT</title>
<link type="text/css"  rel="stylesheet"  href="style.css"/>
</head>

<body>


 <h2 align="center">Enterprise Resource Planning (ERP) Project</h2>
  <hr color="black"/><hr color="black"/>
  <br/>

<a href="adminhome.jsp">Home</a>&nbsp;&nbsp;&nbsp;
<a href="viewallemps.jsp">View All Employees</a>&nbsp;&nbsp;&nbsp;
<a href="addproduct.jsp">Add Product</a>&nbsp;&nbsp;&nbsp;
<a href="viewallproducts.jsp">View All Products</a>&nbsp;&nbsp;&nbsp;
<a href="adminlogout.jsp">Logout</a>&nbsp;&nbsp;&nbsp;

<br>

<h2 align="center"><u>View All Products</u></h2>
<table align="center"  border=2>
<tr>
<th>ID</th>
<th>Category</th>
<th>Name</th>
<th>Cost</th>
<th>Description</th>
<th>Image</th>
<th>Action</th>
</tr>
<%
InitialContext context = new InitialContext();
AdminService adminService = (AdminService) context.lookup("java:global/EPPROJECT/AdminServiceImpl!com.klef.ep.services.AdminService");

List<Product> productlist = adminService.ViewAllProducts();

for(Product product : productlist)
{
	  %>
	    <tr>
	    	  <td><%=product.getId()%></td>
	    	  <td><%=product.getCategory()%></td>
	    	  <td><%=product.getName()%></td>
	    	  <td><%=product.getCost()%></td>
	    	  <td><%=product.getDescription()%></td>
	    	  <td><img src="viewproductbyid.jsp?pid=<%=product.getId()%>"  width="100%"  height="100%" /></td>   
	    	   <td></td>	
	    </tr>
	  
	  <%
}
%>
</table>


</body>
</html>