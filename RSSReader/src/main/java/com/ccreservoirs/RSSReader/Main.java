package com.ccreservoirs.RSSReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ccreservoirs.RSSReader.entity.RSSFeed;
import com.ccreservoirs.services.FeedService;

/**
 * Hello world!
 *
 */
public class Main {
	public static void main(String[] args) {

		String urlPath = "http://blog.csdn.net/rss.html?type=Home&channel=";
		HttpURLConnection httpConnection = null;
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
				RSSFeed feed = fs.getFeed(httpConnection.getInputStream());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
