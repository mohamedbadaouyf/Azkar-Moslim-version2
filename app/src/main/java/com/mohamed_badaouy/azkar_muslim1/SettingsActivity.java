package com.mohamed_badaouy.azkar_muslim1;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
//import com.mohamed_badaouy.azkar_muslim.R;
import java.util.Date;
import java.io.IOException;
import android.text.ClipboardManager;


public class SettingsActivity extends Activity {
	static  Switch mswitchSalatMohamed,mSwitchAlarmAzkar,mSwitchAlarmShowAzkarInScreen,mswitchMaxSoundSalahAlaAlnapy;
   EditText mEditTextTimerRepeateSalatMohamed;
   TextView tvAlarmAzkarSabah,tvAlarmAzkarMasaa,tvChangeTypeFont,tvChangeColor;
	ImageView tvWhatsApp,tvFacebook,tvAbute_developer;
   Date mDate=new Date();
   static  char AM_PM_S='ص';
	static String[] time_S;
	static  char AM_PM_M='ص';
	static String[] time_M;
	static MediaPlayer button_click=new MediaPlayer();
	static Dialog d;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		mswitchSalatMohamed=findViewById(R.id.settingsSwitch);
		mSwitchAlarmAzkar=findViewById(R.id.settingsSwitchAlarmAzkar);
		mSwitchAlarmShowAzkarInScreen=findViewById(R.id.settingsSwitchShowAzkarInScreen);
		mswitchMaxSoundSalahAlaAlnapy=findViewById(R.id.settingsSwitch_maxSoundSalahAlaAlnapy);
		mEditTextTimerRepeateSalatMohamed=findViewById(R.id.settingsEditTextTimeRepeate);
		tvChangeTypeFont=findViewById(R.id.settingsTextView_change_font);
		tvChangeColor=findViewById(R.id.settingsTextView_change_color);
		tvAlarmAzkarSabah=findViewById(R.id.settingsTextViewAlarmAzkarSabah);
		tvAlarmAzkarMasaa=findViewById(R.id.settingsTextViewAlarmAzkarMasaa);
		tvWhatsApp=findViewById(R.id.settingsTextView_WhatsApp);
		tvFacebook=findViewById(R.id.settingsTextView_facebook);
        tvAbute_developer=findViewById(R.id.settingsTextView_abut_devoloper);
		overridePendingTransition(R.anim.translate,R.anim.translate_out);
		
		
			button_click = MediaPlayer.create(SettingsActivity.this, R.raw.button_click);

		
/////////////////////////////SettingsAlarmShowAzkarInScreen////////////////////////////////////////////////////
		boolean RunAndCloseShowAzkarInScreen=getSharedPreferences("SettingsAlarmShowAzkarInScreen",MODE_PRIVATE).getBoolean("RunAndClose",false);
		mSwitchAlarmShowAzkarInScreen.setChecked(RunAndCloseShowAzkarInScreen);
		mSwitchAlarmShowAzkarInScreen.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton p1, boolean p2) {
					if(p2==true){
						Intent ii=new Intent(SettingsActivity.this,MyService.class);
						getSharedPreferences("SettingsAlarmShowAzkarInScreen",MODE_PRIVATE).edit().putBoolean("RunAndClose",true).apply();
						
						Intent i=new Intent(SettingsActivity.this,Alarm_ShowAzkarInScreen.class);
						PendingIntent p=PendingIntent.getBroadcast(SettingsActivity.this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
						AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
						alarm.cancel(p); 
						sendBroadcast(i);
					
						if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
							startForegroundService(ii);
						}else{
							startService(ii);
						}	

					}else{

						Intent ii=new Intent(SettingsActivity.this,MyService.class);
						//stopService(ii);
						getSharedPreferences("SettingsAlarmShowAzkarInScreen",MODE_PRIVATE).edit().putBoolean("RunAndClose",false).apply();
						
						Intent i=new Intent(SettingsActivity.this,Alarm_ShowAzkarInScreen.class);
						PendingIntent p=PendingIntent.getBroadcast(SettingsActivity.this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
						AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
						alarm.cancel(p); 
						//update Notification
						//Intent ii=new Intent(SettingsActivity.this,MyService.class);
						if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
							startForegroundService(ii);
						}else{
							startService(ii);
						}	
					}
				}
				 
			
		});
		
		tvChangeTypeFont.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
				    d=new Dialog(SettingsActivity.this);
					d.requestWindowFeature(Window.FEATURE_NO_TITLE);
					final View v=getLayoutInflater().inflate(R.layout.dialog_change_type_font,null);
					d.setContentView(v);
					d.getWindow().setWindowAnimations(R.style.DialogAnimation2);
					d.getWindow().setBackgroundDrawable(getDrawable( R.drawable.shape_dialog_add_azkar));
					//d.getWindow().setLayout(500,500);
					TextView mTextView1=d.findViewById(R.id.dialog_change_type_font_TextView1);
					TextView mTextView2=d.findViewById(R.id.dialog_change_type_font_TextView2);
					TextView mTextView3=d.findViewById(R.id.dialog_change_type_font_TextView3);
					mTextView1.setTypeface(Typeface.createFromAsset(getAssets(),"DroidKufi-Bold.ttf"));
					mTextView2.setTypeface(Typeface.createFromAsset(getAssets(),"DroidNaskh-Bold.ttf"));
					mTextView3.setTypeface(Typeface.createFromAsset(getAssets(),"khalid-art-bold.ttf"));
					
					int ChooseFont=getSharedPreferences("SettingsChangeTypeFont",MODE_PRIVATE).getInt("font",1);;
					RadioButton mRadioButton1=d.findViewById(R.id.dialog_change_type_fontRadioButton1);
					RadioButton mRadioButton2=d.findViewById(R.id.dialog_change_type_fontRadioButton2);
					RadioButton mRadioButton3=d.findViewById(R.id.dialog_change_type_fontRadioButton3);
					if(ChooseFont==1){mRadioButton1.setChecked(true);}else if(ChooseFont==2){mRadioButton2.setChecked(true);}else{mRadioButton3.setChecked(true);}
					final  RadioGroup mRadioGroup=d.findViewById(R.id.dialog_change_type_font_RadioGroup);
					mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

							@Override
							public void onCheckedChanged(RadioGroup p1, int p2) {
								switch(p2){
									case R.id.dialog_change_type_fontRadioButton1:
										getSharedPreferences("SettingsChangeTypeFont",MODE_PRIVATE).edit().putInt("font",1).apply();
										break;
									case R.id.dialog_change_type_fontRadioButton2:
										getSharedPreferences("SettingsChangeTypeFont",MODE_PRIVATE).edit().putInt("font",2).apply();
										break;
									case R.id.dialog_change_type_fontRadioButton3:
										getSharedPreferences("SettingsChangeTypeFont",MODE_PRIVATE).edit().putInt("font",3).apply();
										break;
								}
							
						}
					});
					d.show();
					
				}
				
			
		});
		
		tvChangeColor.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					Dialog d=new Dialog(SettingsActivity.this);
					d.requestWindowFeature(Window.FEATURE_NO_TITLE);
					d.setContentView(R.layout.dialog_change_color_font);
					d.getWindow().setBackgroundDrawable(getDrawable( R.drawable.shape_dialog_add_azkar));
					d.getWindow().setWindowAnimations(R.style.DialogAnimation2);
					//d.getWindow().setLayout(500,500);
					
				     
					//Toast.makeText(getApplication(),+"", Toast.LENGTH_SHORT).show();
					
					int ChooseColor=getSharedPreferences("SettingsChangeColorFont",MODE_PRIVATE).getInt("color",1);;
					RadioButton mRadioButton1=d.findViewById(R.id.dialog_change_type_fontRadioButton1);
					RadioButton mRadioButton2=d.findViewById(R.id.dialog_change_type_fontRadioButton2);
					RadioButton mRadioButton3=d.findViewById(R.id.dialog_change_type_fontRadioButton3);
					if(ChooseColor==1){mRadioButton1.setChecked(true);}else if(ChooseColor==2){mRadioButton2.setChecked(true);}else{mRadioButton3.setChecked(true);}
					final  RadioGroup mRadioGroup=d.findViewById(R.id.dialog_change_type_font_RadioGroup);
					mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

							@Override
							public void onCheckedChanged(RadioGroup p1, int p2) {
								switch(p2){
									case R.id.dialog_change_type_fontRadioButton1:
										getSharedPreferences("SettingsChangeColorFont",MODE_PRIVATE).edit().putInt("color",1).apply();
										break;
									case R.id.dialog_change_type_fontRadioButton2:
										getSharedPreferences("SettingsChangeColorFont",MODE_PRIVATE).edit().putInt("color",2).apply();
										break;
									case R.id.dialog_change_type_fontRadioButton3:
										getSharedPreferences("SettingsChangeColorFont",MODE_PRIVATE).edit().putInt("color",3).apply();
										break;
								}
 
							}
						});
					d.show();
				}


			});
		
