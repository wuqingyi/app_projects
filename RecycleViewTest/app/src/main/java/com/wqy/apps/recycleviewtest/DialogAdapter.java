package com.wqy.apps.recycleviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by wuqingyi on 2017/3/17.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogViewHolder> {

	private ArrayList<Msg> lstMsg;

	public DialogAdapter(ArrayList<Msg> lstMsg) {
		this.lstMsg = lstMsg;
	}

	@Override
	public DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg, parent, false);
		return new DialogViewHolder(view);
	}

	@Override
	public void onBindViewHolder(DialogViewHolder holder, int position) {
		Msg msg = lstMsg.get(position);
		holder.tv_msg.setText(msg.getContent());
		if (msg.getType() == Msg.TYPE_RECEIVE) {
			holder.ll_msg.setBackgroundResource(R.drawable.message_left);
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.ll_msg.getLayoutParams();
			layoutParams.gravity = Gravity.LEFT;
		} else if (msg.getType() == Msg.TYPE_SEND) {
			holder.ll_msg.setBackgroundResource(R.drawable.message_right);
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.ll_msg.getLayoutParams();
			layoutParams.gravity = Gravity.RIGHT;
		}
	}

	@Override
	public int getItemCount() {
		return this.lstMsg != null ? this.lstMsg.size() : 0;
	}
}
