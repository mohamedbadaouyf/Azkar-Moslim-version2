package com.mohamed_badaouy.azkar_muslim1;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MyService extends Service
{
   ArrayList<String>array=new ArrayList();
	private WindowManager mWindowManager;
	WindowManager.LayoutParams params;
    private View mChatHeadView;
	LayoutInflater inflater;
	WindowManager.LayoutParams wi;
	static CountDownTimer cound;
	Animation anim,anim2;
	TextView t;
	static boolean mboolean=false;
	static RemoteViews remoteViews;
	static boolean RunAndCloseShowAzkarInScreen;
	
	private Handler handler = new Handler();
    private Runnable periodicTask;
	
	
	@Override
	public IBinder onBind(Intent p1)
	{
		// TODO: Implement this method
		return null;
	}

	/*@Override
	public void onStart(Intent intent, int startId) {
		boolean RunAndCloseShowAzkarInScreen=getSharedPreferences("SettingsAlarmShowAzkarInScreen",MODE_PRIVATE).getBoolean("RunAndClose",false);
		Toast.makeText(getApplicationContext(),RunAndCloseShowAzkarInScreen+ "", Toast.LENGTH_SHORT).show();
		
		super.onStart(intent, startId);
	}
*/
	
	
	@Override
	public int onStartCommand(Intent intentt, int flags, int startId)
	{
		//stopSelf();
		
		int repeateShowZkr=getSharedPreferences("repeate_show_azkar",MODE_PRIVATE).getInt("Time",1*60*1000);	
		  RunAndCloseShowAzkarInScreen=getSharedPreferences("SettingsAlarmShowAzkarInScreen",MODE_PRIVATE).getBoolean("RunAndClose",true);
		 remoteViews=new RemoteViews(this.getPackageName(),R.layout.notification_style);
			
		try{
			if(RunAndCloseShowAzkarInScreen==true){
				String infoRepeateShowZkr="ستظهر الأذكار كل "+repeateShowZkr/60/1000+" دقيقة ";
				remoteViews.setTextViewText(R.id.notification_style_TextView,"ظهور الأذكار علي الشاشة فعال !");
				remoteViews.setTextViewText(R.id.notification_style_TextView2,infoRepeateShowZkr);
			 }else{
			   
				 remoteViews.setTextViewText(R.id.notification_style_TextView,"وسبح بحمد ربك قبل طلوع الشمس وقبل الغروب");
				 remoteViews.setTextViewText(R.id.notification_style_TextView2,"");
			 }
			
		Notification.Builder build=new Notification.Builder(this,"chaneel");
		final Notification notification=build
	   .setSmallIcon(R.drawable.icon)
	   .setCustomContentView(remoteViews)
	  // .setContent(remoteViews)
		//.setContentTitle("ظهور الأذكار علي الشاشة فعال !")
	  //.setStyle(new Notification.BigTextStyle().bigText("ستظهر الاذكار كل "+repeateShowZkr/60/1000+" دقيقة "))
		.setShowWhen(true)
		.build();
			
			
		
		NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		NotificationChannel chaneel=new NotificationChannel("chaneel","ظهور الاذكار على الشاشة",NotificationManager.IMPORTANCE_LOW);
		//chaneel.setLightColor(Color.RED);
		manager.createNotificationChannel(chaneel);
		
		//manager.notify(i+1,notification);
		
		startForeground(1,notification);
		updateService();
		//updateNotification("عه");
		
		
				AudioManager audio=(AudioManager)getSystemService(AUDIO_SERVICE);
				if(audio.getRingerMode()!=AudioManager.RINGER_MODE_NORMAL){
				Toast.makeText(MyService.this, "يرجي قفل عدم الازعاج", Toast.LENGTH_LONG).show();
					}
					
			
			
					
				
        }catch(Exception e){Toast.makeText(MyService.this,e.getMessage(), Toast.LENGTH_SHORT).show();}
        
		
		Intent runAllAlarms=new Intent(this,Alarm_SetAllAlarms.class);
		sendBroadcast(runAllAlarms); 
		
		return START_STICKY;
		
		//return super.onStartCommand(intentt, flags,startId);
	}

	

	
	
	@Override
	public void onCreate()
	{
		/*Intent StopServic_Alarm_AzkarSabah=new Intent(this,Alarm_AzkarSabahService.class);
		Intent StopService_Alarm_AzkarMasaa=new Intent(this,Alarm_AzkarMasaa.class);
		stopService(StopServic_Alarm_AzkarSabah);
		stopService(StopService_Alarm_AzkarMasaa);*/
		//schedulePeriodicTask();
		Intent runAllAlarms=new Intent(this,Alarm_SetAllAlarms.class);
		sendBroadcast(runAllAlarms); 
		
		super.onCreate();
	}

	@Override
	public void onDestroy()
	{
		Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
		restartServiceIntent.setPackage(getPackageName());

		PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
		AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
		alarmService.set(AlarmManager.ELAPSED_REALTIME,SystemClock.elapsedRealtime() + 1000,restartServicePendingIntent);
		//handler.removeCallbacks(periodicTask);
		super.onDestroy(); 
		
	}

	@Override
	public void onTaskRemoved(Intent rootIntent)
	{
		Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
		restartServiceIntent.setPackage(getPackageName());

		PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
		AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
		alarmService.set(AlarmManager.ELAPSED_REALTIME,SystemClock.elapsedRealtime() + 1000,restartServicePendingIntent);
		startForegroundService(new Intent(this,MyService.class));
		super.onTaskRemoved(rootIntent);
	}

		
	
	public boolean checkBroadcast(){
		ActivityManager ac=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
		 for(ActivityManager.RunningServiceInfo servicr:ac.getRunningServices(Integer.MAX_VALUE)){
			if(MyServiceforeground.class.getName().equals(servicr.service.getClassName())){
				return true;
			}
		}
		
		
		return false;
	}
	
	
	public void updateService(){


		new CountDownTimer(60*60*1000, 1000){

			@Override
			public void onTick(long p1)
			{
				// TODO: Implement this method
			}

			@Override
			public void onFinish()
			{
				/*Intent i=new Intent(MyService.this,MyService.class);
				 startService(i);*/
				stopSelf();
			}

		}.start();
	}///end update service
	
	
	
}