/////////////////////////////EndSettingsAlarmShowAzkarInScreen////////////////////////////////////////////////////
		//Toast.makeText(getApplicationContext(), Integer.MAX_VALUE+"", Toast.LENGTH_SHORT).show();
/////////////////////////////SettingsAlarmSalatMohamed////////////////////////////////////////////////////
		try{
		
		boolean RunAndCloseSalatMohamed=getSharedPreferences("SettingsAlarmSalatMohamed",MODE_PRIVATE).getBoolean("RunAndClose",false);
	    boolean MaxSoundSalahAlaAlnapy=getSharedPreferences("SettingsMaxSoundSalahAlaAlnapy",MODE_PRIVATE).getBoolean("MaxSoundSalahAlaAlnapy",false);
		int TimeRepeate=getSharedPreferences("SettingsAlarmSalatMohamed",MODE_PRIVATE).getInt("TimeRepeate",0)/60/1000;
		if(RunAndCloseSalatMohamed==true){mswitchSalatMohamed.setChecked(true);}else{mswitchSalatMohamed.setChecked(false);}
		mswitchMaxSoundSalahAlaAlnapy.setChecked(MaxSoundSalahAlaAlnapy);
		mEditTextTimerRepeateSalatMohamed.setText(TimeRepeate+"");
		
		
		mswitchSalatMohamed.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton p1, boolean p2) {
					
					if(p2==true){
						getSharedPreferences("SettingsAlarmSalatMohamed",MODE_PRIVATE).edit().putBoolean("RunAndClose",p2).apply();
						Intent i=new Intent(SettingsActivity.this,Alarm_SalatMohamed.class);
						PendingIntent p=PendingIntent.getBroadcast(SettingsActivity.this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
						AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
						alarm.cancel(p); 
						sendBroadcast(i);
						
					}else{
						getSharedPreferences("SettingsAlarmSalatMohamed",MODE_PRIVATE).edit().putBoolean("RunAndClose",p2).apply();
						Intent i=new Intent(SettingsActivity.this,Alarm_SalatMohamed.class);
						PendingIntent p=PendingIntent.getBroadcast(SettingsActivity.this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
						AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
						alarm.cancel(p); 
						sendBroadcast(i);
						
					}
										
				}
				
		});
		
			mswitchMaxSoundSalahAlaAlnapy.setOnCheckedChangeListener(new OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton p1, boolean p2) {

						if(p2==true){
							getSharedPreferences("SettingsMaxSoundSalahAlaAlnapy",MODE_PRIVATE).edit().putBoolean("MaxSoundSalahAlaAlnapy",p2).apply();
							Intent i=new Intent(SettingsActivity.this,Alarm_SalatMohamed.class);
							PendingIntent p=PendingIntent.getBroadcast(SettingsActivity.this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
							AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
							alarm.cancel(p); 
							sendBroadcast(i);

						}else{
							getSharedPreferences("SettingsMaxSoundSalahAlaAlnapy",MODE_PRIVATE).edit().putBoolean("MaxSoundSalahAlaAlnapy",p2).apply();
							Intent i=new Intent(SettingsActivity.this,Alarm_SalatMohamed.class);
							PendingIntent p=PendingIntent.getBroadcast(SettingsActivity.this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
							AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
							alarm.cancel(p); 

						}

					}

				});
				
		mEditTextTimerRepeateSalatMohamed.addTextChangedListener(new TextWatcher(){

				@Override
				public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {
				}

				@Override
				public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
					String val=mEditTextTimerRepeateSalatMohamed.getText().toString();
					if(mEditTextTimerRepeateSalatMohamed.getText().toString().equals("")){
						//Toast.makeText(getApplication(),"1", Toast.LENGTH_SHORT).show();
					}else if(Integer.parseInt(mEditTextTimerRepeateSalatMohamed.getText().toString())>1000){
						getSharedPreferences("SettingsAlarmSalatMohamed",MODE_PRIVATE).edit().putInt("TimeRepeate",1*60*1000).apply();
						mEditTextTimerRepeateSalatMohamed.setText("1");
						//Toast.makeText(getApplication(),"لا يمكن إدخال رقم اكبر من ذلك", Toast.LENGTH_SHORT).show();
					}else{
						//Toast.makeText(getApplication(),"2", Toast.LENGTH_SHORT).show();
						//try{
						getSharedPreferences("SettingsAlarmSalatMohamed",MODE_PRIVATE).edit().putInt("TimeRepeate",Integer.parseInt(val)*60*1000).apply();
					   }
						//}catch(Exception e){}
						
				}

				@Override
				public void afterTextChanged(Editable p1) {
				}
				
			
		});
		
		
		}catch(Exception e){Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();}
