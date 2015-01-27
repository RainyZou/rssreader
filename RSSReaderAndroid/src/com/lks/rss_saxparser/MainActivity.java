package com.lks.rss_saxparser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ccreservoirs.RSSReader.entity.RSSItem;
import com.ccreservoirs.util.FeedUtil;

public class MainActivity extends Activity implements OnItemClickListener {

	// �������ȡRSS��ַ
	public final String RSS_URL = "http://blog.csdn.net/rss.html?type=Home&channel=";

	public final String tag = "RSSReader";
	private List<HashMap<String, String>> items = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		items = FeedUtil.getItemsMap(RSS_URL);

		showListView();
	}

	/*
	 * ��RSS���ݰ󶨵�ui���������ʾ
	 */
	private void showListView() {

		ListView itemList = (ListView) this.findViewById(R.id.list);
		if (items == null) {
			setTitle("No rss items");
			return;
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, items,
				android.R.layout.simple_list_item_2, new String[] {
						RSSItem.TITLE, RSSItem.DATE }, new int[] {
						android.R.id.text1, android.R.id.text2 });
		itemList.setAdapter(simpleAdapter);
		itemList.setOnItemClickListener(this);
		itemList.setSelection(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {

		Intent intent = new Intent();
		intent.setClass(this, ShowDescriptionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("title", items.get(position).get(RSSItem.TITLE));
		bundle.putString("description",
				items.get(position).get(RSSItem.DESCRIPTION));
		bundle.putString("link", items.get(position).get(RSSItem.LINK));
		bundle.putString("date", items.get(position).get(RSSItem.DATE));
	
		intent.putExtra("android.intent.extra.rssItem", bundle);
		startActivityForResult(intent, 0);

	}

}