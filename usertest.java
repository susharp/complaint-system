/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import factory.connfac;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SUnAndAn
 */
public class usertest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        checkValidity();
        try {
            String status = null;
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Connection con = connfac.getCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from students");
            String uname = null;
            boolean valid = false;
            while (rs.next()) {
                if (rs.getString(3).equalsIgnoreCase(email) && rs.getString(4).equals(password)) {
                    valid = true;
                    uname = rs.getString(3);

                }
            }
            if (valid == true) {

                // HttpSession session=request.getSession();
                // session.setAttribute("username", uname);
                response.sendRedirect("complaint.jsp");
            } else {
                request.setAttribute("status", "Invalid User Name or Password");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);

            }

        } catch (Exception e) {
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

    void checkValidity() {
        Date cDate = new Date();
        int CurrYear = cDate.getYear();
        DateFormat currentDate = new SimpleDateFormat("MM/dd");
        String currdate = currentDate.format(cDate).toString();
        System.out.println(currdate);

        String SavedPostDate = "02/22";  //################################ Date received from DB#################################
        System.out.println(SavedPostDate);
        String d1[] = currdate.split("/");
        String d2[] = SavedPostDate.split("/");
        System.out.println(Integer.parseInt(d1[0]));
        System.out.println(Integer.parseInt(d2[0]));

        if (d1[0].equals(d2[0])) {
            System.out.println(Math.abs(Integer.parseInt(d1[1]) - (Integer.parseInt(d2[1]))));
            if (Math.abs(Integer.parseInt(d1[1]) - (Integer.parseInt(d2[1]))) > 7) {
                System.out.println("Complaint request expired!");
            } else {
                System.out.println("Complain still in process!!");
            }
        } else {
            int m = Integer.parseInt(d2[0]);
            switch (m) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (Math.abs(31 - (Integer.parseInt(d2[1]))) > 7) {
                        System.out.println("31 Complaint request expired!");
                    } else {
                        System.out.println("31Complain still in process!!");
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (Math.abs(30 - (Integer.parseInt(d2[1]))+(Integer.parseInt(d1[1]))) > 7) {
                        System.out.println("30 Complaint Expired!!");
                    } else {
                        System.out.println("30 Complain still in process!!");
                    }
                    break;
                case 2:
                if (((CurrYear % 4 == 0) && 
                     !(CurrYear % 100 == 0))
                     || (CurrYear % 400 == 0))
                     if (Math.abs(29 - (Integer.parseInt(d2[1]))+(Integer.parseInt(d1[1]))) > 7) {
                        System.out.println("29 Complaint Expired!!");
                    } else {
                        System.out.println("29 Complain still in process!!");
                    }
                else
                     if (Math.abs(28 - (Integer.parseInt(d2[1]))+(Integer.parseInt(d1[1]))) > 7) {
                        System.out.println("28 Complaint Expired!!");
                    } else {
                        System.out.println("28 Complain still in process!!");
                    }
                break;    
                default:
                    System.out.println("Invalid month...............");
                    break;
            }
        }

    }
}
