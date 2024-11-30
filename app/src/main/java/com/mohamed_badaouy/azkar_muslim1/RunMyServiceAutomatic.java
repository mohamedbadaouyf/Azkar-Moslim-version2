package com.mohamed_badaouy.azkar_muslim1;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class RunMyServiceAutomatic extends BroadcastReceiver
{

	@Override
	public void onReceive(Context p1, Intent p2) {
		boolean b=checkBroadcast(p1);
		if(b==false){
			Intent intent2 = new Intent(p1, MyService.class);
			if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
				p1.startForegroundService(intent2);
			}else{
				p1.startService(intent2);
			}
		}
		
		// run my service forground show azkar
		Intent intent2 = new Intent(p1, Alarm_ShowAzkarInScreen.class);
		p1.sendBroadcast(intent2);
		//Toast.makeText(p1, "gh", Toast.LENGTH_SHORT).show();
	}
	
	public boolean checkBroadcast(Context c){
		ActivityManager ac=(ActivityManager)c.getSystemService(c.ACTIVITY_SERVICE);
		for(ActivityManager.RunningServiceInfo servicr:ac.getRunningServices(Integer.MAX_VALUE)){
			if(MyService.class.getName().equals(servicr.service.getClassName())){
				return true;
			}
		}


		return false;
	}
		
}

