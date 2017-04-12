package com.wqy.apps.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wuqingyi on 2017/4/12.
 */

public class Basic {
	@SerializedName("City")
	public String cityName;

	@SerializedName("id")
	public String weatherId;

	public Update update;

	public class Update {
		@SerializedName("loc")
		public String updateTime;
	}
}
