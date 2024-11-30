package com.mohamed_badaouy.azkar_muslim1;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import android.text.ClipboardManager;
import android.graphics.Typeface;

public class FragmentMain extends Fragment{
    
    static LinearLayout mLinearLayout;
    ImageButton btn_Settings;
	TextView tv1Minute,tv5Minute,tv10Minute,tv15Minute,tvAzkarSabah,tvAzkarAlmasaa,tvAzkarAlnooom,tvTasbeeh,tvAya;
	ReadZkr mReadZkr=new ReadZkr();
    Date DateNow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v=getLayoutInflater().inflate(R.layout.fragment_main,container,false);
		btn_Settings=v.findViewById(R.id.fragment_mainImageButton_settings);
		tv1Minute=v.findViewById(R.id.fragment_mainTextView_1Minute);
		tv5Minute=v.findViewById(R.id.fragment_mainTextView_5Minute);
		tv10Minute=v.findViewById(R.id.fragment_mainTextView_10Minute);
		tv15Minute=v.findViewById(R.id.fragment_mainTextView_15Minute);
		tvAzkarSabah=v.findViewById(R.id.fragment_mainTextView_azkarSabah);
		tvAzkarAlmasaa=v.findViewById(R.id.fragment_mainTextView_AzkarAlnoom);
		tvAzkarAlnooom=v.findViewById(R.id.fragment_mainTextView_azkarEstiqazMinAlnoom);
		tvTasbeeh=v.findViewById(R.id.fragment_mainTextView_Tasbeeh);
		tvAya=v.findViewById(R.id.fragment_mainTextView_Aya);
		
		Typeface type=Typeface.createFromAsset(getContext().getAssets(),"aldhabi.ttf");
		tvAya.setTypeface(type);
		
		mLinearLayout=v.findViewById(R.id.fragment_mainLinearLayout);
		DateNow=new Date();
		//getContext().sendBroadcast(new Intent(getContext(),Alarm_ZkrAlgomaa.class));
		int repeateShowZkr=	getContext().getSharedPreferences("repeate_show_azkar",getContext().MODE_PRIVATE).getInt("checked",0);	
		if(repeateShowZkr==1){
			changeBackgroundTvMinute(); 
			tv1Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tv_minute));
		}else if(repeateShowZkr==2){
			changeBackgroundTvMinute();
			tv5Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tv_minute));
		}else if(repeateShowZkr==3){
			changeBackgroundTvMinute();
			tv10Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tv_minute));
		}else if(repeateShowZkr==4){
			changeBackgroundTvMinute();
			tv15Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tv_minute));
		}else{
			changeBackgroundTvMinute();
			tv1Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tv_minute));
		} 
		
		///////// change background LinearLayout ////
		try{
		Date d=new Date();
		int houer=d.getHours();
		if(houer==7||houer==8||houer==9||houer==10||houer==11||houer==12||houer==13||houer==14){
			mLinearLayout.setBackgroundDrawable(getContext().getDrawable(R.drawable.bk_zohr));
		}else if(houer==15||houer==16||houer==17){
			mLinearLayout.setBackgroundDrawable(getContext().getDrawable(R.drawable.bk_asr));
		}else if(houer==18||houer==19){
			mLinearLayout.setBackgroundDrawable(getContext().getDrawable(R.drawable.bk_magh));
		}else if(houer==20||houer==21||houer==22||houer==23||houer==0){
			mLinearLayout.setBackgroundDrawable(getContext().getDrawable(R.drawable.bk_esha));
		}else if(houer==1||houer==2||houer==3||houer==4||houer==5||houer==6){
			mLinearLayout.setBackgroundDrawable(getContext().getDrawable(R.drawable.bk_fgr));
		}	
		}catch(Exception e){
			ClipboardManager c=(ClipboardManager)getContext().getSystemService(getContext().CLIPBOARD_SERVICE);
			c.setText(e.getMessage());
			Toast.makeText(getContext(), e.getMessage()+"", Toast.LENGTH_LONG).show();}
		///////////////////////////////////////////////
		btn_Settings.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					startActivity(new Intent(getContext(),SettingsActivity.class));
				}
				
		   
	   });
	   
		///////////////////////////////////////////////
		tv1Minute.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					changeBackgroundTvMinute();
					tv1Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tv_minute));
					getContext().getSharedPreferences("repeate_show_azkar",getContext().MODE_PRIVATE)
					.edit().putInt("Time",1*60*1000)
					.putInt("checked",1)
					.apply();
					Intent intent2 = new Intent(getContext(), MyService.class);
					//startForegroundService(intent2);
					if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
						getContext().startForegroundService(intent2);
					}else{
						getContext().startService(intent2);
					}	
					
					
					
					
				}


			});
		///////////////////////////////////////////////
		tv5Minute.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					changeBackgroundTvMinute();
					tv5Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tv_minute));
					getContext().getSharedPreferences("repeate_show_azkar",getContext().MODE_PRIVATE)
					.edit().putInt("Time",5*60*1000)
					.putInt("checked",2)
					.apply();	
					Intent intent2 = new Intent(getContext(), MyService.class);
					if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
						getContext().startForegroundService(intent2);
					}else{
						getContext().startService(intent2);
					}				
					
					
				}


			});
	   ///////////////////////////////////////////////
		tv10Minute.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					changeBackgroundTvMinute();
					tv10Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tv_minute));
					getContext().getSharedPreferences("repeate_show_azkar",getContext().MODE_PRIVATE)
					.edit().putInt("Time",10*60*1000)
					.putInt("checked",3)
					.apply();	
					Intent intent2 = new Intent(getContext(), MyService.class);
					if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
						getContext().startForegroundService(intent2);
					}else{
						getContext().startService(intent2);
					}					
					//MyServiceforeground.show_WindowManager=true;
					
				}


			});
		///////////////////////////////////////////////
		tv15Minute.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					changeBackgroundTvMinute();
					tv15Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tv_minute));
					getContext().getSharedPreferences("repeate_show_azkar",getContext().MODE_PRIVATE)
						.edit().putInt("Time",15*60*1000)
					    .putInt("checked",4)
						.apply();	
					Intent intent2 = new Intent(getContext(), MyService.class);
					if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
						getContext().startForegroundService(intent2);
					}else{
						getContext().startService(intent2);
					}					
					
				}


			});
		///////////////////////////////////////////////
		tvAzkarSabah.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					Intent i=new Intent(getContext(),ReadZkr.class);
					ReadZkr.currentAzkar=0;
					startActivity(i);
					
				}

			});
		///////////////////////////////////////////////
		tvAzkarAlmasaa.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					Intent i=new Intent(getContext(),ReadZkr.class);
					ReadZkr.currentAzkar=1;
					startActivity(i);
				}


			});
		///////////////////////////////////////////////
		tvAzkarAlnooom.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					Intent i=new Intent(getContext(),ReadZkr.class);
					ReadZkr.currentAzkar=2;
					startActivity(i);
				}


			});
		///////////////////////////////////////////////
		tvTasbeeh.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					startActivity(new Intent(getContext(),Tasbeh.class));
				}


			});
		
		
		return v;
    }
    
	
	public void changeBackgroundTvMinute(){
		//tv1Minute.getBackground().setVisible(true,true);
		//tv5Minute.getBackground().setVisible(false,false);
		tv1Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tvminute_wight));
		tv5Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tvminute_wight));
		tv10Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tvminute_wight));
		tv15Minute.setBackgroundDrawable(getContext().getDrawable(R.drawable.shape_tvminute_wight));
	}
	
	
}
