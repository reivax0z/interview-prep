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

		if(Model.getInstance().getCurrentQuestionsList() == null 
				|| Model.getInstance().getCurrentQuestionsList().isEmpty()){
		
			List<Entry> questions = new ArrayList<Entry>();
			questions.add(new Entry("Why PwC?", 20));
			questions.add(new Entry("Why Digital Change?"));
			questions.add(new Entry("Why working in consulting?"));
			questions.add(new Entry("How did you manage a difficult person?", 30));
			questions.add(new Entry("Any extracurricular activity?"));
			questions.add(new Entry("Current news related to Digital Change?"));
			questions.add(new Entry("Where do you see yourself in 3 years from now?"));
			questions.add(new Entry("What do you expect from the first year at PwC?"));
			questions.add(new Entry("What core value of PwC describes you the most accurately?", 25));
			questions.add(new Entry("What is your most recent major achievement?"));
			questions.add(new Entry("What is your weakness?"));
			questions.add(new Entry("What is your quality?"));
			questions.add(new Entry("Give me an example of a tight relationship with your previous employer?"));
			questions.add(new Entry("Tell me about a time you showed leadership"));
			questions.add(new Entry("Tell me about a time you showed courage"));
			questions.add(new Entry("Tell me about a challenging situation you successfully overtook", 25));
			questions.add(new Entry("Tell me about a failure you faced. How did you fix it?"));
			questions.add(new Entry("Tell me about a time when you worked under pressure?"));
			Model.getInstance().setCurrentQuestionsList(questions);
		}
		
		List<Entry> questions = Model.getInstance().getCurrentQuestionsList();
		Collections.shuffle(questions);
		
		// Forward the info to the appropriate JSP
		request.setAttribute("questions", questions);
				
		request.getRequestDispatcher("DisplayQuestions.jsp").forward(request, response);
	}

}
