package com.mohamed_badaouy.azkar_muslim1;
import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
public class Main_Activity extends Activity
{String text;
	ListView listview;
	ArrayList<String> itms=new ArrayList();
Data_class d=new Data_class();
Fragment mFragment;
ImageButton btnMain,btnShowAzkar,btnAzkar,btnSadaqa;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//overridePendingTransition(R.anim.translate2,R.anim.translate_out2);
		setContentView(R.layout.main_activity);
		btnMain=findViewById(R.id.main_activityImageButtonMain);
		btnShowAzkar=findViewById(R.id.main_activityImageButtonshowAzkar);
		btnAzkar=findViewById(R.id.main_activityImageButtonAzkar);
		btnSadaqa=findViewById(R.id.main_activityImageButtonSadaqa);
		//listview=findViewById(R.id.main_activity_ListView);
		//overridePendingTransition(R.anim.translate2,R.anim.translate_out2);
		checkFragmentBackground();
		btnMain.setBackgroundDrawable(getDrawable(R.drawable.ic_home_black));
		
		//check from open application first time
		if(getSharedPreferences("open_application_first_time",MODE_PRIVATE).getInt("key",0)==0){
			getSharedPreferences("SettingsAlarmShowAzkarInScreen",MODE_PRIVATE).edit().putBoolean("RunAndClose",true).apply();
			getSharedPreferences("open_application_first_time",MODE_PRIVATE).edit().putInt("key",1).apply();
		}
		
		
		
		Intent intent2 = new Intent(Main_Activity.this, MyService.class);
		//stopService(intent2);
		//if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
			//startForegroundService(intent2);
		//}else{
			startService(intent2);
		//}
		
		
		//Intent intent3 = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
		//intent3.setData(Uri.parse("package:" + getPackageName()));
		//startActivity(intent3);
		/*Intent runAllAlarms=new Intent(this,Alarm_SetAllAlarms.class);
		sendBroadcast(runAllAlarms); */
		JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

		ComponentName componentName = new ComponentName(this, JobServiceClass.class);
		JobInfo jobInfo = new JobInfo.Builder(123, componentName)
			//.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // تحديد نوع الشبكة المطلوبة
			.setPersisted(true) // لاستمرارية المهمة حتى بعد إعادة تشغيل الجهاز
			//.setRequiresCharging(true)
		    //.setRequiresDeviceIdle(true)
			.build();

		jobScheduler.schedule(jobInfo);
		
		//
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
              intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
				//intent.setData(Uri.parse("package:" + packageName));
			 startActivity(intent);
            }/*else{
				intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
				//startActivity(intent);
			}*/
			
			
			//startActivityForResult(new Intent(android.provider.Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS), 0);
        }
		
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent,1);
        }
		
		requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},1);
		mFragment=new FragmentMain();
        FragmentManager manger=getFragmentManager();
        FragmentTransaction ft=manger.beginTransaction();
        ft.replace(R.id.main_activityFrameLayout,mFragment);
        ft.commit();
		
	} 
    //click fragment
	public void fragment_Main(View v){
        mFragment=new FragmentMain();
        FragmentManager manger=getFragmentManager();
        FragmentTransaction ft=manger.beginTransaction();
        ft.replace(R.id.main_activityFrameLayout,mFragment);
        ft.commit();
		checkFragmentBackground();
		v.setBackgroundDrawable(getDrawable(R.drawable.ic_home_black));
    }
	public void fragment_ShowAzkar(View v){
       // Toast.makeText(getApplication(), "??", Toast.LENGTH_SHORT).show();
       mFragment=new FragmentShowAzkar();
        FragmentManager manger=getFragmentManager();
        FragmentTransaction ft=manger.beginTransaction();
        ft.replace(R.id.main_activityFrameLayout,mFragment);
        ft.commit();
		checkFragmentBackground();
		v.setBackgroundDrawable(getDrawable(R.drawable.ic_islam_plack));
    }
    public void fragment_Azkar(View v){
        mFragment=new FragmentAzkar();
        FragmentManager manger=getFragmentManager();
        FragmentTransaction ft=manger.beginTransaction();
        ft.replace(R.id.main_activityFrameLayout,mFragment);
        ft.commit();
		checkFragmentBackground();
		v.setBackgroundDrawable(getDrawable(R.drawable.ic_quran_black));
    }
    public void fragment_Sadaqa(View v){
        mFragment=new FragmentSadaqa();
        FragmentManager manger=getFragmentManager();
        FragmentTransaction ft=manger.beginTransaction();
        ft.replace(R.id.main_activityFrameLayout,mFragment);
        ft.commit();
		checkFragmentBackground();
		v.setBackgroundDrawable(getDrawable(R.drawable.ic_heart_black));
    }

    @Override
    protected void onResume() {
        super.onResume();
       overridePendingTransition(R.anim.translate2,R.anim.translate_out2);
    }

	
	
    public void checkFragmentBackground(){
		btnMain.setBackgroundDrawable(getDrawable(R.drawable.ic_home));
		btnSadaqa.setBackgroundDrawable(getDrawable(R.drawable.ic_heart));
		btnShowAzkar.setBackgroundDrawable(getDrawable(R.drawable.ic_islam));
		btnAzkar.setBackgroundDrawable(getDrawable(R.drawable.ic_quran));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(Settings.canDrawOverlays(this)){
			//Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
		}else{
			//Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_LONG).show();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
	
    
}
