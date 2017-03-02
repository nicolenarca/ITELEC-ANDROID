package com.example.weatherapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.weatherapp.R.id;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	
	EditText city;
	ImageButton search;
	TextView weather, mycity, temp, humidity;
	ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.iv = (ImageView) this.findViewById(R.id.imageView1);
        this.city = (EditText) this.findViewById(R.id.editText1);
        this.search = (ImageButton) this.findViewById(R.id.imageButton1);
        this.weather = (TextView) this.findViewById(R.id.textView4);
        this.mycity = (TextView) this.findViewById(R.id.textView1);
        this.temp = (TextView) this.findViewById(R.id.textView2);
        this.humidity = (TextView) this.findViewById(R.id.textView3);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        this.search.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View arg0) {
		
		String getCity = this.city.getText().toString();
		
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+getCity+"&appid=30aab0d6323660caf62acd4414254c75");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			InputStream is = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			int c=0;
			
			while((c=is.read()) != -1)
			{
				sb.append((char)c);
			}
			
			is.close();
			conn.disconnect();
			String data = sb.toString();
			
			JSONObject jo = new JSONObject(data);
			
			JSONObject main = jo.getJSONObject("main");
			JSONArray weather = jo.getJSONArray("weather");
			JSONObject jso = weather.getJSONObject(0);
			
			
			String w = jso.getString("description");
			String icon = jso.getString("icon");
			String ci = jo.getString("name");
			String t = main.getString("temp");
			String h = main.getString("humidity");
			
			url = new URL("http://openweathermap.org/img/w/"+icon+".png");
			conn = (HttpURLConnection) url.openConnection();
			is = conn.getInputStream();
			
			Bitmap bmp = BitmapFactory.decodeStream(is);
			
			double celcius = Double.parseDouble(t)-272.3;
			
			this.iv.setImageBitmap(bmp);
			this.weather.setText(w);
			this.mycity.setText(ci);
			this.humidity.setText("Humidity: "+h+"%");
			this.temp.setText("Temperature: "+String.format("%.2f", celcius)+" °C");
			
			is.close();
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

    
}
