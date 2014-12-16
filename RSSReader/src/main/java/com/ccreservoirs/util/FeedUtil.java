package com.ccreservoirs.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.ccreservoirs.RSSReader.entity.RSSFeed;
import com.ccreservoirs.RSSReader.entity.RSSItem;
import com.ccreservoirs.services.FeedService;

public class FeedUtil {

	public static List<RSSItem> getItems(String urlPath) {
		return getFeed(urlPath).getItemList();
	}

	private static RSSFeed getFeed(String urlPath) {

		HttpURLConnection httpConnection = null;
		RSSFeed feed = null;
		URL url;
		FeedService fs = new FeedService();
		int code = 0;
		try {
			url = new URL(urlPath);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36");
			// OutputStreamWriter writer = new
			// OutputStreamWriter(httpConnection.getOutputStream());
			// writer.close();
			code = httpConnection.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (code == HttpURLConnection.HTTP_OK) {
			try {
				String strCurrentLine;
				feed = fs.getFeed(httpConnection.getInputStream());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return feed;
	}

}
