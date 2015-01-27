package com.ccreservoirs.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.ccreservoirs.RSSReader.entity.RSSFeed;
import com.ccreservoirs.RSSReader.entity.RSSItem;
import com.ccreservoirs.services.FeedService;

public class FeedUtil {

	public static List<RSSItem> getItems(String urlPath) {
		return getFeed(urlPath).getItemList();
	}

	public static List<HashMap<String, String>> getItemsMap(String urlPath) {
		List<RSSItem> tempItems = getFeed(urlPath).getItemList();
		List<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();
		for (RSSItem item : tempItems) {
			temp.add(item.toMap());
		}
		return temp;
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
			Log.e("", "", e);
		}

		if (code == HttpURLConnection.HTTP_OK) {
			try {
				String strCurrentLine;
				feed = fs.getFeed(httpConnection.getInputStream(),
						CharsetUtil.getFileEncode(urlPath));

			} catch (Exception e) {
				Log.e("", "", e);
			}
		}

		return feed;
	}

}
