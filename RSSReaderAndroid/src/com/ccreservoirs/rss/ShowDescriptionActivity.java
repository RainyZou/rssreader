package com.ccreservoirs.rss;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ccreservoirs.util.URLImageGetter;

public class ShowDescriptionActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
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
		Button backButton = (Button) this.findViewById(R.id.back);
		TextView contentText = (TextView) this.findViewById(R.id.content);
		URLImageGetter reviewImgGetter = new URLImageGetter(
				ShowDescriptionActivity.this, contentText);// 实例化URLImageGetter类
		contentText.setText(Html.fromHtml(content, reviewImgGetter, null));

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private class MyImageGetter implements Html.ImageGetter {

		@Override
		public Drawable getDrawable(String source) {

			Log.i("", source);
			Bitmap bitmap;

			try {
				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inJustDecodeBounds = true;
				BitmapFactory.decodeStream(
						(InputStream) new URL(source).getContent(), null, o);
				// The new size we want to scale to
				final int REQUIRED_SIZE = 200;

				// Find the correct scale value. It should be the power of 2.
				int scale = 1;
				while (o.outWidth / scale / 2 >= REQUIRED_SIZE
						&& o.outHeight / scale / 2 >= REQUIRED_SIZE)
					scale *= 2;

				// Decode with inSampleSize
				BitmapFactory.Options o2 = new BitmapFactory.Options();
				o2.inSampleSize = scale;
				// HKEY_CURRENT_USER\Software\Google\Chrome\Metro
				bitmap = BitmapFactory.decodeStream((InputStream) new URL(
						source).getContent(), null, o2);

				@SuppressWarnings("deprecation")
				Drawable drawable = new BitmapDrawable(bitmap);
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight());
				return drawable;
			} catch (Exception e) {
				Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
				d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
				return d;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_show_description, menu);
		return true;
	}

}