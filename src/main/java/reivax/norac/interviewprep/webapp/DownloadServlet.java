package reivax.norac.interviewprep.webapp;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String filePath = "/main/resources";

	/**
	 * Default constructor. 
	 */
	public DownloadServlet() {
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

	    try{
	    	new DownloadJsonFile().download(response, filePath);
	    	return;
	    } catch (IOException e){
			request.getRequestDispatcher("/Home").forward(request, response);
			return;
	    } catch (Exception e) {
			request.getRequestDispatcher("/Home").forward(request, response);
			return;
		}
	}
}
