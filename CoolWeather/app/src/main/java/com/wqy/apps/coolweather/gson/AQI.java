package com.wqy.apps.coolweather.gson;

/**
 * Created by wuqingyi on 2017/4/12.
 */

public class AQI {
	public AQICity city;

	public class AQICity {
		public String aqi;
		public String pm25;
	}
}
