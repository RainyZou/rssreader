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
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

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
		contentText.setText(Html.fromHtml(content, new MyImageGetter(), null));

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	// ImageGetter imageGetter = new ImageGetter() {
	// @Override
	// public Drawable getDrawable(String source) {
	// Drawable drawable = null;
	// URL url;
	// InputStream input = null;
	// try {
	// Log.i("", source);
	// url = new URL(source);
	// input = url.openStream();
	// drawable = Drawable.createFromStream(input, ""); // 获取网路图片
	// input.close();
	// } catch (Exception e) {
	// return null;
	// }
	// drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
	// drawable.getIntrinsicHeight());
	// return drawable;
	// }
	// };

	private class MyImageGetter implements Html.ImageGetter {

		@Override
		public Drawable getDrawable(String arg0) {
			
			Log.i("", arg0);
			Bitmap bitmap;

			try {
				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inJustDecodeBounds = true;
				BitmapFactory.decodeStream(
						(InputStream) new URL(arg0).getContent(), null, o);
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
				bitmap = BitmapFactory.decodeStream(
						(InputStream) new URL(arg0).getContent(), null, o2);

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