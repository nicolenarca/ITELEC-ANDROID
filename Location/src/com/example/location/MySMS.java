package com.example.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySMS extends BroadcastReceiver {

	Location location;
	double lng, lat;
	private String senderaddress;
	private String message;
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub

		try {
			Bundle b = arg1.getExtras();
			Object[] pdus=(Object[])b.get("pdus");
			
			for (int i=0;i<pdus.length;i++){
				SmsMessage sms = SmsMessage.createFromPdu((byte[])pdus[i]);
				 senderaddress=sms.getOriginatingAddress();
				 message=sms.getMessageBody();
				
				Toast.makeText(arg0, "Sender: "+senderaddress+"Message: "+message, Toast.LENGTH_LONG).show();
				
			
			
				location = new Location(arg0);

				lat = location.getLat();
				lng = location.getLong();
				
			if(message.equals("Paulina")){
				SmsManager m= SmsManager.getDefault();
				m.sendTextMessage(senderaddress, null, ""+senderaddress+"\nLatitude: "+lat+"\nLongitude: "+lng, null,null);
				
			}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
