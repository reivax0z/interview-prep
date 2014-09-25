package reivax.norac.interviewprep.webapp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CountriesServlet
 */
public class QuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public QuestionsServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processData(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processData(request, response);
	}

	private void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> questions = new ArrayList<String>();
		questions.add("Why PwC?");
		questions.add("Why Digital Change?");
		questions.add("Why working in consulting?");
		questions.add("How did you manage a difficult person?");
		questions.add("Any extracurricular activity?");
		questions.add("Current news related to Digital Change?");
		questions.add("Where do you see yourself in 3 years from now?");
		questions.add("What do you expect from the first year at PwC?");
		questions.add("What core value of PwC describes you the most accurately?");
		questions.add("What is your most recent major achievement?");
		questions.add("What is your weakness?");
		questions.add("What is your quality?");
		questions.add("Give me an example of a tight relationship with your previous employer?");
		questions.add("Tell me about a time you showed leadership");
		questions.add("Tell me about a time you showed courage");
		questions.add("Tell me about a challenging situation you successfully overtook");
		questions.add("Tell me about a failure you faced. How did you fix it?");
		questions.add("Tell me about a time when you worked under pressure?");
		
		Collections.shuffle(questions);
		
		// Forward the info to the appropriate JSP
		request.setAttribute("questions", questions);
				
		request.getRequestDispatcher("DisplayQuestions.jsp").forward(request, response);
	}

}
