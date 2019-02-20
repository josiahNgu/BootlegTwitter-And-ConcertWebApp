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

import model.Orders;
import mysql.classes.OrdersDB;

/**
 * Servlet implementation class ManageOrder
 */
@WebServlet("/ManageOrder")
public class ManageOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String orderNum = request.getParameter("orderNumber");
		
		OrdersDB aOrderDB = new OrdersDB();
		System.out.println("orderId: "+orderNum);
		
		if(orderNum != null) {
			int orderId = Integer.parseInt(orderNum);

			ArrayList<Orders> orderResult = aOrderDB.editOrders(orderId);
			session.setAttribute("manageOrder", orderResult);
			String address = "ManageOrder.jsp";
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(address);
			dispatcher.forward(request, response);	

		}else {
			System.err.println("orderId not found!");
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
