package com.mohamed_badaouy.azkar_muslim1;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;

public class MyNotificationListener extends NotificationListenerService {

	@Override
	public void onNotificationPosted(StatusBarNotification sbn) {
		
		
		if(CheckMyService()==true){
			//Toast.makeText(getApplication(), "true", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getApplication(), "false", Toast.LENGTH_SHORT).show();
			Intent intent2 = new Intent(this, MyService.class);
			//stopService(intent2);
			if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
				startForegroundService(intent2);
			}else{
				startService(intent2);
			}
		}
		
		//Toast.makeText(getApplication(), "777", Toast.LENGTH_SHORT).show();
		
		super.onNotificationPosted(sbn);
	}

	

	@Override
	public void onNotificationRemoved(StatusBarNotification sbn) {
		super.onNotificationRemoved(sbn);
	}
    
    
    public boolean CheckMyService(){
		ActivityManager ac=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
		for(ActivityManager.RunningServiceInfo servicr:ac.getRunningServices(Integer.MAX_VALUE)){
			if(MyService.class.getName().equals(servicr.service.getClassName())){
				return true;
			}
		}


		return false;
	}
	
	
	////
}
