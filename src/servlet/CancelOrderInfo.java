package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import model.Orders;
import mysql.classes.OrdersDB;

/**
 * Servlet implementation class CancelOrder
 */
@WebServlet("/CancelOrderInfo")
public class CancelOrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger log = Logger.getLogger(Login.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderInfo() {
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
			String orderItemNum = request.getParameter("orderItemId");
			
			OrdersDB aOrderDB = new OrdersDB();
			
			if(orderItemNum != null) {
				int orderItemId = Integer.parseInt(orderItemNum);

				Orders result = aOrderDB.cancelOrderInfo(orderItemId);
				request.setAttribute("cancelOrderInfo", result);
				String address = "CancelOrder.jsp";
				RequestDispatcher dispatcher =
						request.getRequestDispatcher(address);
				dispatcher.forward(request, response);	

			}else {
				System.err.println("orderItemId not found!");
			}
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
