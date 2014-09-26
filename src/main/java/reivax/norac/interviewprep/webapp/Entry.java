package reivax.norac.interviewprep.webapp;

public class Entry {

	private String question;
	private long timeout;
	
	public Entry(){
		
	}
	
	public Entry(String question){
		this.question = question;
	}
	
	public Entry(String question, long timeout){
		this.question = question;
		this.timeout = timeout;
	}
	
	@Override
	public boolean equals(Object o){
		return o instanceof Entry && ((Entry)o).getQuestion().equals(this.getQuestion());
	}
	
	@Override
	public int hashCode(){
		return this.question.hashCode();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
