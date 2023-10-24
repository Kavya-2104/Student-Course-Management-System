package com.klef.ep.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.klef.ep.models.Product;
import com.klef.ep.services.AdminService;


@WebServlet("/insertproduct")
@MultipartConfig
public class InsertProductServlet extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try
		{
			String pcategory = request.getParameter("pcategory");
			System.out.println(pcategory);
			String pname = request.getParameter("pname");
			String pcost = request.getParameter("pcost");
			double cost = Double.parseDouble(pcost);
			String pdesc = request.getParameter("pdesc");
			Part file = request.getPart("pimage");
			
			System.out.println(file);
			
			InitialContext context = new InitialContext();
			AdminService adminService = (AdminService) context.lookup("java:global/EPPROJECT/AdminServiceImpl!com.klef.ep.services.AdminService");
			
			
	        InputStream inputStream = file.getInputStream();
	        Blob blob = new javax.sql.rowset.serial.SerialBlob(getBytesFromInputStream(inputStream));


		Product product= new Product();
		product.setCategory(pcategory);
		product.setName(pname);
		product.setCost(cost);
		product.setDescription(pdesc);
		product.setImagedata(blob);

		System.out.print(product);

		adminService.AddProduct(product);

		response.sendRedirect("addproductsuccess.jsp");
		}
		catch(Exception e)
		{
			out.print(e);
		}
	}

	
	
	
	
	 // Method to convert InputStream to byte array
    private byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

}
