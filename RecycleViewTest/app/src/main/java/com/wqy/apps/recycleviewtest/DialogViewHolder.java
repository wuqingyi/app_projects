package com.wqy.apps.recycleviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wuqingyi on 2017/3/17.
 */

public class DialogViewHolder extends RecyclerView.ViewHolder {

	LinearLayout ll_msg_left;
	TextView tv_msg_left;

	LinearLayout ll_msg_right;
	TextView tv_msg_right;

	public DialogViewHolder(View itemView) {
		super(itemView);
		ll_msg_left = (LinearLayout) itemView.findViewById(R.id.ll_msg_left);
		ll_msg_right = (LinearLayout) itemView.findViewById(R.id.ll_msg_right);
		tv_msg_left = (TextView) itemView.findViewById(R.id.tv_msg_left);
		tv_msg_right = (TextView) itemView.findViewById(R.id.tv_msg_right);
	}
}
