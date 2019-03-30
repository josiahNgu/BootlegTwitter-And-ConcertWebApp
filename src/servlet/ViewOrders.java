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

import model.Orders;
import model.Shows;
import model.Users;
import mysql.classes.OrdersDB;

/**
 * Servlet implementation class ViewOrders
 */
@WebServlet("/ViewOrders")
public class ViewOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger log = Logger.getLogger(Login.class.getName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext sc = this.getServletContext();
		String propFilePath = sc.getRealPath("/WEB-INF/lib/log4j.properties");
		PropertyConfigurator.configure(propFilePath);
		
		try {
			HttpSession session = request.getSession();
			Users bean = (Users) session.getAttribute("userBean");
			int userId = bean.getUserId();
			System.out.println("customer Id: " + userId);
			OrdersDB aOrderDB = new OrdersDB();
			if (userId > 0) {
				ArrayList<Orders> orderResult = aOrderDB.getOrders(userId);
				session.setAttribute("orderResult", orderResult);
				String address = "ViewOrders.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			} else {
				System.err.println("userId not found!");
			} 
		} catch (Exception e) {
			// TODO: handle exception
			log.error("This is a error message.",e);

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
