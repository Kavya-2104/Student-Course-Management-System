<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    String username = (String)session.getAttribute("username");
    if(username == null)
    {
    	response.sendRedirect("asessionexpiry.html");
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>EP Project</title>
<link type="text/css"  rel="stylesheet"  href="style.css"/>
</head>
<body bgcolor="lightblue">

<h2 align="center">Enterprise Resource Planning (ERP) Project</h2>
  <hr color="black"/><hr color="black"/>
  <br/>
  
<a href="adminhome.jsp">Home</a>&nbsp;&nbsp;&nbsp;
<a href="viewallemps.jsp">View All Employees</a>&nbsp;&nbsp;&nbsp;
<a href="addproduct.jsp">Add Product</a>&nbsp;&nbsp;&nbsp;
<a href="viewallproducts.jsp">View All Products</a>&nbsp;&nbsp;&nbsp;
<a href="adminlogout.jsp">Logout</a>&nbsp;&nbsp;&nbsp;


<br><br>

Welcome <%=username%>

<br>

<h2 align="center"><u>Add Product</u></h2>
<form method="post"  action="insertproduct" enctype="multipart/form-data">
<table align="center">
<tr><td></td></tr>
<tr>
    <td><b>Category</b></td>
    <td>
        <select name="pcategory" required>
        <option value="">---Select---</option>
        <option value="Electronic Gadgets">Electronic Gadgets</option>
         <option value="Clothing">Clothing</option>
        </select>
    </td>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
    <td><b>Name</b></td>
    <td>
        <input type="text" name="pname" required>
    </td>
</tr>
<tr><td></td></tr>
<tr>
    <td><b>Cost</b></td>
    <td>
        <input type="number" name="pcost" required>
    </td>
</tr>
<tr><td></td></tr>
<tr>
    <td><b>Description</b></td>
    <td>
        <textarea name="pdesc"></textarea>
    </td>
</tr>
<tr><td></td></tr>
<tr>
    <td><b>Image</b></td>
    <td>
         <input type="file" name="pimage" required>
    </td>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr align="center">
    <td>
        <input type="submit" value="Add" required>
    </td>
    <td>
        <input type="reset" value="Clear" required>
    </td>
</tr>
</table>

</form>

</body>
</html>