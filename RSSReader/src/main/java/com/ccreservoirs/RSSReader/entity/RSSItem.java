package com.ccreservoirs.RSSReader.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class RSSItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1025480173754686127L;
	private String title;
	private String link;
	private String description;
	private int id;
	private int feedId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	/**
	 * 通过 Feed 注入 date
	 */
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

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
		return title;
	}

}
