package com.ccreservoirs.mapper;

import java.util.List;

import com.ccreservoirs.RSSReader.entity.RSSFeed;

public interface feedMapper {

	public void addFeed(RSSFeed feed);

	public void updateFeed(RSSFeed feed);

	public RSSFeed getFeedByTime(String date);

	public RSSFeed getFeedById(int id);

	public List<RSSFeed> getAllFeed();

}
