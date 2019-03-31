package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Users;
import mysql.classes.UsersDB;
import mysql.classes.VenueDB;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String oldPassword = request.getParameter("password");
		String password = request.getParameter("password1");

		boolean userExists = false;
		boolean userPasswordMatches = false;
		
		UsersDB aUserDB = new UsersDB();

		PrintWriter out = response.getWriter();

		userExists = aUserDB.validateUserByUsername(userName);
		userPasswordMatches = aUserDB.validateUser(userName,oldPassword);

		if(userExists && userPasswordMatches) {
			// change the password
			boolean status = aUserDB.changePassword(userName, password);

			if(status) {
				String address = "Login.jsp";
				RequestDispatcher dispatcher =
						request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			}
			else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Password change unsuccessful');");
				out.println("location='ChangePassword.jsp';");
				out.println("</script>");	
			}

		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Username/Password Incorrect!');");
			out.println("location='ChangePassword.jsp';");
			out.println("</script>");		
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
