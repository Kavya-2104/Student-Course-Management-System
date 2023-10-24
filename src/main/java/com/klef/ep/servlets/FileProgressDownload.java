package com.klef.ep.servlets;

import dbconnection.dbconn;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FileProgressDownload extends HttpServlet 
{
    private static final int BUFFER_SIZE = 4096;   
    
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException 
    {
        response.setContentType("APPLICATION/OCTET-STREAM");   
        
       
       String goalid = request.getParameter("pid");
       String fid = request.getParameter("facultyid");
       
   String filename=fid+"_"+goalid+".pdf";
   response.setHeader("Content-Disposition","attachment;filename=\"" + filename + "\"");

   
   
       
       
       HttpSession session = request.getSession();
       
       session.setAttribute("fid", fid);
       
       System.out.println(goalid);
       
       try
       {
           Connection con = dbconn.getconn();
          
           String sql = "SELECT * FROM progressfiles WHERE progress_id = ? and facultyid = ?      ";
           PreparedStatement statement = con.prepareStatement(sql);
           statement.setString(1, goalid);
           statement.setString(2, fid);
           
           ResultSet rs  = statement.executeQuery();
           
           if(rs.next())
           {
               Blob blob = rs.getBlob("progressfile");
               InputStream inputstream  = blob.getBinaryStream();
               int fileLength = inputstream.available();
           
               System.out.println(fileLength);

              
               OutputStream outStream = response.getOutputStream();
               
               byte[] buffer = new byte[BUFFER_SIZE];
               
               
               int bytesRead = -1;
                 
                while ((bytesRead = inputstream.read(buffer)) != -1) 
                {
                    outStream.write(buffer, 0, bytesRead);
                }
                 
                inputstream.close();
                outStream.close();     

           }
           
           else
           {
               response.sendRedirect("filedownloaderror.html");
           }
           
           
           
       }
       catch(SQLException e)
       {
           response.getWriter().print("SQL Error: " + e.getMessage());
       }
       
       
       
    }


}
