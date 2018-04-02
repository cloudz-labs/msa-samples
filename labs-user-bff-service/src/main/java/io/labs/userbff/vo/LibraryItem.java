package io.labs.userbff.vo;

import java.util.List;

public class LibraryItem {

	private String id;
	private List<String> categories = null;
	private String title;
	private String contents;
	private List<LibraryItemLink> links = null;
	private List<LibraryItemAttachment> attachments = null;
	private String regDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public List<LibraryItemLink> getLinks() {
		return links;
	}

	public void setLinks(List<LibraryItemLink> links) {
		this.links = links;
	}

	public List<LibraryItemAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<LibraryItemAttachment> attachments) {
		this.attachments = attachments;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}