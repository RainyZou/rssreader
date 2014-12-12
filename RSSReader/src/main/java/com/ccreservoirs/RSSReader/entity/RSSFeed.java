package com.ccreservoirs.RSSReader.entity;

import java.io.Serializable;
import java.util.List;

public class RSSFeed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6559719213195928892L;

	private String title;
	private String link;
	private String description;
	private String pubDate;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[title]:" + title + "[link]:" + link + "[description]:"
				+ description + "[pubDate]:" + pubDate;
	}

	private List<RSSItem> itemList;

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

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public List<RSSItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<RSSItem> itemList) {
		this.itemList = itemList;
	}

}
