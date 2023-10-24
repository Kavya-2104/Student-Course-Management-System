package ProgressFiles;

import dbconnection.dbconn;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileProgressUpload extends HttpServlet 
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
 
        final Part filePart = request.getPart("pfile");
       
        InputStream pdfFileBytes = null;
        
        final PrintWriter writer = response.getWriter();
        
        HttpSession session=request.getSession(false);  
        
        String goalid=(String)session.getAttribute("goalid");  
        
        String fid=(String)session.getAttribute("fid");  
        
        
        try
        {
            
        if (!filePart.getContentType().equals("application/pdf"))
            {
         
        
        	response.sendRedirect("fileuploaderror1.html");
        
    
                       
                       
                       return;
            }
 
           else if (filePart.getSize()>1048576 ) { //2mb
               {
               
                           
            	  	response.sendRedirect("fileuploaderror2.html");
                    
              return;
               }
           }    
           
         pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data
 
            final byte[] bytes = new byte[pdfFileBytes.available()];
             pdfFileBytes.read(bytes);  //Storing the binary data in bytes array.
 
         
         Connection con = dbconn.getconn();
         PreparedStatement statement = null;
         String sql = " insert into progressfiles(goal_id,facultyid,progressfile) values(?,?,?)  ";
         statement = con.prepareStatement(sql);
            
          statement.setString(1, goalid);
          statement.setString(2, fid);
          statement.setBytes(3,bytes);  
       
                    
            
          
             
        int success =  statement.executeUpdate();
         
               if(success>=1)
               {
  
                 	response.sendRedirect("fileupload.html");
                    
               }
                 
               
               
               con.close(); 
 
          
         
         
        }
        catch (SQLException e) 
        {
           writer.println(e.getMessage());
        }
         
    }

}
