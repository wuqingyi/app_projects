package com.wqy.apps.listviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv_fruit = (ListView) findViewById(R.id.lv_fruit);
		FruitAdapter fruitAdapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, initFruit());
		lv_fruit.setAdapter(fruitAdapter);
		lv_fruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				TextView tv_fruit = (TextView) view.findViewById(R.id.tv_fruit);
				String val = (String) tv_fruit.getText();
				if (val != null) {
					Toast.makeText(MainActivity.this, "You select " + val, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.item_second_activity:
				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				startActivity(intent);
				break;
		}
		return true;
	}

	private List<Fruit> initFruit() {
		ArrayList<Fruit> lstFruit = new ArrayList<>();
		lstFruit.add(new Fruit("Apple", R.drawable.apple_pic));
		lstFruit.add(new Fruit("Banana", R.drawable.banana_pic));
		lstFruit.add(new Fruit("Orange", R.drawable.orange_pic));
		lstFruit.add(new Fruit("Watermelon", R.drawable.watermelon_pic));
		lstFruit.add(new Fruit("Pear", R.drawable.pear_pic));
		lstFruit.add(new Fruit("Grape", R.drawable.grape_pic));
		lstFruit.add(new Fruit("Pineapple", R.drawable.pineapple_pic));
		lstFruit.add(new Fruit("Strawberry", R.drawable.strawberry_pic));
		lstFruit.add(new Fruit("Cheery", R.drawable.cherry_pic));
		lstFruit.add(new Fruit("Mango", R.drawable.mango_pic));
		return lstFruit;
	}
}
