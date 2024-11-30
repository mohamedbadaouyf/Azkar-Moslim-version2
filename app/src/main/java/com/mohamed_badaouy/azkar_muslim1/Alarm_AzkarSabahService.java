package com.mohamed_badaouy.azkar_muslim1;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
//import com.mohamed_badaouy.azkar_muslim.R;
import java.util.Calendar;
import java.util.Date;

public class Alarm_AzkarSabahService extends Service {
	Date mDate;
	PowerManager pm;
    PowerManager.WakeLock wakelok,wakelok2;
	static WindowManager mWindowManager;
    static	WindowManager.LayoutParams params;
	static View	mChatHeadView;
	static LayoutInflater inflater;
	Animation anim,anim2;
	TextView tv_ImageAlarmAzkar,tv_ImageCloseWindo;
	static MediaPlayer sound=new MediaPlayer();
	static boolean show_WindowManager=false;
	static  char AM_PM_S='ص';
	static String[] time_S;
	
	@Override
	public IBinder onBind(Intent p1) {
		return null;
	}
 
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		WakeLocker.acquire(this);
		anim=AnimationUtils.loadAnimation(this,R.anim.translate_out);
		anim2=AnimationUtils.loadAnimation(this,R.anim.translate2);
		mDate=new Date();

		boolean RunAndCloseAlarmAzkar=getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).getBoolean("RunAndCloseAlarmAzkar",false);
		int hour=	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).getInt("HoursSabah",6);
		int minute=	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).getInt("MinuteSabah",0);
		if(show_WindowManager==true){
			//mWindowManager.removeView(mChatHeadView);
			//show_WindowManager=false;
			//mWindowManager=null;
		}

		if(RunAndCloseAlarmAzkar==true){
			if(new Date().getHours()==hour&&new Date().getMinutes()==minute){
               // myNotfication(this);
				create_windo(this);
				//myNotfication(this);
				startForeground(2,myNotfication(this));
				sound.stop();
				sound=MediaPlayer.create(Alarm_AzkarSabahService.this,R.raw.morning1);
				sound.start();
				sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

						@Override
						public void onCompletion(MediaPlayer p1) {
							sound=MediaPlayer.create(Alarm_AzkarSabahService.this,R.raw.morning);
							sound.start();
							//NextAlarm(context);
						}


					});

			}//end if
			NextAlarm(this);
		}//end if

		WakeLocker.release();
		
		return START_STICKY;
	}
	
    
    public  Notification myNotfication(Context context){
		String alarmAzkarsabah=context.	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",context.MODE_PRIVATE).getString("AlarmAzkarSabah","6:0");
		time_S=alarmAzkarsabah.split(":");
		Change_Time_24Hours_To_12Hours_S(time_S[0]);
		Change_Minute_To_Tow_Numper_S(time_S[1]);
        Notification.Builder builder=new Notification.Builder(context,"mynotficationS");
        Notification notification=builder
            .setSmallIcon(R.drawable.icon)
            // .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon))
            //.setStyle(new Notification.MediaStyle())
            .setContentTitle("حآن وقت قراءة أذكار الصباح")
            .setContentText(time_S[0]+":"+time_S[1]+" "+AM_PM_S)
            .setShowWhen(true)
            .build();
        NotificationChannel c=new NotificationChannel("mynotficationS","أذكار الصباح",NotificationManager.IMPORTANCE_LOW);
        NotificationManager manager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(c);
        manager.notify(2,notification);
        return notification;
    }

	public void NextAlarm(Context context){
		Intent AzkarSabah= new Intent(context,Alarm_AzkarSabahService.class);
        PendingIntent  AzkarSabahPendingIntent = PendingIntent.getService(context, 0, AzkarSabah, 0);
        AlarmManager  azkarSabahAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		int hour=context.	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",context.MODE_PRIVATE).getInt("HoursSabah",6);
		int minute=context.	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",context.MODE_PRIVATE).getInt("MinuteSabah",0);
		//Toast.makeText(context,hour+ "", Toast.LENGTH_LONG).show();
		Calendar d4=Calendar.getInstance();
		d4.setTimeInMillis(System.currentTimeMillis());
		d4.add(Calendar.DAY_OF_YEAR,1);
		d4.set(Calendar.HOUR_OF_DAY,hour); 
        d4.set(Calendar.MINUTE,minute); 
        d4.set(Calendar.SECOND,0); 
        d4.set(Calendar.MILLISECOND,0);
		azkarSabahAlarmManager.cancel(AzkarSabahPendingIntent);
        AlarmManager.AlarmClockInfo alarmClockInfo4 = new AlarmManager.AlarmClockInfo(d4.getTimeInMillis(), AzkarSabahPendingIntent);
		azkarSabahAlarmManager.setAlarmClock(alarmClockInfo4,AzkarSabahPendingIntent);

	}

	public void create_windo(final Context p1){

		try{
			inflater=LayoutInflater.from(p1);
			//mChatHeadView = LayoutInflater.from(p1).inflate(R.layout.raw_itm_windo, null);
			mChatHeadView=inflater.inflate(R.layout.raw_itme_windo_alarm_azkar,null);

			//Add the view to the window.
			//final WindowManager.LayoutParams params 
			params= new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);

			//Specify the chat head position
			params.gravity = Gravity.TOP | Gravity.RIGHT;        //Initially view will be added to top-left corner
			params.x = 0;
			params.y = 100;

			//Add the view to the window
			mWindowManager = (WindowManager) p1.getSystemService(Context.WINDOW_SERVICE);
			mWindowManager.addView(mChatHeadView, params);
			show_WindowManager=true;
		    tv_ImageAlarmAzkar=mChatHeadView.findViewById(R.id.raw_itm_windoTextView_ImageAlarmAzkar);
			tv_ImageCloseWindo=mChatHeadView.findViewById(R.id.raw_itm_windoTextView_ImageClose);

			tv_ImageAlarmAzkar.setBackgroundDrawable(p1.getDrawable(R.drawable.outer_m1));

			tv_ImageAlarmAzkar.startAnimation(anim2);
			tv_ImageCloseWindo.startAnimation(anim2);


			tv_ImageAlarmAzkar.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p11) {
						Intent i=new Intent(p1,ReadZkr.class);
						i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
						ReadZkr.currentAzkar=0;
						p1.startActivity(i);
					}


				});



			tv_ImageCloseWindo.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(final View p11)

					{

						tv_ImageAlarmAzkar.startAnimation(anim);
						tv_ImageCloseWindo.startAnimation(anim);
						Thread th=new Thread(){

							public void run(){

								try
								{


									//tv_ImageAlarmAzkar.startAnimation(anim);
									//tv_ImageCloseWindo.startAnimation(anim);
									sleep(500);
									mWindowManager.removeView(mChatHeadView);
									show_WindowManager=false;
									stopForeground(2);
									NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
									manager.cancel(2);
									//stopSelf();
									//mWindowManager=null;
								

								}
								catch (Exception e)
								{
									Toast.makeText(p1, e.getMessage(),Toast.LENGTH_LONG).show();
								}
							}

						};th.start();	

					}


				});

		}catch(Exception e){
			Toast.makeText(p1, e.getMessage(),Toast.LENGTH_LONG).show();
		}
	}




	public void Change_Minute_To_Tow_Numper_S(String Minute){
		char[] minute_s= Minute.toCharArray();
		if(minute_s.length==1){
			String num="0"+Minute;
			time_S[1]=num;
		}
	}//End Change_Minute_To_Tow_Numper_S


	public void Change_Time_24Hours_To_12Hours_S(String Hours){
		if(Hours.equals("12")){
			AM_PM_S='م';
		} else if(Hours.equals("13")){
			time_S[0]="1";
			AM_PM_S='م';
		}else if(Hours.equals("14")){
			time_S[0]="2";
			AM_PM_S='م';
		}else if(Hours.equals("15")){
			time_S[0]="3";
			AM_PM_S='م';
		}else if(Hours.equals("16")){
			time_S[0]="4";
			AM_PM_S='م';
		}else if(Hours.equals("17")){
			time_S[0]="5";
			AM_PM_S='م';
		}else if(Hours.equals("18")){
			time_S[0]="6";
			AM_PM_S='م';
		}else if(Hours.equals("19")){
			time_S[0]="7";
			AM_PM_S='م';
		}else if(Hours.equals("20")){
			time_S[0]="8";
			AM_PM_S='م';
		}else if(Hours.equals("21")){
			time_S[0]="9";
			AM_PM_S='م';
		}else if(Hours.equals("22")){
			time_S[0]="10";
			AM_PM_S='م';
		}else if(Hours.equals("23")){
			time_S[0]="11";
			AM_PM_S='م';
		}else if(Hours.equals("0")){
			time_S[0]="12";
			AM_PM_S='ص';
		}else{
			AM_PM_S='ص';
		}

	}//end Change_Time_24Hours_To_12Hours_S

	@Override
	public void onDestroy() {
		mWindowManager.removeView(mChatHeadView);
		//show_WindowManager=false;
		super.onDestroy();
	}
	
    
}
