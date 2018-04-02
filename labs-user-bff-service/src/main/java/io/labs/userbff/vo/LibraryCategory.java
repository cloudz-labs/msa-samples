package io.labs.userbff.vo;

import java.util.List;

public class LibraryCategory {
	private String id;
	private String name;
	private int order;
	private List<LibraryItem> documents;

	public String getId() {
		return id;
	}	
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public List<LibraryItem> getDocuments() {
		return documents;
	}
	public void setDocuments(List<LibraryItem> documents) {
		this.documents = documents;
	}

}