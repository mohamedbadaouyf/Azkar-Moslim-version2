package com.mohamed_badaouy.azkar_muslim1;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.CountDownTimer;
import android.os.Environment;
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
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;

 class MyBroadcastReceiver6 extends BroadcastReceiver
{
	PendingIntent pendingIntent;
	AlarmManager alarmManager;
	private WindowManager mWindowManager;
	WindowManager.LayoutParams params;
    private View mChatHeadView;
	LayoutInflater inflater;
	WindowManager.LayoutParams wi;
	static CountDownTimer cound;
	public static int num=0;
	Data_class d=new Data_class();
	static ArrayList<String> array=new ArrayList();
	static ArrayList<String> array2=new ArrayList();
	Animation anim,anim2;
	TextView t;
	static boolean mboolean=false;
	int i=0;
	
	@Override
	public void onReceive( final Context p1, Intent p2)
	{
		PowerManager pm = (PowerManager) p1.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
		//wl.acquire();
			anim=AnimationUtils.loadAnimation(p1,R.anim.translate_out);
		    anim2=AnimationUtils.loadAnimation(p1,R.anim.translate2);
			//array=d.getSharedPreferenceses();
			//d.getSharedPreferenceses();
			//array.add("666");
		
		String a="";
		String a2="";
		
		
		SharedPreferences sharedPreferences = p1.getSharedPreferences("SaveFavorite", Context.MODE_PRIVATE);
		
		//writer.append(mUserName+","+mPrice+","+mAlmoqadema+","+mAlqast+mCountMonth+","+mMonyAllMonth+"\n");
		
		for (int i = 0; i <1000; i++) {
			if(i>=0){
				a=sharedPreferences
					.getString(i+"","");
				a2=sharedPreferences
					.getString(i+"app","");
			}

			if(a!=""){
				array.add(a);
			}
			if(a2!=""){
				array.add(a2);
			}


		}
		//array=d.Azkary();
		/*array.add(a2=p1.getSharedPreferences("SaveFavorite",p1.MODE_PRIVATE)
			.getString(0+"app",""));*/
		array.add("6");
		//array=array2;
		//int timeInSec =1;
		//String i=Build.VERSION.RELEASE;
		//Toast.makeText(p1,"ssss"+"\n"+i+"",Toast.LENGTH_LONG).show();
		
		
		
		windo(p1);
		create_windo(p1);
		//wl.release();
		/*Thread th=new Thread(){
			public void run(){
				try{	
				
						t.startAnimation(anim);
						sleep(500);
						mWindowManager = (WindowManager) p1.getSystemService(Context.WINDOW_SERVICE);
						mWindowManager.removeView(mChatHeadView);
					}
				catch (InterruptedException e)
				{}


			}
		};th.start();*/
		//num++;
		//}
			
		
	}

	public void create_windo(Context p1){

		try{
			inflater=LayoutInflater.from(p1);
			//mChatHeadView = LayoutInflater.from(p1).inflate(R.layout.raw_itm_windo, null);
           mChatHeadView=inflater.inflate(R.layout.raw_itm_windo,null);
			
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
		mboolean=true;
		  t=mChatHeadView.findViewById(R.id.raw_itm_windoTextView);
			Random r=new Random();
			int rr=r.nextInt(array.size());
		    t.setText(array.get(rr));
			t.startAnimation(anim2);
			t.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					
					{
						//mWindowManager.removeView(mChatHeadView);
						//cound.start();
						//cound.onFinish();
						
						Thread th=new Thread(){

							public void run(){

								try
								{

									//	mWindowManager = (WindowManager) p1.getSystemService(p1.WINDOW_SERVICE);
									//	mWindowManager.addView(mChatHeadView, params);
									//	create_windo(p1);
									t.startAnimation(anim);
									sleep(500);
									mWindowManager.removeView(mChatHeadView);
									//cound.cancel();
									//create_windo(p1);
									
								}
								catch (Exception e)
								{
									//Toast.makeText(p1, e.getMessage(),Toast.LENGTH_LONG).show();
								}
							}

						};th.start();		}
					
				
			});

		}catch(Exception e){
			Toast.makeText(p1, e.getMessage(),Toast.LENGTH_LONG).show();
		}
	}
	
	
	public void windo(final Context p1){
		cound=new CountDownTimer(15000,1000){

			@Override
			public void onTick(long p11)
			{
				//mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
				//mWindowManager.addView(mChatHeadView, params);
             //create_windo(p1);
			}

			@Override
			public void onFinish()
			{
				Thread th=new Thread(){

					public void run(){

						try
						{
 
						//	mWindowManager = (WindowManager) p1.getSystemService(p1.WINDOW_SERVICE);
						//	mWindowManager.addView(mChatHeadView, params);
						//	create_windo(p1);
							t.startAnimation(anim);
							Thread.sleep(500);
							mWindowManager.removeView(mChatHeadView);
							//cound.cancel();
							//create_windo(p1);
							/*sleep(30000);
							Intent i=new Intent(p1,MyBroadcastReceiver.class);
							p1.sendBroadcast(i);*/
						}
						catch (Exception e)
						{
							//Toast.makeText(p1, e.getMessage(),Toast.LENGTH_LONG).show();
						}
					}

				};th.start();
			}


		};cound.start();

	}
	
	
	
	
}
