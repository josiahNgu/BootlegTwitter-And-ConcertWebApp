package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CreditCards;
import model.Shows;
import model.Users;
import mysql.classes.TransactionDB;

/**
 * Servlet implementation class CustomerTransactionConfirmation
 */
@WebServlet("/CustomerTransactionConfirmation")
public class CustomerTransactionConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerTransactionConfirmation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("confirmOrder",session.getAttribute("shoppingList"));
		session.removeAttribute("shoppingList");
//		isubtotal = request.getAttribute("subtotal");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String cardNumber = request.getParameter("cardNumber");
		String cardType = request.getParameter("cardType");
		String expiryMonth = request.getParameter("expiryMonth");
		String expiryYear = request.getParameter("expiryYear");
		String cvc = request.getParameter("cvc");
		String billingAddress = request.getParameter("billingAddress");
		Users currentUser = (Users) session.getAttribute("userBean");
		String userId = Integer.toString(currentUser.getUserId());
		CreditCards userCard = new CreditCards(firstName,lastName,cardNumber,cardType,expiryMonth,expiryYear,cvc,billingAddress,userId);
		System.out.print(expiryYear);
		
		Random r = new Random( System.currentTimeMillis() );
	    int orderNumber = 10000 + r.nextInt(20000);
		TransactionDB transaction = new TransactionDB();
//		transaction.CreditCards(userCard);
//		System.out.print(orderNumber+ " "+subtotal);
//		transaction.addOrder(orderNumber, customerId, totalCost, orderDate, billingAddress, creditcard);
//		transaction.addOrderItems(orderNumber, performanceId, quantity);
		
		session.setAttribute("subtotal", 0);
		String address = "CustomerTransactionConfirmation.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
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
