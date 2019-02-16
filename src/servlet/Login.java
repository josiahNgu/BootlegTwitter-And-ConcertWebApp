package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Users;
import mysql.classes.UsersDB;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		boolean userExists = false;
		boolean userPasswordMatches = false;
		
		Users aUser = new Users();
		
		UsersDB aUserDB = new UsersDB();
		
		userExists = aUserDB.validateUserByUsername(userName);
		userPasswordMatches = aUserDB.validateUser(userName,password);
		
		if(userExists && userPasswordMatches) {
			aUser = aUserDB.getUser(userName);
			
			HttpSession session = request.getSession();
		    session.setAttribute("userBean", aUser);
		    
		    String address = "CustomerHomepage.jsp";
		    RequestDispatcher dispatcher =
		      request.getRequestDispatcher(address);
		    dispatcher.forward(request, response);
			
		} else {
			response.sendRedirect("Register.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
