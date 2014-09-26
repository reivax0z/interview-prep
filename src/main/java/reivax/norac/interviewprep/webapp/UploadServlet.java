package reivax.norac.interviewprep.webapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.simple.parser.ParseException;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String filePath = ".";
	private static String fileName = "compare.json";

	/**
	 * Default constructor. 
	 */
	public UploadServlet() {
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

		request.setAttribute("questions", Model.getInstance().getCurrentQuestionsList());
		
	    // Step1: Upload the file on system
	    try{
	    	UploadJsonFile.upload2(request, filePath, fileName);
	    } catch (IOException e){
			request.setAttribute("upload-fail", Boolean.TRUE);
			request.getRequestDispatcher("DisplayQuestions.jsp").forward(request, response);
			System.out.println(e);
			return;
	    } catch (Exception e) {
			request.setAttribute("upload-fail", Boolean.TRUE);
			request.getRequestDispatcher("DisplayQuestions.jsp").forward(request, response);
			System.out.println(e);
			return;
		}
	    
	    // Step2: Decode and compare the file
	    try {
	    	// Decode the file
			List<Entry> uploaded = JsonDecode.decodeBook(filePath + File.separator
			        + fileName);
			
			// Get book from Model
			Model.getInstance().setCurrentQuestionsList(uploaded);
			Collections.shuffle(uploaded);
			request.setAttribute("questions", uploaded);
			
		} catch (ParseException e) {
			request.setAttribute("upload-fail", Boolean.TRUE);
			request.getRequestDispatcher("DisplayQuestions.jsp").forward(request, response);
			System.out.println(e);
			return;
		} catch (Exception e) {
			request.setAttribute("upload-fail", Boolean.TRUE);
			request.getRequestDispatcher("DisplayQuestions.jsp").forward(request, response);
			System.out.println(e);
			return;
		}
	    
		request.setAttribute("upload-success", Boolean.TRUE);
		request.setAttribute("upload-fail", Boolean.FALSE);
		request.getRequestDispatcher("DisplayQuestions.jsp").forward(request, response);
	}
}
