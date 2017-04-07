package com.wqy.apps.recycleviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wuqingyi on 2017/3/17.
 */

public class DialogViewHolder extends RecyclerView.ViewHolder {

	LinearLayout ll_msg;
	TextView tv_msg;

	public DialogViewHolder(View itemView) {
		super(itemView);
		ll_msg = (LinearLayout) itemView.findViewById(R.id.ll_msg);
		tv_msg = (TextView) itemView.findViewById(R.id.tv_msg);
	}
}
