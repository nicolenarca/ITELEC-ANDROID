package com.example.weblogin;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudent extends Activity implements OnClickListener, OnItemSelectedListener {
	
	EditText idno, familyname, givenname;
	Spinner campus, course, yearlevel;
	Button save, cancel;
	String ca="", ye="", co="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.add_student);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		this.idno = (EditText) this.findViewById(R.id.editText1);
		this.familyname = (EditText) this.findViewById(R.id.editText2);
		this.givenname = (EditText) this.findViewById(R.id.editText3);
		this.campus = (Spinner) this.findViewById(R.id.spinner3);
		this.course = (Spinner) this.findViewById(R.id.spinner2);
		this.yearlevel = (Spinner) this.findViewById(R.id.spinner1);
		this.save = (Button) this.findViewById(R.id.button1);
		this.cancel = (Button) this.findViewById(R.id.button2);
		
		this.save.setOnClickListener(this);
		this.cancel.setOnClickListener(this);
		this.campus.setOnItemSelectedListener(this);
		this.course.setOnItemSelectedListener(this);
		this.yearlevel.setOnItemSelectedListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		
		String idno = this.idno.getText().toString();
		String familyname = this.familyname.getText().toString();
		String givenname = this.givenname.getText().toString();

		switch(arg0.getId())
		{
		case R.id.button1:
			
			if(idno.equals("") || familyname.equals("") || givenname.equals("") || ca.equals("") || co.equals("") || ye.equals("") )
			{
				Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
			}
			else
			{
			try {
				URL url = new URL("http://10.0.2.2/weblogin/addstudent.php?idno="+idno+"&familyname="+familyname+"&givenname="+givenname+"&course="+co+"&yearlevel="+ye+"&campus="+ca);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				InputStream is = conn.getInputStream();
				
				StringBuffer sb = new StringBuffer();
				
				int c=0;
				
				while((c=is.read()) != -1){
					
					sb.append((char)c);
				}
				
				Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
				
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
			Intent intent= new Intent (this, StudentList.class);
			this.startActivityForResult(intent, 100);
			
		case R.id.button2: this.finish();
		}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
			ca = this.campus.getItemAtPosition(arg2).toString();
			co = this.course.getItemAtPosition(arg2).toString();
			ye = this.yearlevel.getItemAtPosition(arg2).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
