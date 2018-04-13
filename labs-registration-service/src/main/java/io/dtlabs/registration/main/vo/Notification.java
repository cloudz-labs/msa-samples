package io.dtlabs.registration.main.vo;

public class Notification {
	private String color;
	private String message;
	private boolean notify;
	private String message_format;

	public Notification(String color, String message, boolean notify, String message_format) {
		this.color = color;
		this.message = message;
		this.notify = notify;
		this.message_format = message_format;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	public String getMessage_format() {
		return message_format;
	}

	public void setMessage_format(String message_format) {
		this.message_format = message_format;
	}

}


