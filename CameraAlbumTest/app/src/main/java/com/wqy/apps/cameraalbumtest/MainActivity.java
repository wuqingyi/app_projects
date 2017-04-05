package com.wqy.apps.cameraalbumtest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private static final int TAKE_PHOTO = 1;
	private static final int FROM_ALBUM = 2;
	private ImageView imageView;
	private Uri imgUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn_take_photo = (Button) findViewById(R.id.btn_take_photo);
		btn_take_photo.setOnClickListener(this);
		Button btn_from_album = (Button) findViewById(R.id.btn_from_album);
		btn_from_album.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_take_photo:
				File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
				try {
					if (outputImage.exists()) {
						outputImage.delete();
					}
					outputImage.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (Build.VERSION.SDK_INT >= 24) {
					imgUri = FileProvider.getUriForFile(this, "com.wqy.apps.cameraalbumtest.fileprovider", outputImage);
				} else {
					imgUri = Uri.fromFile(outputImage);
				}
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
				startActivityForResult(intent, TAKE_PHOTO);
				break;
			case R.id.btn_from_album:
				if (ContextCompat.checkSelfPermission(this,
						Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
				} else {
					openAlbum();
				}
				break;
			default:
				break;
		}
	}

	private void openAlbum() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, FROM_ALBUM);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case TAKE_PHOTO:
				if (requestCode == RESULT_OK) {
					try {
						Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri));
						imageView.setImageBitmap(bitmap);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;

			case FROM_ALBUM:

			default:
				break;
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode) {
			case 1:
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					openAlbum();
				} else {
					Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show();
				}
		}
	}
}
