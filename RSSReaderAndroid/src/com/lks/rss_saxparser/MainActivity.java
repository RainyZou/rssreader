package com.lks.rss_saxparser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.lks.rss_saxparser.R;
import com.lks.rss_saxparser.entity.RssFeed;
import com.lks.rss_saxparser.entity.RssItem;
import com.lks.rss_saxparser.saxparser.RssFeed_SAXParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity implements OnItemClickListener {

	// �������ȡRSS��ַ
	public final String RSS_URL = "http://blog.csdn.net/rss.html?type=Home&channel=mobile";

	public final String tag = "RSSReader";
	private RssFeed feed = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			feed = new RssFeed_SAXParser().getFeed(RSS_URL);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		showListView();
	}

	/*
	 * ��RSS���ݰ󶨵�ui���������ʾ
	 */
	private void showListView() {

		ListView itemList = (ListView) this.findViewById(R.id.list);
		if (feed == null) {
			setTitle("���ʵ�RSS��Ч");
			return;
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,
				feed.getAllItems(), android.R.layout.simple_list_item_2,
				new String[] { RssItem.TITLE, RssItem.PUBDATE }, new int[] {
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
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

		Intent intent = new Intent();
		intent.setClass(this, ShowDescriptionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("title", feed.getItem(position).getTitle());
		bundle.putString("description",feed.getItem(position).getDescription());
		bundle.putString("link", feed.getItem(position).getLink());
		bundle.putString("pubdate", feed.getItem(position).getPubdate());
		// ��android.intent.extra.INTENT�����������ݲ���
		intent.putExtra("android.intent.extra.rssItem", bundle);
		startActivityForResult(intent, 0);
		
	}

}