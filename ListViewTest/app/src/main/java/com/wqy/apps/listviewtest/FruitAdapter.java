package com.wqy.apps.listviewtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wuqingyi on 2017/3/15.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {
	private int sourceId;

	public FruitAdapter(Context context, int resource, List<Fruit> fruits) {
		super(context, resource, fruits);
		this.sourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Fruit fruit = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView != null) {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		} else {
			view = LayoutInflater.from(getContext()).inflate(this.sourceId, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.imgView_fruit = (ImageView) view.findViewById(R.id.imgView_fruit);
			viewHolder.tv_fruit = (TextView) view.findViewById(R.id.tv_fruit);
			view.setTag(viewHolder);
		}
		viewHolder.tv_fruit.setText(fruit.getFruitName());
		viewHolder.imgView_fruit.setImageResource(fruit.getImageId());
		return view;
	}
}

class ViewHolder {
	TextView tv_fruit;
	ImageView imgView_fruit;
}

class Fruit {
	private String fruitName;
	private int imageId;

	public Fruit(String fruitName, int imageId) {
		this.fruitName = fruitName;
		this.imageId = imageId;
	}

	public String getFruitName() {
		return fruitName;
	}

	public int getImageId() {
		return imageId;
	}
}