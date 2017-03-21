package com.wqy.apps.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		RecyclerView rv_fruit = (RecyclerView) findViewById(R.id.rv_fruit);
		LinearLayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

		GridLayoutManager GridLayoutManager = new GridLayoutManager(SecondActivity.this, 3);
		rv_fruit.setLayoutManager(GridLayoutManager);
		FruitAdapter2 fruitAdapter2 = new FruitAdapter2(initFruit());
		rv_fruit.setAdapter(fruitAdapter2);
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
