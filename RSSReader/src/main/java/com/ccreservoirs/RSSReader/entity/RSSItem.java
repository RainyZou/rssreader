package com.ccreservoirs.RSSReader.entity;

import java.io.Serializable;

public class RSSItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1025480173754686127L;
	private String title;
	private String link;
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[tilte]:" + title + "[link]:" + link + "[description]:"
				+ description;
	}

}
