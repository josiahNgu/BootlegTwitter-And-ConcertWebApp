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
import mysql.classes.TransactionDB;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.removeAttribute("seatNumberError");
		ArrayList<Shows> currentList = (ArrayList<Shows>) session.getAttribute("shoppingList");
		TransactionDB transaction = new TransactionDB();
		ArrayList<String> error =new ArrayList<String>();
		Boolean hasError = false;
		for(int i = 0; i<currentList.size();i++) {
			String seatNumber = transaction.getSeat(currentList.get(i).getPerformanceId());
			if(Integer.parseInt(currentList.get(i).getNumOfreqSeat())> Integer.parseInt(seatNumber)) {
				error.add(currentList.get(i).getMovieName() + " only have " + currentList.get(i).getSeatLeft() + " seat left."); 
				System.out.println(currentList.get(i).getMovieName()+" not enough seat ");
				hasError = true;
			}
		}
		if(hasError) {
			session.setAttribute("seatNumberError", error);
			String address = "ViewAndCheckoutShoppingCart.jsp";
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}else {
			String address = "CustomerTransaction.jsp";
			session.setAttribute("seatNumberError", error);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
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
