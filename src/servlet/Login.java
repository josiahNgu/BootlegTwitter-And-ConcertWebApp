package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import model.Users;
import mysql.classes.UsersDB;
import mysql.classes.VenueDB;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log 
    = Logger.getLogger(Login.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = this.getServletContext();
		String propFilePath = sc.getRealPath("/WEB-INF/lib/log4j.properties");
		PropertyConfigurator.configure(propFilePath);
		
		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			boolean userExists = false;
			boolean userPasswordMatches = false;
			
			Users aUser = new Users();
			
			UsersDB aUserDB = new UsersDB();
			
			VenueDB venueDB = new VenueDB();
			ArrayList<String> venue = venueDB.getVenue();


			userExists = aUserDB.validateUserByUsername(userName);
			userPasswordMatches = aUserDB.validateUser(userName,password);
			
			if(userExists && userPasswordMatches) {
				//this now returns userId as well
				aUser = aUserDB.getUser(userName);
				
				HttpSession session = request.getSession();
			    session.setAttribute("userBean", aUser);
			    // add venue to session
				session.setAttribute("venue", venue);
			    String address = "CustomerHomepage.jsp";
			    RequestDispatcher dispatcher =
			      request.getRequestDispatcher(address);
			    dispatcher.forward(request, response);
				
			} else {
				response.sendRedirect("Register.jsp");
			}
		}
		catch (Exception e) {
			log.error("This is a error message.",e);

		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
