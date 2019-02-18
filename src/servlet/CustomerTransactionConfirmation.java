package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CreditCards;
import mysql.classes.CreditCardsDB;

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
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String cardNumber = request.getParameter("cardNumber");
		String cardType = request.getParameter("cardType");
		String expiryMonth = request.getParameter("expiryMonth");
		String expiryYear = request.getParameter("expiryYear");
		String cvc = request.getParameter("cvc");
		String billingAddress = request.getParameter("billingAddress");
		CreditCards userCard = new CreditCards(firstName,lastName,cardNumber,cardType,expiryMonth,expiryYear,cvc,billingAddress,"1");
		CreditCardsDB card = new CreditCardsDB();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
