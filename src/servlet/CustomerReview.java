package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String movieName = selectedShow.getMovieName();
		String movieId = review.getMovieId(movieName);
		String userId = Integer.toString(currentUser.getUserId());
		String condition = "alert-danger";
		String header ="Error Occured!";
		String content = "Your comment was not submited. Comment length exceeded the max length of 255 characters. Please try again later.";
		session.removeAttribute("reviewAlertColor");
		session.removeAttribute("reviewAlertHeader");
		session.removeAttribute("reviewAlertContent");
		
		
		System.out.print(comment + wordCount + date + selectedShow.getMovieName() +"user id " + userId);
		if(wordCount <= 255) {
			condition = "alert-success";
			header = "Success!";
			content = "Your comment was submitted succesfully! Thank you for your response.";
			review.addReview(comment, userId, movieId, date, rating);
		}
		
		session.setAttribute("reviewAlertColor", condition);
		session.setAttribute("reviewAlertHeader",header);
		session.setAttribute("reviewAlertContent",content);

		String address = "ReviewConfirmation.jsp";
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
