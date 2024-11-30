package com.mohamed_badaouy.azkar_muslim1;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

public class Alarm_ShowAzkarInScreen extends BroadcastReceiver {

	PendingIntent mPendingIntent;
	AlarmManager mAlarmManager;
	
	
	
	@Override
	public void onReceive(Context p1, Intent p2) {
		boolean RunAndCloseShowAzkarInScreen=p1.getSharedPreferences("SettingsAlarmShowAzkarInScreen",p1.MODE_PRIVATE).getBoolean("RunAndClose",false);
		if(RunAndCloseShowAzkarInScreen==true){
		Intent ii=new Intent(p1,MyServiceforeground.class);
		p1.startService(ii);
		setAlarm(p1);
		}//end if
	}
	
    
    public void setAlarm(Context c){
		int x=1*60*1000;
		int timeRepeate=c.getSharedPreferences("repeate_show_azkar",Context.MODE_PRIVATE).getInt("Time",x);

        Intent intent = new Intent(c, Alarm_ShowAzkarInScreen.class);
        mPendingIntent = PendingIntent.getBroadcast(c, 0, intent, 0);
        mAlarmManager = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        //mAlarmManager.cancel(mPendingIntent);

        Calendar d=Calendar.getInstance();

		//if(TimeRepeate>0){
		d.setTimeInMillis(System.currentTimeMillis()+timeRepeate);
		
        mAlarmManager.cancel(mPendingIntent);
        //mPendingIntent.cancel(); 
        AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(d.getTimeInMillis(), mPendingIntent);
        mAlarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,d.getTimeInMillis(), mPendingIntent);
       // mAlarmManager.setAlarmClock(alarmClockInfo,mPendingIntent);
        // Log.d("tag",d.getTimeInMillis()/1000/60+"");
	}
    
}
