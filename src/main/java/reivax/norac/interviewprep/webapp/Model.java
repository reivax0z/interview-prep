package reivax.norac.interviewprep.webapp;

import java.util.ArrayList;
import java.util.List;

public class Model{

	private List<Entry> currentQuestionsList = null;
	
	private Model(){
		currentQuestionsList = new ArrayList<Entry>();
	}
	
	/**
	 * Singleton, only one model
	 */
	private static Model singleton = new Model();
	
	public static Model getInstance(){
		return singleton;
	}
	
	public List<Entry> getCurrentQuestionsList() {
		return currentQuestionsList;
	}

	public void setCurrentQuestionsList(List<Entry> currentQuestionsList) {
		this.currentQuestionsList = currentQuestionsList;
	}
}
