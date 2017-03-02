package com.example.weblogin;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class StudentList extends Activity {

	ListView lv;
	ArrayList<Student> list= new ArrayList<Student>();
	MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.studentlist);
		
		this.lv = (ListView) this.findViewById(R.id.listView1);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
	try
	{
		URL url = new URL("http://10.0.2.2/weblogin/studentlist.php");
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
		
		JSONArray ja = new JSONArray(data);
		JSONObject jo = new JSONObject();
		
		for(int i=0; i<ja.length(); i++)
		{
			jo = ja.getJSONObject(i);
			String idno = jo.getString("idno");
			String lastname = jo.getString("familyname");
			String firstname = jo.getString("givenname");
			String course = jo.getString("course");
			String year = jo.getString("year");
			String campus = jo.getString("campus");
			
			list.add(new Student(idno, lastname, firstname, course, year, campus));
		}
		
		adapter = new MyAdapter(this, list);
		this.lv.setAdapter(adapter);
		
		this.adapter.notifyDataSetChanged();
		
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
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent = new Intent(this, AddStudent.class);
		this.startActivity(intent);
		
		return super.onOptionsItemSelected(item);
		
		
	
	}
	
	
	

}
