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
 * Servlet implementation class UpdateShoppingCart
 */
@WebServlet("/UpdateShoppingCart")
public class UpdateShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String numTickets = request.getParameter("numOfTickets");
		Shows selectedShow = (Shows) session.getAttribute("detailResult");
		Shows addShow = new Shows(selectedShow.getStartTime(),selectedShow.getEndTime(),selectedShow.getMovieName(),selectedShow.getVenue()
				,selectedShow.getThumbnail(),selectedShow.getRating(),selectedShow.getDescription(),selectedShow.getSeatLeft(),selectedShow.getPpSeat()
				,numTickets);
		int subtotal = Integer.parseInt(request.getParameter("ticketPrice")) * Integer.parseInt(request.getParameter("numOfTickets"));
		System.out.print(subtotal);
		ArrayList<Shows> shoppingList = (ArrayList<Shows>)session.getAttribute("shoppingList");
		if(shoppingList == null) {
			shoppingList = new ArrayList<Shows>();
			session.setAttribute("shoppingList", shoppingList);
		}
		System.out.print(numTickets);
		shoppingList.add(addShow);
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
