package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bank;
import mysql.BankDB;
/**
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String cardNumber = request.getParameter("cardNumber");
		String cardType = request.getParameter("cardType");
		String expiryMonth = request.getParameter("expiryMonth");
		String expiryYear = request.getParameter("expiryYear");
		String cvc = request.getParameter("cvc");
		String billingAddress = request.getParameter("billingAddress");
		String userId = request.getParameter("userId");
		String subtotal = request.getParameter("subtotal");
		int orderTotal = Integer.parseInt(subtotal);
		String balance =Integer.toString(orderTotal +1000);
		BankDB transaction = new BankDB();
		int transactionStatus= 0;
		String remainingBalance = transaction.creditCardsValid(cardNumber, orderTotal);
		Boolean newCard = true;
		newCard = transaction.cardExisted(cardNumber);
		Bank userCard = new Bank(firstName,lastName,cardNumber,cardType,expiryMonth,expiryYear,cvc,billingAddress,userId);
		System.out.println("Bank: " +"fName: " + firstName + "CNumber: " +cardNumber + "UserId : " + userId);
		if(newCard) {
			transactionStatus = 1;
			transaction.CreditCards(userCard);
			transaction.updateBalance(cardNumber, balance);
		}
		else if (!newCard && Double.parseDouble(remainingBalance)>0) {
			transactionStatus = 1;
			transaction.updateBalance(userCard.getCardNumber(),remainingBalance);
		}
		else {
			transactionStatus= 0 ;
			System.out.println("Not enough fund" + "subtotal: " + subtotal);
		}
		PrintWriter out = response.getWriter(); 
		out.println(transactionStatus);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
