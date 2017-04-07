package com.wqy.apps.notificationtest;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NoticeActivity extends AppCompatActivity implements View.OnClickListener {

	private Uri imgUri;
	public static final int TAKE_PHOTO = 1;
	private ImageView picture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		Button take = (Button) findViewById(R.id.btn_start_camera);
		take.setOnClickListener(this);
		picture = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
		try {
			if (outputImage.exists()) {
				outputImage.delete();
			}
			outputImage.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Build.VERSION.SDK_INT > 24) {
			imgUri = FileProvider.getUriForFile(this, "com.wqy.apps.notificationtest", outputImage);
		} else {
			imgUri = Uri.fromFile(outputImage);
		}

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
		startActivityForResult(intent, TAKE_PHOTO);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case TAKE_PHOTO:
				if (resultCode == RESULT_OK) {
					try {
						picture.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri)));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
		}
	}
}
