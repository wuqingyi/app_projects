package com.wqy.apps.fragmenttest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {

	public static void actionStart(Context context, String title, String content){
		Intent intent = new Intent(context, NewsContentActivity.class);
		intent.putExtra("news_title", title);
		intent.putExtra("news_content", content);
		context.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_content);
		String newsTitle = getIntent().getStringExtra("news_title");
		String newsContent = getIntent().getStringExtra("new_content");
		NewsContentFragment newsContentFragment = (NewsContentFragment)
				getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
		newsContentFragment.refresh(newsTitle, newsContent);
	}
}
