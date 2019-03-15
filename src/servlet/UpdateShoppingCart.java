package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		System.out.println("number of tickets : "+numTickets);
		int transactionStatus = 1;
		Shows selectedShow = (Shows) session.getAttribute("detailResult");
		int orderCost = Integer.parseInt(numTickets) * Integer.parseInt(selectedShow.getPpSeat());
		Shows addShow = new Shows(selectedShow.getStartTime(),selectedShow.getEndTime(),selectedShow.getMovieName(),selectedShow.getVenue()
				,selectedShow.getThumbnail(),selectedShow.getRating(),selectedShow.getDescription(),selectedShow.getSeatLeft(),selectedShow.getPpSeat()
				,numTickets,orderCost,selectedShow.getPerformanceId());
		ArrayList<Shows> shoppingList = (ArrayList<Shows>)session.getAttribute("shoppingList");
		if(shoppingList == null) {
			shoppingList = new ArrayList<Shows>();
			session.setAttribute("shoppingList", shoppingList);
		}
		shoppingList.add(addShow);
		int subtotal = 0;
		for(int i =0;i<shoppingList.size();i++) {
			subtotal += shoppingList.get(i).getOrderCost();
		}
		session.setAttribute("subtotal", subtotal);
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
