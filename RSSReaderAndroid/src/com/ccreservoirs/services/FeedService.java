package com.ccreservoirs.services;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ccreservoirs.RSSReader.entity.RSSFeed;
import com.ccreservoirs.RSSReader.entity.RSSItem;

public class FeedService {

	/**
	 * 从流中读取 RSS Feed
	 */
	public RSSFeed getFeed(InputStream xml, Charset charset) throws Exception {

		String charsetname  = "";
		if (charset == null) {
			charsetname = "utf-8";
		}else{
			charsetname = charset.name();
		}
		RSSFeed feed = null;
		RSSItem item = null;

		// 获得pull解析器工厂
		XmlPullParserFactory pullParserFactory = XmlPullParserFactory
				.newInstance();

		// 获取XmlPullParser的实例
		XmlPullParser pullParser = pullParserFactory.newPullParser();

		// 设置需要解析的XML数据
		pullParser.setInput(xml, charsetname);

		// 取得事件
		int event = pullParser.getEventType();

		// 若为解析到末尾
		while (event != XmlPullParser.END_DOCUMENT) // 文档结束
		{
			// 节点名称
			String nodeName = pullParser.getName();
			switch (event) {
			case XmlPullParser.START_DOCUMENT: // 文档开始
				feed = new RSSFeed();
				feed.setItemList(new ArrayList<RSSItem>());

				break;
			case XmlPullParser.START_TAG: // 标签开始
				if ("title".equals(nodeName)) {
					String title = pullParser.nextText();
					if (item == null) {
						feed.setTitle(title);
					} else {
						item.setTitle(title);
					}
				}
				if ("link".equals(nodeName)) {
					String link = pullParser.nextText();
					if (item == null) {
						feed.setLink(link);
					} else {
						item.setLink(link);
					}
				}
				if ("description".equals(nodeName)) {
					String description = pullParser.nextText();
					if (item == null) {
						feed.setDescription(description);
					} else {
						item.setDescription(description);
					}

				}

				if ("pubDate".equals(nodeName)) {
					String pubDate = pullParser.nextText();
					if (item == null) {
						feed.setPubDate(pubDate);
					} else {
						item.setDate(pubDate);
					}

				}

				if ("item".equals(nodeName)) {
					item = new RSSItem();
					feed.getItemList().add(item);
				}

				break;
			case XmlPullParser.END_TAG: // 标签结束

				break;
			}
			event = pullParser.next(); // 下一个标签

		}

		for (RSSItem itemTemp : feed.getItemList()) {
			if (itemTemp.getDate() == null
					|| itemTemp.getDate().equalsIgnoreCase("")) {
				itemTemp.setDate(feed.getPubDate());
			}
		}

		return feed;

	}

}
