package com.wqy.apps.coolweather;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wqy.apps.coolweather.gson.Forecast;
import com.wqy.apps.coolweather.gson.Weather;
import com.wqy.apps.coolweather.util.HttpUtil;
import com.wqy.apps.coolweather.util.Utility;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

	private ScrollView weatherLayout;
	private TextView titleCity;
	private TextView titleUpdateTime;
	private TextView degreeText;
	private TextView weatherInfoText;
	private LinearLayout forecastLayout;
	private TextView aqiText;
	private TextView pm25Text;
	private TextView comfortText;
	private TextView carWashText;
	private TextView sportText;
	private ImageView bingPicImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= 21) {
			View decorView = getWindow().getDecorView();
			decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			getWindow().setStatusBarColor(Color.TRANSPARENT);
		}
		setContentView(R.layout.activity_weather);
		weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
		titleCity = (TextView) findViewById(R.id.title_city);
		titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
		degreeText = (TextView) findViewById(R.id.degree_text);
		weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
		forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
		aqiText = (TextView) findViewById(R.id.aqi_text);
		pm25Text = (TextView) findViewById(R.id.pm25_text);
		comfortText = (TextView) findViewById(R.id.comfort_text);
		carWashText = (TextView) findViewById(R.id.car_wash_text);
		sportText = (TextView) findViewById(R.id.sport_text);
		bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String weatherId_pref = preferences.getString("weather_id", null);
		String date_pref = preferences.getString("date", null);
		String weatherString = preferences.getString("weather", null);

		String weatherId = getIntent().getStringExtra("weather_id");
		String today = DateFormat.format("yyyy_MM_dd", new Date()).toString();

		if (weatherId_pref != null && weatherId_pref.equals(weatherId)
				&& date_pref != null && date_pref.equals(today)
				&& weatherString != null) {
			Weather weather = Utility.handleWeatherResponse(weatherString);
			showWeatherInfo(weather);
		} else {
			weatherLayout.setVisibility(View.INVISIBLE);
			requestWeather(weatherId);
		}

		String bingPic = preferences.getString("bing_pic", null);
		if (bingPic != null)

		{
			Glide.with(this).load(bingPic).into(bingPicImg);
		} else

		{
			loadBingPic();
		}

	}

	private void loadBingPic() {
		String requestBingPic = "http://guolin.tech/api/bing_pic";
		HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				final String bingPic = response.body().string();
				SharedPreferences.Editor editor = PreferenceManager
						.getDefaultSharedPreferences(WeatherActivity.this).edit();
				editor.putString("bing_pic", bingPic);
				editor.apply();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
					}
				});
			}
		});
	}

	private void requestWeather(String weatherId) {
		loadBingPic();

		String weatherUrl = "http://guolin.tech/api/weather?cityid=" +
				weatherId + "&key=09b6ee4796094d12a4054cf30414a10a";
		HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(WeatherActivity.this,
								"获取天气信息失败", Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				final String responseText = response.body().string();
				final Weather weather = Utility.handleWeatherResponse(responseText);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (weather != null && "ok".equalsIgnoreCase(weather.status)) {
							SharedPreferences.Editor editor = PreferenceManager
									.getDefaultSharedPreferences(WeatherActivity.this).edit();
							editor.putString("weather", responseText);
							editor.apply();
							showWeatherInfo(weather);
						} else {
							Toast.makeText(WeatherActivity.this,
									"获取天气信息失败", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		});
	}

	private void showWeatherInfo(Weather weather) {
		titleCity.setText(weather.basic.cityName);
		titleUpdateTime.setText(weather.basic.update.updateTime.split(" ")[1]);
		degreeText.setText(weather.now.temperature + "℃");
		weatherInfoText.setText(weather.now.more.info);
		forecastLayout.removeAllViews();
		for (Forecast forecast : weather.forecastList) {
			View view = LayoutInflater.from(this)
					.inflate(R.layout.forecast_item, forecastLayout, false);
			TextView dateText = (TextView) view.findViewById(R.id.date_text);
			TextView infoText = (TextView) view.findViewById(R.id.info_text);
			TextView maxText = (TextView) view.findViewById(R.id.max_text);
			TextView minText = (TextView) view.findViewById(R.id.min_text);
			dateText.setText(forecast.date);
			infoText.setText(forecast.more.info);
			maxText.setText(forecast.temperature.max);
			minText.setText(forecast.temperature.min);
			forecastLayout.addView(view);
		}
		if (weather.aqi != null) {
			aqiText.setText(weather.aqi.city.aqi);
			pm25Text.setText(weather.aqi.city.pm25);
		} else {
			aqiText.setText("");
			pm25Text.setText("");
		}
		comfortText.setText("舒适度：" + weather.suggestion.comfort.info);
		carWashText.setText("洗车指数：" + weather.suggestion.carWash.info);
		sportText.setText("运动建议：" + weather.suggestion.sport.info);
		weatherLayout.setVisibility(View.VISIBLE);
	}
}
