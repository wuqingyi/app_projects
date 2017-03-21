package com.wqy.apps.listviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wuqingyi on 2017/3/15.
 */

public class FruitAdapter2 extends RecyclerView.Adapter<ViewHolder2> {

	private final List<Fruit> lstFruit;

	public FruitAdapter2(List<Fruit> lstFruit) {
		this.lstFruit = lstFruit;
	}

	@Override
	public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item2, parent, false);
		return new ViewHolder2(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder2 holder, int position) {
		Fruit fruit = this.lstFruit.get(position);
		holder.imgView_fruit.setImageResource(fruit.getImageId());
		holder.tv_fruit.setText(fruit.getFruitName());
	}

	@Override
	public int getItemCount() {
		return lstFruit == null ? 0 : lstFruit.size();
	}
}

class ViewHolder2 extends RecyclerView.ViewHolder {
	TextView tv_fruit;
	ImageView imgView_fruit;

	public ViewHolder2(View itemView) {
		super(itemView);
		tv_fruit = (TextView) itemView.findViewById(R.id.tv_fruit2);
		imgView_fruit = (ImageView) itemView.findViewById(R.id.imgView_fruit2);
	}
}
