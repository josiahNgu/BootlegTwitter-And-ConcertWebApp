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

import model.Shows;

/**
 * Servlet implementation class InvalidateShoppingList
 */
@WebServlet("/DeleteItemShoppingCart")
public class DeleteItemShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log 
    = Logger.getLogger(Login.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteItemShoppingCart() {
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
			ArrayList<Shows> currentList = (ArrayList<Shows>) session.getAttribute("shoppingList");
			String index = request.getParameter("index");
			currentList.remove(Integer.parseInt(index));
			int subtotal = 0;
			for(int i =0;i<currentList.size();i++) {
				subtotal += currentList.get(i).getOrderCost();
			}
			if(subtotal == 0 ) {
				session.removeAttribute("seatNumberError");
				session.removeAttribute("seatNumberAlertColor");
			}
			session.setAttribute("subtotal", subtotal);
			session.setAttribute("shoppingList", currentList);
			String address = "ViewAndCheckoutShoppingCart.jsp";
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
