package com.mohamed_badaouy.azkar_muslim1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import com.mohamed_badaouy.azkar_muslim1.SQLiteDatabase.DatabaseAllAzkar;
import java.util.ArrayList;
import java.util.Random;
import android.text.Html;
import android.graphics.Typeface;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public class MyServiceforeground extends Service {
	static ArrayList<List_item>array2=new ArrayList();
	static ArrayList<String>array=new ArrayList();
	static WindowManager mWindowManager;
	static WindowManager.LayoutParams params;
    static View mChatHeadView;
	LayoutInflater inflater;
	WindowManager.LayoutParams wi;
	static CountDownTimer cound,cound2;
	Animation anim,anim2;
	static TextView t;
	static boolean mboolean=false;
	Data_class mData_class=new Data_class();
	static boolean show_WindowManager=false;
	static DatabaseAllAzkar ChooseFavorite;
	static Thread mThread=new Thread();
	static int checkFromShowZkrForColor=0;
	static StringBuilder sbr;
	static int mRandom=0;
	
	@Override
	public IBinder onBind(Intent p11) {
		return null;
	}

	@Override
	public void onCreate() {
		
		super.onCreate();
	}

	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		anim=AnimationUtils.loadAnimation(this,R.anim.translate_out);
		anim2=AnimationUtils.loadAnimation(this,R.anim.translate2);
		IntentFilter in=new IntentFilter();
		in.addAction("Read_Word_Color");
		in.addAction("Show_Word");
		registerReceiver(br,in);
		//t.startAnimation(anim);
		//sleep(500);
		ChooseFavorite=new DatabaseAllAzkar(this, 9000);

		if(show_WindowManager==true){    
			mWindowManager.removeView(mChatHeadView);
	        show_WindowManager=false;
			//mWindowManager=null;
		}

		if (cound != null) { 
			cound.cancel();
		}
		array2.clear();
		array2=ChooseFavorite.read_data();
		create_windo(MyServiceforeground.this);
		windo(MyServiceforeground.this);
		return START_STICKY;
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
			show_WindowManager=true;
			t=mChatHeadView.findViewById(R.id.raw_itm_windoTextView);
			Random r=new Random();
			 final int rr=r.nextInt(array2.size());
			t.setText(Html.fromHtml(array2.get(rr).TextZkr+""));
			t.startAnimation(anim2); 
			
/////////////////////////////// Settings mChatHeadView And TextView t/////////////////////////////////////////////////////
	       //// settings type font /////
			int ChooseFont=getSharedPreferences("SettingsChangeTypeFont",MODE_PRIVATE).getInt("font",1);
			if(ChooseFont==1){
				t.setTypeface(Typeface.createFromAsset(getAssets(),"DroidKufi-Bold.ttf"));
			}else if(ChooseFont==2){
				t.setTypeface(Typeface.createFromAsset(getAssets(),"DroidNaskh-Bold.ttf"));
			}else{t.setTypeface(Typeface.createFromAsset(getAssets(),"khalid-art-bold.ttf"));}
			             
			//// settings color /////
			int ChooseColor=getSharedPreferences("SettingsChangeColorFont",MODE_PRIVATE).getInt("color",1);
			if(ChooseColor==1){
				t.setBackgroundDrawable(getDrawable(R.drawable.shape_show_azkar_in_screen));
			}else if(ChooseColor==2){
				t.setBackgroundDrawable(getDrawable(R.drawable.shape_show_azkar_in_screen2));
			}else{t.setBackgroundDrawable(getDrawable(R.drawable.shape_show_azkar_in_screen3));}
			
			
////////////////////////////// End Settings mChatHeadView And TextView t/////////////////////////////////////////////////
				
			/*sbr=new StringBuilder();
			final String[] a=array2.get(rr).TextZkr.trim().split(" ");	
			mThread=new Thread(){
				public void run(){  
					
					/*try {
						sleep(1000);
					} catch (InterruptedException e) {}
					for (int i = 0; i < a.length; i++) {
                           
					for (int ii = 0; ii < a.length; ii++) {
						if(ii==i){
							String text11=("<font color="+"#FD4216"+">"+a[ii]+"</font>");
							sbr.append(""+text11+" ");
						}else{
							sbr.append(""+a[ii]+" ");
						}

						}//end for tow
                         Intent intent=new Intent();
						intent.setAction("Read_Word_Color");
						sendBroadcast(intent);
						
						try {
							sleep(190);
							sbr.delete(0,1000000);
							//sbr=null;
							

						} catch (InterruptedException e) {}
					}//end for one
					Intent intent=new Intent();
					mRandom=rr;
					intent.setAction("Show_Word");
					sendBroadcast(intent);
					//t.setText(Html.fromHtml(array2.get(rr).TextZkr+""));
                   // mThread=null;
				}//end run
                 
			};mThread.start();*/
		
		///////////////**
			t.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)

					{
						

						Thread th=new Thread(){

							public void run(){

								try
								{
                                   
									//	mWindowManager = (WindowManager) p1.getSystemService(p1.WINDOW_SERVICE);
									//	mWindowManager.addView(mChatHeadView, params);
									//	create_windo(p1);
									//t.startAnimation(anim);
									//sleep(500);
									mWindowManager.removeView(mChatHeadView);
									show_WindowManager=false;
									
									//stopSelf();
									//mWindowManager=null;
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
				//stopSelf();
				Thread th=new Thread(){

					public void run(){

						try
						{

							
						//	t.startAnimation(anim);
							//Thread.sleep(500);
							mWindowManager.removeView(mChatHeadView);
							show_WindowManager=false;
							//mWindowManager=null;
							stopSelf();
							
						}
						catch (Exception e)
						{
							//Toast.makeText(p1, e.getMessage(),Toast.LENGTH_LONG).show();
						}
					}

				};th.start();
			} 


		};cound.start();

	}//end windo
	
	

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		//Toast.makeText(getApplication(), "stop", Toast.LENGTH_SHORT).show();
		super.onTaskRemoved(rootIntent);
	}

	@Override
	public void onDestroy() {
		Thread th=new Thread(){
			public void run(){
				try {
					t.startAnimation(anim);
					sleep(500);
					mWindowManager.removeView(mChatHeadView);
				    show_WindowManager=false;
					} catch (InterruptedException e) {}
				
				
			} 
		};//th.start();
		
		
	   super.onDestroy();
	}
	
    
    BroadcastReceiver br=new BroadcastReceiver(){

		@Override
		public void onReceive(Context p1, Intent p2) {
			//Toast.makeText(p1, sbr+"", Toast.LENGTH_SHORT).show();
			if(p2.getAction().equals("Read_Word_Color")){
				t.setText(Html.fromHtml(sbr+""));
			}else{
				//int rr=Integer.parseInt( p2.getStringExtra("random"));
				t.setText(Html.fromHtml(array2.get(mRandom).TextZkr+""));
			}
			
		}
		
		
	};
    
}
