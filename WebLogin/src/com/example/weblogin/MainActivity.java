package com.example.weblogin;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	EditText username, password;
	Button login;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        this.username = (EditText) this.findViewById(R.id.editText1);
        this.password = (EditText) this.findViewById(R.id.editText2);
        this.login = (Button) this.findViewById(R.id.button1);
        
        
        
        this.login.setOnClickListener(this);
 
        
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		String username = this.username.getText().toString();
		String password = this.password.getText().toString();
		
		try {
			URL url = new URL("http://10.0.2.2/weblogin/login.php?username="+username+"&password="+password);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			InputStream is = con.getInputStream();
			
			int c=0;
			
			StringBuffer sb = new StringBuffer();
			
			while((c=is.read()) != -1)
			{
				sb.append((char)c);
			}
			is.close();
			con.disconnect();
			
			String m = sb.toString();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("http response");
			builder.setMessage(sb.toString());
			builder.setNeutralButton("Okey", null);
			
			
			AlertDialog dialog = builder.create();
			dialog.show();
			
			if(m.equals("Login Accepted"))
			{
				Intent intent = new Intent(this, StudentList.class);
				this.startActivity(intent);
			}
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
    
}