//////////////////////////////EndSettingsAlarmSalatMohamed/////////////////////////////////////////////////
		
//////////////////////////////SettingsAlarmAzkar/////////////////////////////////////////////////
		boolean RunAndCloseAlarmAzkar=getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).getBoolean("RunAndCloseAlarmAzkar",false);
		mSwitchAlarmAzkar.setChecked(RunAndCloseAlarmAzkar);
		mSwitchAlarmAzkar.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton p1, boolean p2) {
					
					if(p2==true){
					
					//-sendBroadcast(i);
						getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).edit()
							.putBoolean("RunAndCloseAlarmAzkar",p2).apply();
						
						Intent i=new Intent(SettingsActivity.this,MyService.class);
						if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
							startForegroundService(i);
						}else{
							startService(i);
						}	
						 
						
					}else{
						getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).edit()
							.putBoolean("RunAndCloseAlarmAzkar",p2).apply();
						
						Intent i=new Intent(SettingsActivity.this,Alarm_AzkarSabah.class);
						PendingIntent p=PendingIntent.getBroadcast(SettingsActivity.this,0,i,0);
						AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
						alarm.cancel(p); 
						p.cancel();
						
						Intent i2=new Intent(SettingsActivity.this,Alarm_AzkarMasaa.class);
						PendingIntent pi2=PendingIntent.getBroadcast(SettingsActivity.this,0,i2,0);
						AlarmManager alarm2=(AlarmManager)getSystemService(ALARM_SERVICE);
						alarm2.cancel(pi2); 
						pi2.cancel();
						
					}
					
					
					
				} 
				
		  
	  });
		
		
		
		//try{
		String alarmAzkarsabah=	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).getString("AlarmAzkarSabah","6:0");
		String alarmAzkarmasaa=	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).getString("AlarmAzkarMasaa","17:0");
		 time_M=alarmAzkarmasaa.split(":");
		time_S=alarmAzkarsabah.split(":");
		Change_Time_24Hours_To_12Hours_M(time_M[0]);
		Change_Time_24Hours_To_12Hours_S(time_S[0]);
		 Change_Minute_To_Tow_Numper_S(time_S[1]);
		Change_Minute_To_Tow_Numper_M(time_M[1]); 
		 
		tvAlarmAzkarSabah.setText(time_S[0]+":"+time_S[1]+" "+AM_PM_S);
		tvAlarmAzkarMasaa.setText(time_M[0]+":"+time_M[1]+" "+AM_PM_M);
			//Toast.makeText(getApplicationContext(),time_M[0]+":"+time_M[1]+" "+AM_PM_M, Toast.LENGTH_SHORT).show();
		//}catch(Exception e){Toast.makeText(getApplicationContext(),e.getMessage() +"", Toast.LENGTH_LONG).show();}
		tvAlarmAzkarSabah.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					TimePickerDialog d=new TimePickerDialog(SettingsActivity.this, new TimePickerDialog.OnTimeSetListener(){

							@Override
							public void onTimeSet(TimePicker p1, int p2, int p3) {
								//tvAlarmAzkarSabah.setText(p2+" : "+p3);
								getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).edit().putString("AlarmAzkarSabah",p2+":"+p3)
								.putInt("HoursSabah",p2)
								.putInt("MinuteSabah",p3)
								.apply();
								
								String alarmAzkarsabah=	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).getString("AlarmAzkarSabah","6:0");
								time_S=alarmAzkarsabah.split(":");
								Change_Time_24Hours_To_12Hours_S(time_S[0]);
								Change_Minute_To_Tow_Numper_S(time_S[1]);
								tvAlarmAzkarSabah.setText(time_S[0]+":"+time_S[1]+" "+AM_PM_S);
								
								/*Intent i=new Intent(SettingsActivity.this,Alarm_AzkarSabah.class);
								PendingIntent p=PendingIntent.getBroadcast(SettingsActivity.this,0,i,0);
								AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
								alarm.cancel(p); 
								p.cancel();*/
							Intent ii=new Intent(SettingsActivity.this,MyService.class);
								//Intent i=new Intent(SettingsActivity.this,MyService.class);
								if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
									startForegroundService(ii);
								}else{
									startService(ii);
								}	
								
								
							}
							
						
					},new Date().getHours(),new Date().getMinutes(),false);
				
					d.show();
					
				}
				
			
		});
		////
		tvAlarmAzkarMasaa.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					TimePickerDialog d=new TimePickerDialog(SettingsActivity.this, new TimePickerDialog.OnTimeSetListener(){

							@Override
							public void onTimeSet(TimePicker p1, int p2, int p3) {
								//tvAlarmAzkarMasaa.setText(p2+" : "+p3);
									
								getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).edit().putString("AlarmAzkarMasaa",p2+":"+p3)
								.putInt("HoursMasaa",p2)
								.putInt("MinuteMasaa",p3) 
								.apply();
								
								String alarmAzkarmasaa=	getSharedPreferences("SettingsAlarmAzkarSabahAndMasaa",MODE_PRIVATE).getString("AlarmAzkarMasaa","17:0");
								time_M=alarmAzkarmasaa.split(":");
								Change_Time_24Hours_To_12Hours_M(time_M[0]);
								Change_Minute_To_Tow_Numper_M(time_M[1]);
								tvAlarmAzkarMasaa.setText(time_M[0]+":"+time_M[1]+" "+AM_PM_M);
								
								/*Intent i=new Intent(SettingsActivity.this,Alarm_AzkarMasaa.class);
								PendingIntent p=PendingIntent.getBroadcast(SettingsActivity.this,0,i,0);
								AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
								alarm.cancel(p); 
								p.cancel();*/
								//Intent ii=new Intent(SettingsActivity.this,MyService.class);
								Intent i=new Intent(SettingsActivity.this,MyService.class);
								if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
									startForegroundService(i);
								}else{
									startService(i);
								}	
								
							}


						},new Date().getHours(),new Date().getMinutes(),false);

					d.show();

				}


			});
