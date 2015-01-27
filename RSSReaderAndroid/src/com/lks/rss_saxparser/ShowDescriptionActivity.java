package com.lks.rss_saxparser;

import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
				content = "No more contents";
			} else {
				content = bundle.getString("title") + "\n\n"
						+ bundle.getString("date") + "\n\n"
						+ bundle.getString("description")
						+ bundle.getString("link");
			}
		} else {
			content = "No more contents";
		}

		TextView contentText = (TextView) this.findViewById(R.id.content);
		contentText.setText(Html.fromHtml(content, imageGetter, null));

		Button backButton = (Button) this.findViewById(R.id.back);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	ImageGetter imageGetter = new ImageGetter() {
		@Override
		public Drawable getDrawable(String source) {
			Drawable drawable = null;
			URL url;
			try {
				url = new URL(source);
				drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
			} catch (Exception e) {
				return null;
			}
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_show_description, menu);
		return true;
	}

}