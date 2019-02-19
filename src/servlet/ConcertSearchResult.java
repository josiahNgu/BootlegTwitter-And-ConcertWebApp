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

import model.Review;
import model.Shows;
import mysql.classes.ConcertsDB;
import mysql.classes.ReviewDB;

/**
 * Servlet implementation class ConcertSearchResult
 */
@WebServlet("/ConcertSearchResult")
public class ConcertSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConcertSearchResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String movieName = request.getParameter("detailButton");
		String venueName = request.getParameter("venueName");
		ConcertsDB detailSearch = new ConcertsDB();
		ReviewDB getComment = new ReviewDB();
		Shows details = detailSearch.detailResult(movieName,venueName);
		//add comment section
		ArrayList<Review> allComment = getComment.getReview(movieName); 
		session.removeAttribute("comments");
		session.setAttribute("detailResult", details);
		session.setAttribute("comments", allComment);
		String address = "ConcertDetailsSelection.jsp";
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
