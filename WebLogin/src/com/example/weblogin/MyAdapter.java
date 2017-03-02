package com.example.weblogin;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	
	Context context;
	ArrayList<Student> list;
	LayoutInflater inflater; 
	
	public MyAdapter(Context context, ArrayList<Student> list) {
		super();
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

		ItemHandler handler=null;
		
		if(arg1==null)
		{
			arg1 = inflater.inflate(R.layout.studentlayout, null);
			handler = new ItemHandler();
			handler.familyname = (TextView) arg1.findViewById(R.id.textView1);
			handler.givenname = (TextView) arg1.findViewById(R.id.textView2);
			handler.idno = (TextView) arg1.findViewById(R.id.textView3);
			handler.course = (TextView) arg1.findViewById(R.id.textView4);
			handler.yearlevel = (TextView) arg1.findViewById(R.id.textView5);
			handler.campus = (TextView) arg1.findViewById(R.id.textView6);
			arg1.setTag(handler);
			
		}else handler = (ItemHandler) arg1.getTag();
		
		handler.familyname.setText(list.get(arg0).getFname());
		handler.givenname.setText(list.get(arg0).getGname());
		handler.idno.setText(list.get(arg0).getIdno());
		handler.course.setText(list.get(arg0).getCourse());
		handler.yearlevel.setText(list.get(arg0).getYear());
		
		return arg1;
	}
	
	
	static class ItemHandler{
		
		TextView familyname, givenname, idno, course, yearlevel, campus;
		
	}

}
