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
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Alarm_QiamAllil extends BroadcastReceiver {
    
    static MediaPlayer media=new MediaPlayer();
    PowerManager pm;
    PowerManager.WakeLock wakelok,wakelok2;
    AlarmManager mAlarmManager;
	PendingIntent mPendingIntent;
    AudioManager mAudioManager;
    Date mDate=new Date();
	
    @Override
    public void onReceive(Context context, Intent intent) {
        WakeLocker.acquire(context);
        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		int currenVolom  =  mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		if(mAudioManager.getRingerMode()==AudioManager.RINGER_MODE_NORMAL){
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,100,AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);
		} 
		
      if(mDate.getHours()==22&&mDate.getMinutes()<5){
        Random r=new Random();
        int num=r.nextInt(2);
        if(num==1){
            media.stop();
            media=MediaPlayer.create(context,R.raw.qiam_mashary);
            media.start(); 
        }else{
            media.stop();
            media=MediaPlayer.create(context,R.raw.qiam_minshawy);
            media.start(); 
        }
     }//end if check from date
      //  myNotfication(context);
        setAlarm(context);
        WakeLocker.release();
		//mAudioManager.setStreamVolume(currenVolom,100,AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);
    }
    
    public static Notification myNotfication(Context context){
        Notification.Builder builder=new Notification.Builder(context,"mynotfication");
        Notification notification=builder
            .setSmallIcon(R.drawable.ic_launcher)
            // .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon))
            //.setStyle(new Notification.MediaStyle())
            .setContentTitle("Alarm Manager Testing")
            .setContentText(new Date().getMinutes()+" : "+new Date().getHours())
            .setShowWhen(true)
            .build();
        NotificationChannel c=new NotificationChannel("mynotfication","MyChaneel",NotificationManager.IMPORTANCE_LOW);
        NotificationManager manager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(c);
        manager.notify(1,notification);
        return notification;
    }

    public void setAlarm(Context c){
        Intent intent = new Intent(c, Alarm_QiamAllil.class);
        mPendingIntent = PendingIntent.getBroadcast(c, 0, intent, 0);
        mAlarmManager = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        //mAlarmManager.cancel(mPendingIntent);

        Calendar d=Calendar.getInstance();
        d.setTimeInMillis(System.currentTimeMillis());
        d.add(Calendar.DAY_OF_YEAR,1);
        d.set(Calendar.HOUR_OF_DAY,22); 
        d.set(Calendar.MINUTE,0); 
        d.set(Calendar.SECOND,0);
        d.set(Calendar.MILLISECOND,0);
        
        mAlarmManager.cancel(mPendingIntent);
        //mPendingIntent.cancel();
        AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(d.getTimeInMillis(), mPendingIntent);
        // mAlarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,d.getTimeInMillis(), mPendingIntent);
        mAlarmManager.setAlarmClock(alarmClockInfo,mPendingIntent);
        // Log.d("tag",d.getTimeInMillis()/1000/60+"");
	}
    
}
