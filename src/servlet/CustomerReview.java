package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import model.Users;
import mysql.classes.ReviewDB;

/**
 * Servlet implementation class CustomerReview
 */
@WebServlet("/CustomerReview")
public class CustomerReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ReviewDB review = new ReviewDB();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate currentDate = LocalDate.now();
		String comment = request.getParameter("comment");
		String rating = request.getParameter("rating");
		String date = dtf.format(currentDate);
		int wordCount = comment.length();
		Shows selectedShow = (Shows) session.getAttribute("detailResult");
		Users currentUser = (Users) session.getAttribute("userBean");
		String movieId = review.getMovieId(selectedShow.getMovieName());
		String userId = Integer.toString(currentUser.getUserId());
		int transactionStatus = 0;
		ArrayList<Review> allComment = null;
		System.out.print(comment + "Rating: " + rating + "wordCount: " + wordCount + date + selectedShow.getMovieName() +"user id " + userId);
		
		if(wordCount <= 255) {
			transactionStatus = 1;
			review.addReview(comment, userId, movieId, date, rating);
			allComment = review.getReview(selectedShow.getMovieName());
		}
		session.setAttribute("movieName", selectedShow.getMovieName());
		session.setAttribute("comments", allComment);
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