//////////////////////////////EndSettingsAlarmAzkar/////////////////////////////////////////////////
			
//////////////////////////////SettingsMyPageOnSocial/////////////////////////////////////////////////
		tvWhatsApp.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					button_click.start();
					whatsapp();
				}
				
				
			});
			
			
			
		tvFacebook.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					button_click.start();
					getOpenFacebookIntent(SettingsActivity.this);
				}


			});
			
			
		tvAbute_developer.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					button_click.start();
					LayoutInflater lay=getLayoutInflater();
					View view=lay.inflate(R.layout.dialoge_about_developer,null);
					Dialog d=new Dialog(SettingsActivity.this);
					d.requestWindowFeature(Window.FEATURE_NO_TITLE|Window.FEATURE_NO_TITLE);
					d.setContentView(view);
					d.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
					ImageView image=d.findViewById(R.id.dialoge_about_developer_facebook);
					image.setOnClickListener(new OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								button_click.start();
								getOpenFacebookIntent(getApplicationContext());
							}


						});

					d.show();
				}


			});
//////////////////////////////EndSettingsMyPageOnSocial/////////////////////////////////////////////////
	
			
			
			
	}//end on create
    
	
	public  void getOpenFacebookIntent(Context context) {

		try {
			context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
			//Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/<100010108337898>"));
			Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100010108337898"));
			startActivity(i);
		} catch (Exception e) {
			Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100010108337898"));
			startActivity(i);
		}
	}// get open facebock
	
	
	public void whatsapp(){
		//button_click.start();
		String url = "https://api.whatsapp.com/send?phone="+"+201129148609";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity( i);
	}
	
