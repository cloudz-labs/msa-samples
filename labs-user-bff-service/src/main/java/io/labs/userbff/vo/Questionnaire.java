package io.labs.userbff.vo;

public class Questionnaire {
	private Number id;
	private Number questionId;
	private String email;
	private Object contents;
	private String regDate;
	/**
	 * @return the id
	 */
	public Number getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Number id) {
		this.id = id;
	}
	/**
	 * @return the questionId
	 */
	public Number getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Number questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the contents
	 */
	public Object getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(Object contents) {
		this.contents = contents;
	}
	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	} 
}
