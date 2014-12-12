package com.lks.rss_saxparser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ShowDescriptionActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_description);
		String content = null;

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent
					.getBundleExtra("android.intent.extra.rssItem");
			if (bundle == null) {
				content = "������˼���������";
			} else {
				content = bundle.getString("title") + "\n\n"
						+ bundle.getString("pubdate") + "\n\n"
						+ bundle.getString("description").replace('\n', ' ')
						+ "\n\n��ϸ��Ϣ�����������ַ:\n" + bundle.getString("link");
			}
		} else {
			content = "������˼���������";
		}

		TextView contentText = (TextView) this.findViewById(R.id.content);
		contentText.setText(content);

		Button backButton = (Button) this.findViewById(R.id.back);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_show_description, menu);
		return true;
	}

}