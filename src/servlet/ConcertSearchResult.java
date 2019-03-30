package servlet;

import java.io.IOException;
import java.util.ArrayList;

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
	static Logger log 
    = Logger.getLogger(Login.class.getName());
       
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
		ServletContext sc = this.getServletContext();
		String propFilePath = sc.getRealPath("/WEB-INF/lib/log4j.properties");
		PropertyConfigurator.configure(propFilePath);
		
		try {
			HttpSession session = request.getSession();
			String movieName = request.getParameter("detailButton");
			String venueName = request.getParameter("venueName");
			System.out.println("Concert Search Result servlet: selected movie session: " + movieName);
			ConcertsDB detailSearch = new ConcertsDB();
			ReviewDB getComment = new ReviewDB();
			Shows details = detailSearch.detailResult(movieName,venueName);
			ArrayList<Review> allComment ;
			if(movieName == null ) {
				allComment = getComment.getReview(movieName);
			}else {
				allComment = getComment.getReview(movieName);
			}
			int rating =0;
			int overallRating =1;
			for(int i = 0 ;i<allComment.size();i++) {
				if(allComment.get(i).getRating() != null)
				 rating += Integer.parseInt(allComment.get(i).getRating());			
			}
			overallRating = (rating/allComment.size());
			System.out.println("overallRating for this perfomance: "+ overallRating);
			session.removeAttribute("overallRating");
			session.setAttribute("overallRating", overallRating);
			session.removeAttribute("comments");
			session.setAttribute("detailResult", details);
			session.setAttribute("comments", allComment);
			session.setAttribute("movieName", movieName);

			String address = "ConcertDetailsSelection.jsp";
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} catch (Exception e) {
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
