/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import factory.connfac;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author SUnAndAn
 */
public class cregister extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
           protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String cname=request.getParameter("cname");
            String textarea=request.getParameter("textarea");
            String q="insert into complaints values(?,?,NOW())";
             PreparedStatement ps=  connfac.getCon().prepareStatement(q);
              ps.setString(2, cname);
              ps.setString(3, textarea);
               int i= ps.executeUpdate();
            if(i>=1)
            {
            request.setAttribute("status", "thank you for your complaint ");
            
            }
           else
            {
            request.setAttribute("status", "Sorry, Server Problem !");
            
            }
            RequestDispatcher rd=request.getRequestDispatcher("complaint.jsp");
            rd.forward(request, response);
           
            
            
         
        }
        catch(Exception e)
        {
        out.println(e);
        }
            
            
            
             
            
        }
    }

  