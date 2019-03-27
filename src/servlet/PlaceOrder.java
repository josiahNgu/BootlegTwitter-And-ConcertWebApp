package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Shows;
import model.Users;
import mysql.classes.TransactionDB;

/**
 * Servlet implementation class PlaceOrder
 */
@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("Login.jsp");
		}
		String billingAddress = request.getParameter("billingAddress");
		String cardNumber = request.getParameter("cardNumber");
		Users currentUser = (Users) session.getAttribute("userBean");
		String customerId = Integer.toString(currentUser.getUserId());
		int subtotal = 0;
		ArrayList<Shows> shoppingList = (ArrayList<Shows>)session.getAttribute("shoppingList");
		for(int i =0;i<shoppingList.size();i++) {
			subtotal += shoppingList.get(i).getOrderCost();
		}
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String currentTime  = df.format(date);
		System.out.println("order placed on: " +currentTime);
		TransactionDB transaction = new TransactionDB();
		Random r = new Random( System.currentTimeMillis() );
		int orderNumber = 10000 + r.nextInt(20000);
		String totalCost = Integer.toString(subtotal);
		transaction.addOrder(Integer.toString(orderNumber), customerId, totalCost, currentTime, billingAddress, cardNumber);
		for(int i =0;i<shoppingList.size();i++) {
			transaction.addOrderItems(Integer.toString(orderNumber),shoppingList.get(i).getPerformanceId(), shoppingList.get(i).getNumOfreqSeat());
			String seatNumber = transaction.getSeat(shoppingList.get(i).getPerformanceId());
			int updateNumber = Integer.parseInt(seatNumber)- Integer.parseInt(shoppingList.get(i).getNumOfreqSeat());
			String updateSeat = Integer.toString(updateNumber);
			System.out.print("update Seat " + updateSeat + "performanceId " + shoppingList.get(i).getPerformanceId());
			transaction.decreaseSeat(updateSeat, shoppingList.get(i).getPerformanceId());
		}
		session.removeAttribute("shoppingList");
		session.removeAttribute("subtotal");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