/////////////////// Format Time for Azkar Sabah And Azkar Masaa ////////
	
	public void Change_Minute_To_Tow_Numper_S(String Minute){
		char[] minute_s= Minute.toCharArray();
		if(minute_s.length==1){
			String num="0"+Minute;
			time_S[1]=num;
		}
	}//End Change_Minute_To_Tow_Numper_S
	
	public void Change_Minute_To_Tow_Numper_M(String Minute){
		char[] minute_m= Minute.toCharArray();
		if(minute_m.length==1){
			String num="0"+Minute;
			time_M[1]=num;
		}
	}//End Change_Minute_To_Tow_Numper_M
	
	public void Change_Time_24Hours_To_12Hours_M(String Hours){
		if(Hours.equals("12")){
			AM_PM_M='م';
		} else if(Hours.equals("13")){
			time_M[0]="1";
			AM_PM_M='م';
		}else if(Hours.equals("14")){
			time_M[0]="2";
			AM_PM_M='م';
		}else if(Hours.equals("15")){
			time_M[0]="3";
			AM_PM_M='م';
		}else if(Hours.equals("16")){
			time_M[0]="4";
			AM_PM_M='م';
		}else if(Hours.equals("17")){
			time_M[0]="5";
			AM_PM_M='م';
		}else if(Hours.equals("18")){
			time_M[0]="6";
			AM_PM_M='م';
		}else if(Hours.equals("19")){
			time_M[0]="7";
			AM_PM_M='م';
		}else if(Hours.equals("20")){
			time_M[0]="8";
			AM_PM_M='م';
		}else if(Hours.equals("21")){
			time_M[0]="9";
			AM_PM_M='م';
		}else if(Hours.equals("22")){
			time_M[0]="10";
			AM_PM_M='م';
		}else if(Hours.equals("23")){
			time_M[0]="11";
			AM_PM_M='م';
		}else if(Hours.equals("0")){
			time_M[0]="12";
			AM_PM_M='ص';
		}else{
			AM_PM_M='ص';
		}
		
	}//end Change_Time_24Hours_To_12Hours_M
	
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

/////////////////// End Format Time for Azkar Sabah And Azkar Masaa ////////
	
	@Override
	public void onBackPressed() {
		//overridePendingTransition(R.anim.translate2,R.anim.translate_out2);
		super.onBackPressed();
		overridePendingTransition(R.anim.translate2,R.anim.translate_out2);
	}
	
}
