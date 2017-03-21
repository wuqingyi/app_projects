package com.wqy.apps.recycleviewtest;

/**
 * Created by wuqingyi on 2017/3/17.
 */

public class Msg {
	private String content;
	private int type;
	public static int TYPE_SEND = 0;
	public static int TYPE_RECEIVE = 1;

	public Msg(String content, int type) {
		this.content = content;
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public int getType() {
		return type;
	}
}
