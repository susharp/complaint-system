
import factory.connfac;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class cadminpassword extends HttpServlet {

   
           @Override
           protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  {
           String password=request.getParameter("password");
            connfac.getCon().createStatement().executeUpdate("update admin set password='"+password+"' where Adminid='admin'");
           request.setAttribute("status", "Password Changed : "+new Date().toString());
            RequestDispatcher rd=request.getRequestDispatcher("cadminpass.jsp");
            rd.forward(request, response);
        }
        catch(Exception e)
        {
            out.println(e);
        }
    }
        }
    

