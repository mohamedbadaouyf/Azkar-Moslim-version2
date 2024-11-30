package com.mohamed_badaouy.azkar_muslim1;
import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class Tasbeh extends Activity {
 
	TextView tvCound,tvClearCound,tvClickCound,tvTotalCound,tvDayCound;
	int iCoundTotal=0;
	int iCoundDay=0;
   static MediaPlayer mMediaPlayer=new MediaPlayer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(getWindow().FEATURE_NO_TITLE);
		setContentView(R.layout.tasbeh);
		tvCound=findViewById(R.id.tasbehTextViewCound);
		tvClearCound=findViewById(R.id.tasbehTextViewClearCound);
		tvClickCound=findViewById(R.id.tasbehTextViewClickCound);
		tvTotalCound=findViewById(R.id.tasbehTextViewTotalCound);
		tvDayCound=findViewById(R.id.tasbehTextViewDayCound);
		overridePendingTransition(R.anim.translate,R.anim.translate_out);
		//	InputStream in= getAssets().open("DroidKufi-Bold.ttf");
		
		 final SimpleDateFormat DateNowformat =new SimpleDateFormat("dd/MM/yyyy");
		 String DateNow=DateNowformat.format(new Date());
		 String DateSaved =getSharedPreferences("DateSaved",MODE_PRIVATE).getString("DateSaved",""); 
		 if(!DateNow.equals(DateSaved)){
			 iCoundDay=0;
			 tvDayCound.setText("تسبيحات اليوم   "+iCoundDay);
		 }else{
			 iCoundDay=getSharedPreferences("DateSaved",MODE_PRIVATE).getInt("count",0); 
			 tvDayCound.setText("تسبيحات اليوم   "+iCoundDay);
		 }
		 	
		// Log.d("date",s.format(new Date()));
		
		//Toast.makeText(getApplicationContext(),s.format(new Date()) +"", Toast.LENGTH_SHORT).show();
		//Typeface t=new Typeface();
		
	//	tvTotalCound.setTypeface(Typeface.createFromAsset(,""));
		
		int TotalCound=getSharedPreferences("saveTotalCoundTasbeeh",MODE_PRIVATE)
			.getInt("TotalCound",0);

		tvTotalCound.setText("مجموع التسبيحات   "+TotalCound);
		
		tvClickCound.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					mMediaPlayer.stop();
					mMediaPlayer=MediaPlayer.create(Tasbeh.this,R.raw.tasbeeh_click);
					mMediaPlayer.start();
				    iCoundTotal++;
					tvCound.setText(iCoundTotal+"");

					int TotalCound=getSharedPreferences("saveTotalCoundTasbeeh",MODE_PRIVATE)
						.getInt("TotalCound",0); 
                        TotalCound+=1;
						iCoundDay++;
					   getSharedPreferences("saveTotalCoundTasbeeh",MODE_PRIVATE).edit().putInt("TotalCound",TotalCound).apply();
					getSharedPreferences("DateSaved",MODE_PRIVATE).edit().putString("DateSaved",DateNowformat.format(new Date())).putInt("count",iCoundDay).apply();
					
						
						
					tvTotalCound.setText("مجموع التسبيحات   "+TotalCound);
					tvDayCound.setText("تسبيحات اليوم   "+iCoundDay);
				}
				
			
		});
		tvClearCound.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					iCoundTotal=0;
					tvCound.setText(iCoundTotal+"");
				}


			});
		
		
		
		
	}//end on create
    
    @Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.translate2,R.anim.translate_out2);
	}
    
}
