package com.mohamed_badaouy.azkar_muslim1;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;
//import com.mohamed.azkar_muslim_mohamed.R;
import java.util.Calendar;
import java.util.Date;
 
public class Alarm_ZkrAlgomaa extends BroadcastReceiver {
   
    static MediaPlayer media=new MediaPlayer();
    PowerManager pm;
    PowerManager.WakeLock wakelok,wakelok2;
  static  AlarmManager mAlarmManager;
  static PendingIntent mPendingIntent;
    AudioManager mAudioManager; 
    Date mDate=new Date();
	static  char AM_PM_g='ص';
	static String[] time_g=new String[2];
	
    @Override
    public void onReceive(final Context context, Intent intent) {
        WakeLocker.acquire(context);
        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		if(mAudioManager.getRingerMode()==AudioManager.RINGER_MODE_NORMAL){
			 mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,100,AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);
		}
		media=MediaPlayer.create(context,R.raw.salat_alnabi_alghamady);
      if(mDate.getHours()==10&&mDate.getMinutes()<5){
		media.start(); 
		}
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

                @Override
                public void onCompletion(MediaPlayer p1) {
                    
                    Thread th=new Thread(){
                        public void run(){
                            media=MediaPlayer.create(context,R.raw.mohummed);
                            media.start();
                            try {
                                sleep(12000);
                                media=MediaPlayer.create(context,R.raw.mohummed);
                                media.start();
                                sleep(12000);
                                media=MediaPlayer.create(context,R.raw.mohummed);
                                media.start();
                            } catch (InterruptedException e) {}
                            
                        }
                    };th.start();
                    
                    
                }
                
            
        });
                
         myNotfication(context);
        setAlarm(context);
        WakeLocker.release();
    }
    public void setAlarm(Context c){
        Intent intent = new Intent(c, Alarm_SalatMohamed.class);
        mPendingIntent = PendingIntent.getBroadcast(c, 0, intent, 0);
        mAlarmManager = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        //mAlarmManager.cancel(mPendingIntent);

        Calendar d=Calendar.getInstance();
         d.setTimeInMillis(System.currentTimeMillis());
         d.add(Calendar.WEEK_OF_YEAR,1);
         d.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
         d.set(Calendar.HOUR_OF_DAY,10); 
         d.set(Calendar.MINUTE,0); 
         d.set(Calendar.SECOND,0);
         d.set(Calendar.MILLISECOND,0);

       mAlarmManager.cancel(mPendingIntent);
        //mPendingIntent.cancel();
        AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(d.getTimeInMillis(), mPendingIntent);
        mAlarmManager.setAlarmClock(alarmClockInfo,mPendingIntent);
	}
	
	public  Notification myNotfication(Context context){
		time_g[0]=String.valueOf(mDate.getHours());
		time_g[1]=String.valueOf(mDate.getMinutes());
		Change_Time_24Hours_To_12Hours_g(time_g[0]);
		Change_Minute_To_Tow_Numper_g(time_g[1]);
        Notification.Builder builder=new Notification.Builder(context,"mynotficationG");
        Notification notification=builder
            .setSmallIcon(R.drawable.icon)
            // .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon))
            //.setStyle(new Notification.MediaStyle())
            .setContentTitle("هل صليت على النبي اليوم")
            .setContentText(time_g[0]+":"+time_g[1]+" "+AM_PM_g)
            .setShowWhen(true) 
            .build();
        NotificationChannel c=new NotificationChannel("mynotficationG","ذكر الجمعة",NotificationManager.IMPORTANCE_LOW);
        NotificationManager manager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(c);
        manager.notify(4,notification);
        return notification;
    }//end mynotification
	
	public void Change_Minute_To_Tow_Numper_g(String Minute){
		char[] minute_m= Minute.toCharArray();
		if(minute_m.length==1){
			String num="0"+Minute;
			time_g[1]=num;
		}
	}//End Change_Minute_To_Tow_Numper_M


	public void Change_Time_24Hours_To_12Hours_g(String Hours){
		if(Hours.equals("12")){
			AM_PM_g='م';
		} else if(Hours.equals("13")){
			time_g[0]="1";
			AM_PM_g='م';
		}else if(Hours.equals("14")){
			time_g[0]="2";
			AM_PM_g='م';
		}else if(Hours.equals("15")){
			time_g[0]="3";
			AM_PM_g='م';
		}else if(Hours.equals("16")){
			time_g[0]="4";
			AM_PM_g='م';
		}else if(Hours.equals("17")){
			time_g[0]="5";
			AM_PM_g='م';
		}else if(Hours.equals("18")){
			time_g[0]="6";
			AM_PM_g='م';
		}else if(Hours.equals("19")){
			time_g[0]="7";
			AM_PM_g='م';
		}else if(Hours.equals("20")){
			time_g[0]="8";
			AM_PM_g='م';
		}else if(Hours.equals("21")){
			time_g[0]="9";
			AM_PM_g='م';
		}else if(Hours.equals("22")){
			time_g[0]="10";
			AM_PM_g='م';
		}else if(Hours.equals("23")){
			time_g[0]="11";
			AM_PM_g='م';
		}else if(Hours.equals("0")){
			time_g[0]="12";
			AM_PM_g='ص';
		}else{
			AM_PM_g='ص';
		}

	}//end Change_Time_24Hours_To_12Hours_M
	
}
