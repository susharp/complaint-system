import factory.connfac;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class sreg extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            
                String fname=request.getParameter("fname");
            
                String lname=request.getParameter("lname");
                                
                 
                  String email=request.getParameter("email");
                                     
            String password=request.getParameter("password");
             String dept=request.getParameter("dept");
              String branch=request.getParameter("branch");
            String q="insert into students values(?,?,?,?,?,?)";
            
            PreparedStatement ps=  connfac.getCon().prepareStatement(q);
            ps.setString(1, fname);
            
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, dept);
            ps.setString(6, branch);
            
            
           
           int i= ps.executeUpdate();
            if(i>=1)
            {
            request.setAttribute("status", "Request have been sent to admin will be Process within 24 haur ");
            
            }
           else
            {
            request.setAttribute("status", "Sorry, Server Problem !");
            
            }
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
           
            
            
         
        }
        catch(Exception e)
        {
        out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
