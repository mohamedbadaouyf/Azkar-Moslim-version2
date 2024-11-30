package com.mohamed_badaouy.azkar_muslim1;

import android.app.AlarmManager;
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
import android.widget.Toast;

public class Alarm_SalatMohamed extends BroadcastReceiver {
    
    static MediaPlayer media=new MediaPlayer();
    PowerManager pm;
    PowerManager.WakeLock wakelok,wakelok2;
    AlarmManager mAlarmManager;
	PendingIntent mPendingIntent;
	static  AudioManager mAudioManager;
    Date Date=new Date();
	static int currenVolom;
	
    @Override 
    public void onReceive(final Context context, Intent intent) {
		boolean RunAndCloseSalatMohamed=context.getSharedPreferences("SettingsAlarmSalatMohamed",context.MODE_PRIVATE).getBoolean("RunAndClose",false);
		
		if(RunAndCloseSalatMohamed==true){
		WakeLocker.acquire(context);
        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		 currenVolom  =  mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		//mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,100,AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);
       // mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,100,0);
			
	       
			if(mAudioManager.getRingerMode()==AudioManager.RINGER_MODE_NORMAL){
				if(context.getSharedPreferences("SettingsMaxSoundSalahAlaAlnapy",context.MODE_PRIVATE).getBoolean("MaxSoundSalahAlaAlnapy",false)){
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,100,0);
				} 
			}
	    
	 //  Toast.makeText(context,currenVolom +"", Toast.LENGTH_SHORT).show();
	  
	  //context.getSharedPreferences("SettingsAlarmSalatMohamed",context.MODE_PRIVATE).edit().putBoolean("RunAndClose",true).apply();
	Random r=new Random();
        int num=r.nextInt(2); 
        
		//if(media.isPlaying()==false){
		
    /*   if(num==1){
           media.stop();
            media= MediaPlayer.create(context,R.raw.mohamed);
            media.start(); 
        }else{*/
           media.stop();
            media= MediaPlayer.create(context,R.raw.mohamed2);
            media.start(); 
    //   }//end if 
	  // }//end if 
		media.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

				@Override
				public void onCompletion(MediaPlayer p1) {
					setAlarm(context);
								
					if(mAudioManager.getRingerMode()==AudioManager.RINGER_MODE_NORMAL){
						mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,currenVolom,0);
					}
					//mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,currenVolom,0);
					//Toast.makeText(context, currenVolom+"", Toast.LENGTH_LONG).show();
				}
				
			
		});
        
        
        //  myNotfication(context);
       setAlarm(context);
        WakeLocker.release();
		;
		}//end if
    }
    
    public void setAlarm(Context c){
		int x=1*60*1000;
		int TimeRepeate=c.getSharedPreferences("SettingsAlarmSalatMohamed",c.MODE_PRIVATE).getInt("TimeRepeate",x);
		
        Intent intent = new Intent(c, Alarm_SalatMohamed.class);
        mPendingIntent = PendingIntent.getBroadcast(c, 0, intent, 0);
        mAlarmManager = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        //mAlarmManager.cancel(mPendingIntent);

        Calendar d=Calendar.getInstance();
		
		//if(TimeRepeate>0){
			d.setTimeInMillis(System.currentTimeMillis()+TimeRepeate);
		//}else{
       //d.setTimeInMillis(System.currentTimeMillis()+1*60*1000);
	  // }
       /* d.add(Calendar.DAY_OF_YEAR,1);
        d.set(Calendar.HOUR_OF_DAY,22); 
        d.set(Calendar.MINUTE,0); 
        d.set(Calendar.SECOND,0);
        d.set(Calendar.MILLISECOND,0);*/

        mAlarmManager.cancel(mPendingIntent);
        //mPendingIntent.cancel();
        AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(d.getTimeInMillis(), mPendingIntent);
        // mAlarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,d.getTimeInMillis(), mPendingIntent);
        mAlarmManager.setAlarmClock(alarmClockInfo,mPendingIntent);
      // Log.d("tag",d.getTimeInMillis()/1000/60+"");
	}
}
