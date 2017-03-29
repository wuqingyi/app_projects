package com.wqy.apps.fragmenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wuqingyi on 2017/3/23.
 */

public class NewsTitleFragment extends Fragment {
	private boolean isTwoPane = false;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.news_title_frag, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(getActivity().findViewById(R.id.visibility_layout) != null){
			isTwoPane = true;
		}else{
			isTwoPane = false;
		}
	}
}

class NewsTitleRecycleViewAdapter extends RecyclerView.Adapter<NewsTitleRecycleViewViewHolder> {


	@Override
	public NewsTitleRecycleViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater.from(parent.getContext()).inflate(R.id.item_news_title,parent,false);
		return null;
	}

	@Override
	public void onBindViewHolder(NewsTitleRecycleViewViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}
}

class NewsTitleRecycleViewViewHolder extends RecyclerView.ViewHolder{
	private TextView tv_title;
	public NewsTitleRecycleViewViewHolder(View itemView) {
		super(itemView);
		tv_title = (TextView)itemView.findViewById(R.id.rv_news_title);
	}
}