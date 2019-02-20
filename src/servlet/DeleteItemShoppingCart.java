package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Shows;

/**
 * Servlet implementation class InvalidateShoppingList
 */
@WebServlet("/DeleteItemShoppingCart")
public class DeleteItemShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		}
		session.setAttribute("subtotal", subtotal);
		session.setAttribute("shoppingList", currentList);
		String address = "ViewAndCheckoutShoppingCart.jsp";
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
