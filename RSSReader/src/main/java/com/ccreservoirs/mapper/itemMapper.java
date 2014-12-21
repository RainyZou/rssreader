package com.ccreservoirs.mapper;

import java.util.List;

import com.ccreservoirs.RSSReader.entity.RSSItem;

public interface itemMapper {

	public void insertItem(RSSItem item);

	public void updateItem(RSSItem item);

	public RSSItem getItemById(int id);

	public List<RSSItem> getItemByFeed(int feedId);
}
