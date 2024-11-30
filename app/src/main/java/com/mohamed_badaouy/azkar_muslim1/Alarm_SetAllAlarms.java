package com.mohamed_badaouy.azkar_muslim1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;
import java.util.Calendar;
import android.text.ClipboardManager;

public class Alarm_SetAllAlarms extends BroadcastReceiver {
  
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;
    PowerManager pm;
    PowerManager.WakeLock wakelok;
    AlarmManager  salatMohAlarmManager;PendingIntent  salatMohPendingIntent;
	
	/*static PendingIntent  AzkarSabahPendingIntent;
	static AlarmManager  azkarSabahAlarmManager;*/
	
    @Override
    public void onReceive(Context context, Intent intent) {
      try{
 /////////////////////// Azkar Qiam Allil Alarm /////////////////////////////////////////
	
       Intent qiam= new Intent(context, Alarm_QiamAllil.class);
        mPendingIntent = PendingIntent.getBroadcast(context, 0, qiam, 0);
        mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Calendar d=Calendar.getInstance();
      //  if(d!=null){
		d.setTimeInMillis(System.currentTimeMillis());
        d.set(Calendar.HOUR_OF_DAY,22); 
        d.set(Calendar.MINUTE,0); 
        d.set(Calendar.SECOND,0);
        d.set(Calendar.MILLISECOND,0);
		//}
		//if(d.getTimeInMillis()>System.currentTimeMillis()){
		mAlarmManager.cancel(mPendingIntent);
        AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(d.getTimeInMillis(), mPendingIntent);
        mAlarmManager.setAlarmClock(alarmClockInfo,mPendingIntent);
       /*  }else{
			 d.add(Calendar.DAY_OF_YEAR,1);
			 mAlarmManager.cancel(mPendingIntent);
			 AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(d.getTimeInMillis(), mPendingIntent);
			 mAlarmManager.setAlarmClock(alarmClockInfo,mPendingIntent);
		 }*/
		// Toast.makeText(context, d.getTime()+"", Toast.LENGTH_SHORT).show();

///////////////////////////////SalatMohamed And ShowAzkarInScreen Alarms///////////////////////////////////////////////////
		  Intent salatMohamed=new Intent(context,Alarm_SalatMohamed.class);
		 context. sendBroadcast(salatMohamed);
		  
		 Intent Alarm_ShowAzkarInScreen=new Intent(context,Alarm_ShowAzkarInScreen.class);
		 context.sendBroadcast(Alarm_ShowAzkarInScreen);
		  
		/*  Intent salatMohamed= new Intent(context, Alarm_SalatMohamed.class);
         salatMohPendingIntent = PendingIntent.getBroadcast(context, 0, salatMohamed, PendingIntent.FLAG_UPDATE_CURRENT);
         salatMohAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Calendar d2=Calendar.getInstance();
        d2.setTimeInMillis(System.currentTimeMillis()+10000);
       /* d2.set(Calendar.HOUR_OF_DAY,22); 
        d2.set(Calendar.MINUTE,0); 
        d2.set(Calendar.SECOND,0);
        d2.set(Calendar.MILLISECOND,0);*/
		/*salatMohAlarmManager.cancel(salatMohPendingIntent);
        AlarmManager.AlarmClockInfo alarmClockInfo2 = new AlarmManager.AlarmClockInfo(d2.getTimeInMillis(), salatMohPendingIntent);
       salatMohAlarmManager.setAlarmClock(alarmClockInfo2,salatMohPendingIntent);
        /////////////
		
        //context.sendBroadcast(new Intent(context,Alarm_QiamAllil.class));
     */
/////////////////////// Azkar Algomaa Alarm /////////////////////////////////////////
       Intent ZkrAlgomaa= new Intent(context,Alarm_ZkrAlgomaa.class);
     //     context.sendBroadcast(ZkrAlgomaa);
		  PendingIntent  zkrPendingIntent = PendingIntent.getBroadcast(context, 0, ZkrAlgomaa, 0);
        AlarmManager  zkrAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Calendar d3=Calendar.getInstance();
		//if(d3!=null){
		d3.setTimeInMillis(System.currentTimeMillis());
        d3.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
        d3.set(Calendar.HOUR_OF_DAY,10); 
        d3.set(Calendar.MINUTE,0); 
        d3.set(Calendar.SECOND,0);
        d3.set(Calendar.MILLISECOND,0);
		//}
		//if(d3.getTimeInMillis()>System.currentTimeMillis()){
		zkrAlarmManager.cancel(zkrPendingIntent);
        AlarmManager.AlarmClockInfo alarmClockInfo3 = new AlarmManager.AlarmClockInfo(d3.getTimeInMillis(), zkrPendingIntent);
       zkrAlarmManager.setAlarmClock(alarmClockInfo3,zkrPendingIntent);
	   /* }else{
			d3.add(Calendar.WEEK_OF_YEAR,1);
			zkrAlarmManager.cancel(zkrPendingIntent);
			AlarmManager.AlarmClockInfo alarmClockInfo3 = new AlarmManager.AlarmClockInfo(d3.getTimeInMillis(), zkrPendingIntent);
			zkrAlarmManager.setAlarmClock(alarmClockInfo3,zkrPendingIntent);
	
		}*/
//////////////////////////////////////////////////////////////////////////////////
		
	   
/////////////////////// Azkar Sabah Alarm /////////////////////////////////////////
    Intent AzkarSabah= new Intent(context,Alarm_AzkarSabahService.class);
     PendingIntent  AzkarSabahPendingIntent = PendingIntent.getService(context, 0, AzkarSabah, 0);
       AlarmManager  azkarSabahAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		int hour=context.	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",context.MODE_PRIVATE).getInt("HoursSabah",6);
		int minute=context.	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",context.MODE_PRIVATE).getInt("MinuteSabah",0);
		
		Calendar d4=Calendar.getInstance();
		//  if(d4!=null){
		d4.setTimeInMillis(System.currentTimeMillis());
        d4.set(Calendar.HOUR_OF_DAY,hour); 
        d4.set(Calendar.MINUTE,minute); 
        d4.set(Calendar.SECOND,0);
        d4.set(Calendar.MILLISECOND,0);
		//}
		//if(d4.getTimeInMillis()>System.currentTimeMillis()){
		azkarSabahAlarmManager.cancel(AzkarSabahPendingIntent);
		AlarmManager.AlarmClockInfo alarmClockInfo4 = new AlarmManager.AlarmClockInfo(d4.getTimeInMillis(), AzkarSabahPendingIntent);
		azkarSabahAlarmManager.setAlarmClock(alarmClockInfo4,AzkarSabahPendingIntent);
		/*}else{
			d4.add(Calendar.DAY_OF_YEAR,1);
			azkarSabahAlarmManager.cancel(AzkarSabahPendingIntent);
			AlarmManager.AlarmClockInfo alarmClockInfo4 = new AlarmManager.AlarmClockInfo(d4.getTimeInMillis(), AzkarSabahPendingIntent);
			azkarSabahAlarmManager.setAlarmClock(alarmClockInfo4,AzkarSabahPendingIntent);
		}*/
//////////////////////////////////////////////////////////////////////////////////
		  

/////////////////////// Azkar Masaa Alarm /////////////////////////////////////////
		  
	Intent AzkarMasaa= new Intent(context,Alarm_AzkarMasaaService.class);
        PendingIntent  AzkarMasaaPendingIntent = PendingIntent.getService(context, 0, AzkarMasaa, 0);
        AlarmManager  azkarMasaaAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        int hourmasaa=context.	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",context.MODE_PRIVATE).getInt("HoursMasaa",17);
		int minutemasaa=context.	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",context.MODE_PRIVATE).getInt("MinuteMasaa",0);
		
		Calendar d5=Calendar.getInstance();
		//  if(d5!=null){
		d5.setTimeInMillis(System.currentTimeMillis());
        d5.set(Calendar.HOUR_OF_DAY,hourmasaa); 
        d5.set(Calendar.MINUTE,minutemasaa); 
        d5.set(Calendar.SECOND,0);
        d5.set(Calendar.MILLISECOND,0);
		//}
		// if(d5.getTimeInMillis()>System.currentTimeMillis()){
		azkarMasaaAlarmManager.cancel(AzkarMasaaPendingIntent);
        AlarmManager.AlarmClockInfo alarmClockInfo5= new AlarmManager.AlarmClockInfo(d5.getTimeInMillis(), AzkarMasaaPendingIntent);
		azkarMasaaAlarmManager.setAlarmClock(alarmClockInfo5,AzkarMasaaPendingIntent);
		/*}else{
			d5.add(Calendar.DAY_OF_YEAR,1);
			azkarMasaaAlarmManager.cancel(AzkarMasaaPendingIntent);
			AlarmManager.AlarmClockInfo alarmClockInfo5= new AlarmManager.AlarmClockInfo(d5.getTimeInMillis(), AzkarMasaaPendingIntent);
			azkarMasaaAlarmManager.setAlarmClock(alarmClockInfo5,AzkarMasaaPendingIntent);
		}*/
//////////////////////////////////////////////////////////////////////////////////

	  }catch(Exception e){Toast.makeText(context,e.getMessage()+"set all alarm", Toast.LENGTH_LONG).show();
	    ClipboardManager clip=(ClipboardManager)context.getSystemService(context.CLIPBOARD_SERVICE);
		clip.setText(e.getMessage());
	  }//end catch
           }
    
}
