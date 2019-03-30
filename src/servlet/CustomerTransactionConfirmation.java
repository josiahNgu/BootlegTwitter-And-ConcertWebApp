package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

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

	static Logger log 
    = Logger.getLogger(Login.class.getName());
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
		ServletContext sc = this.getServletContext();
		String propFilePath = sc.getRealPath("/WEB-INF/lib/log4j.properties");
		PropertyConfigurator.configure(propFilePath);
		
		try {
			HttpSession session = request.getSession();
			session.setAttribute("confirmOrder",session.getAttribute("shoppingList"));
			ArrayList<Shows> shoppingList = (ArrayList<Shows>)session.getAttribute("shoppingList");
			session.removeAttribute("orderNumber");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String cardNumber = request.getParameter("cardNumber");
			String cardType = request.getParameter("cardType");
			String expiryMonth = request.getParameter("expiryMonth");
			String expiryYear = request.getParameter("expiryYear");
			String cvc = request.getParameter("cvc");
			String billingAddress = request.getParameter("billingAddress");
			Users currentUser = (Users) session.getAttribute("userBean");
			String customerId = Integer.toString(currentUser.getUserId());
			CreditCards userCard = new CreditCards(firstName,lastName,cardNumber,cardType,expiryMonth,expiryYear,cvc,billingAddress,customerId);
			int subtotal = 0;
			for(int i =0;i<shoppingList.size();i++) {
				subtotal += shoppingList.get(i).getOrderCost();
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate currentDate = LocalDate.now();
			String date = dtf.format(currentDate);
			TransactionDB transaction = new TransactionDB();
			Random r = new Random( System.currentTimeMillis() );
			int orderNumber = 10000 + r.nextInt(20000);
			String totalCost = Integer.toString(subtotal);
			Boolean newCard = true;
			newCard = transaction.cardExisted(cardNumber);
			System.out.print(newCard);
			String remainingBalance = transaction.creditCardsValid(cardNumber, subtotal);
			System.out.print(remainingBalance);
			
			//set session Attribute for alert details
			session.removeAttribute("orderNumber");
			session.removeAttribute("transactionAlertColor");
			session.removeAttribute("transactionAlertContent");
			String transactionAlertHeader = "Success!";
			String transactionAlertColor ="alert-success";
			String transactionOrderNumber ="";
			String transactionAlertContent="was placed successfully!";
			
			if(newCard) {
				transaction.CreditCards(userCard);
				transaction.addOrder(Integer.toString(orderNumber), customerId, totalCost, date, billingAddress, userCard.getCardNumber());
				for(int i =0;i<shoppingList.size();i++) {
					transaction.addOrderItems(Integer.toString(orderNumber),shoppingList.get(i).getPerformanceId(), shoppingList.get(i).getNumOfreqSeat());
					String seatNumber = transaction.getSeat(shoppingList.get(i).getPerformanceId());
					int updateNumber = Integer.parseInt(seatNumber)- Integer.parseInt(shoppingList.get(i).getNumOfreqSeat());
					String updateSeat = Integer.toString(updateNumber);
					System.out.println("seatNumber " + seatNumber + "update Seat number " + updateSeat + "performanceId " + shoppingList.get(i).getPerformanceId());
					transaction.decreaseSeat(updateSeat, shoppingList.get(i).getPerformanceId());
				}
			}
			else if(Double.parseDouble(remainingBalance)>0 && newCard == false) {
			//give balance to delete
				transaction.updateBalance(userCard.getCardNumber(),remainingBalance);
				transaction.addOrder(Integer.toString(orderNumber), customerId, totalCost, date, billingAddress, userCard.getCardNumber());
				for(int i =0;i<shoppingList.size();i++) {
					transaction.addOrderItems(Integer.toString(orderNumber),shoppingList.get(i).getPerformanceId(), shoppingList.get(i).getNumOfreqSeat());
					String seatNumber = transaction.getSeat(shoppingList.get(i).getPerformanceId());
					int updateNumber = Integer.parseInt(seatNumber)- Integer.parseInt(shoppingList.get(i).getNumOfreqSeat());
					String updateSeat = Integer.toString(updateNumber);
					System.out.print("update Seat " + updateSeat + "performanceId " + shoppingList.get(i).getPerformanceId());
					transaction.decreaseSeat(updateSeat, shoppingList.get(i).getPerformanceId());
				}
				transactionOrderNumber = "#" + Integer.toString(orderNumber);
			} else {
				System.out.println("Not enough fund" + "subtotal: " + subtotal);
				transactionAlertColor ="alert-danger";
				transactionAlertContent="An Error has occured while processing your order. Please try again later.";
				transactionAlertHeader = "Order Not Placed!";
				session.setAttribute("confirmOrder", "");

			}
			System.out.println(orderNumber);
			session.setAttribute("orderNumber", transactionOrderNumber);
			session.setAttribute("transactionAlertColor", transactionAlertColor);
			session.setAttribute("transactionAlertHeader", transactionAlertHeader);
			session.setAttribute("transactionAlertContent", transactionAlertContent);
			session.removeAttribute("shoppingList");
			session.removeAttribute("subtotal");
			String address = "CustomerTransactionConfirmation.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
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
