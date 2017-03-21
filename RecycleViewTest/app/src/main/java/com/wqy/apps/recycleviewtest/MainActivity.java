package com.wqy.apps.recycleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	EditText et_msg;
	ArrayList<Msg> lstMsg;
	private DialogAdapter da;
	RecyclerView rv_dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rv_dialog = (RecyclerView) findViewById(R.id.rv_dialog);
		rv_dialog.setLayoutManager(new LinearLayoutManager(this));
		lstMsg = new ArrayList<>();
		da = new DialogAdapter(lstMsg);
		rv_dialog.setAdapter(da);
		et_msg = (EditText) findViewById(R.id.et_input);
		Button btn_send = (Button) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_send:
				String content = et_msg.getText().toString();
				Msg msg;
				msg = new Msg(content, !content.endsWith("r") ? Msg.TYPE_SEND : Msg.TYPE_RECEIVE);
				lstMsg.add(msg);
				da.notifyItemInserted(lstMsg.size() - 1);
				et_msg.setText("");
				rv_dialog.scrollToPosition(lstMsg.size() - 1);
				break;
		}
	}
}
