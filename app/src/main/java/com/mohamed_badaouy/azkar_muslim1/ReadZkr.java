package com.mohamed_badaouy.azkar_muslim1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mohamed_badaouy.azkar_muslim1.List_item;
import com.mohamed_badaouy.azkar_muslim1.SQLiteDatabase.DatabaseAllAzkar;
import com.mohamed_badaouy.azkar_muslim1.SQLiteDatabase.DatabaseNewAzkarFromUser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.os.Handler;
import android.view.MotionEvent;

public class ReadZkr extends Activity {
	ListView mListView;
	TextView tvActionBar;
	
    ArrayList<String> Azkar=new ArrayList<>();
	ArrayList<List_item> Azkar2=new ArrayList<>();
	ArrayList<Integer> RepeateZkr=new ArrayList<>();
	ArrayList<String> Test=new ArrayList<>();
	static int currentAzkar;
    JSONArray mJSONArray;
	Animation mAnimation;
	static int kayForDeleteZkr =0;
	static int kayForAddedZkr =0;
	SharedPreferences EditAzkarSharedPreferences;
	SharedPreferences AddAzkarSharedPreferences;
	SharedPreferences DeletAzkarSharedPreferences;

	DatabaseAllAzkar mDatabaseAllAzkar;
	DatabaseNewAzkarFromUser mDatabaseNewAzkarFromUser;
	static int posotionList=0,MaxAndMinFont=20;
	LinearLayout mLinearLayout,mLinearLayout_settingsZkr,mLinearLayout_ListView;
	static String category="";
	
	ImageButton btnMaxFont,btnMinFont,btnDarkMode,btnChangeTheme;
	//TextView tvChangeTheme;
	static int NumperTheme=1;
	boolean ChangeToDarkMode=false;
	
	//View v;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(getWindow().FEATURE_NO_TITLE);
		setContentView(R.layout.read_zkr);
		mLinearLayout=findViewById(R.id.read_zkrLinearLayout);
		mLinearLayout_settingsZkr=findViewById(R.id.read_zkrLinearLayout_settingsZkr);
		mLinearLayout_ListView=findViewById(R.id.read_zkrLinearLayout_ListView);
		mListView=findViewById(R.id.read_zkrListView);
		btnMaxFont=findViewById(R.id.read_zkrImageButton_max_font);
		btnMinFont=findViewById(R.id.read_zkrImageButton_min_font);
		btnChangeTheme=findViewById(R.id.read_zkrImageButton_changeTheme);
		btnDarkMode=findViewById(R.id.read_zkrImageButton_DarkMode);
		mListView.setDividerHeight(0);
		mListView.setScrollBarSize(0);
		
		
		mAnimation=AnimationUtils.loadAnimation(this,R.anim.translate_out);
		tvActionBar=findViewById(R.id.read_zkrActionBar);
	  //  ActionBar=findViewById(R.id.read_zkrRelativeLayout_ActionBar);
		Typeface mTypeface=Typeface.createFromAsset(getAssets(),"khalid-art-bold.ttf");
		tvActionBar.setTypeface(mTypeface);
		
		mDatabaseAllAzkar=new DatabaseAllAzkar(this,currentAzkar);
		try
        { 

            InputStream in=getAssets().open("azkar3.json");
			byte[] buffer=new byte[in.available()];
			in.read(buffer);
			in.close();
            String json;
            json=new String(buffer,StandardCharsets.UTF_8);
			if(currentAzkar<135){
            try {
				mJSONArray=new JSONArray(json);

				// for(int i=0;i<mJSONArray.length();i++){
				
				JSONObject mJSONObject=mJSONArray.getJSONObject(currentAzkar);
				tvActionBar.setText(mJSONObject.getString("category"));
              //  t.setText(category);
			    
				JSONArray mJSONArray2=new JSONArray(mJSONObject.getString("array"));

				Azkar2 = mDatabaseAllAzkar.read_data();
				//Azkar2.add(new List_item("9999",1));
				
				///////
				if(Azkar2.size()<1){

					for (int i = 0; i < mJSONArray2.length(); i++) {
						/*if(i==1&&currentAzkar==0){
							Azkar2.add(new List_item("﴿آمَنَ الرَّسُولُ بِمَا أُنزِلَ إِلَيْهِ مِن رَّبِّهِ وَالْمُؤْمِنُونَ كُلٌّ آمَنَ بِاللَّهِ وَمَلآئِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لاَ نُفَرِّقُ بَيْنَ أَحَدٍ مِّن رُّسُلِهِ وَقَالُواْ سَمِعْنَا وَأَطَعْنَا غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ* لاَ يُكَلِّفُ اللَّهُ نَفْساً إِلاَّ وُسْعَهَا لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ رَبَّنَا لاَ تُؤَاخِذْنَا إِن نَّسِينَا أَوْ أَخْطَأْنَا رَبَّنَا وَلاَ تَحْمِلْ عَلَيْنَا إِصْراً كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِن قَبْلِنَا رَبَّنَا وَلاَ تُحَمِّلْنَا مَا لاَ طَاقَةَ لَنَا بِهِ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَآ أَنتَ مَوْلاَنَا فَانصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ﴾.",1));
							mDatabaseAllAzkar.insert_data("﴿آمَنَ الرَّسُولُ بِمَا أُنزِلَ إِلَيْهِ مِن رَّبِّهِ وَالْمُؤْمِنُونَ كُلٌّ آمَنَ بِاللَّهِ وَمَلآئِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لاَ نُفَرِّقُ بَيْنَ أَحَدٍ مِّن رُّسُلِهِ وَقَالُواْ سَمِعْنَا وَأَطَعْنَا غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ* لاَ يُكَلِّفُ اللَّهُ نَفْساً إِلاَّ وُسْعَهَا لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ رَبَّنَا لاَ تُؤَاخِذْنَا إِن نَّسِينَا أَوْ أَخْطَأْنَا رَبَّنَا وَلاَ تَحْمِلْ عَلَيْنَا إِصْراً كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِن قَبْلِنَا رَبَّنَا وَلاَ تُحَمِّلْنَا مَا لاَ طَاقَةَ لَنَا بِهِ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَآ أَنتَ مَوْلاَنَا فَانصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ﴾.",1);
						}*/
				     
						JSONObject mJSONObject22=mJSONArray2.getJSONObject(i);
						//if(mJSONObject22.getString("category").equals(category)&&mJSONObject22.getString("count").equals("")){
							//Azkar2.add(new List_item(mJSONObject22.getString("zekr"),Integer.parseInt(mJSONObject22.getString("count")+1)));
						//}else if(mJSONObject22.getString("category").equals(category)){
							Azkar2.add(new List_item(mJSONObject22.getString("text"),Integer.parseInt(mJSONObject22.getString("count"))));
						//}
						//Azkar.add(mJSONObject22.getString("zekr"));
						//RepeateZkr.add(Integer.parseInt(mJSONObject2.getString("count")));
						//Azkar2.add(new List_item(mJSONObject22.getString("zekr"),Integer.parseInt(mJSONObject22.getString("count"))));
						mDatabaseAllAzkar.insert_data(mJSONObject22.getString("text"),Integer.parseInt(mJSONObject22.getString("count")));
						
					}

				}//end if
				//////***




            } catch (JSONException e) {Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();}

			}// end if <-- if(currentAzkar<134)

        } catch (IOException e)
        {}
          
		try{
			
			MyAdapter m=new MyAdapter(Azkar2);
			mListView.setAdapter(m);
			
			//////// for the azkar arbaoon alnaoaoia الاربعون النووية //////
			
			
			if( currentAzkar==135){
				tvActionBar.setText("ألاربعون النووية");
				
					InputStream in=getAssets().open("hadeth.json");
					byte b[]=new byte[in.available()];
					in.read(b);in.close();
					 String json=new String(b,StandardCharsets.UTF_8);
					JSONArray JSONArray2=new JSONArray(json);
				    for (int i = 0; i < JSONArray2.length(); i++) {
                         JSONObject mJSONObject=JSONArray2.getJSONObject(i);
						Azkar2.add(new List_item(mJSONObject.getString("hadeth"),1));
                     }
					
					MyAdapter mm=new MyAdapter(Azkar2);
					mListView.setAdapter(mm);

			}
			/*if(currentAzkar>25){
				
				t.setText(getIntent().getStringExtra("TitleAzkar"));
				View v=getLayoutInflater().inflate(R.layout.raw_itm_button_add_zkr,null);
				TextView mTextView=v.findViewById(R.id.read_zkrButton);
				mDatabaseNewAzkarFromUser=new DatabaseNewAzkarFromUser(this,getIntent().getStringExtra("TitleAzkar"));
				Azkar2 = mDatabaseNewAzkarFromUser.read_data();
				if(Azkar2.size()<1){mLinearLayout.addView(v);}
				MyAdapter mm=new MyAdapter(Azkar2);
				mListView.setAdapter(mm);
				mTextView.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(final View p11) {
							
							final Dialog mDialog1=new Dialog(ReadZkr.this);
							mDialog1.requestWindowFeature(mDialog1.getWindow().FEATURE_NO_TITLE);
							mDialog1.getWindow().setBackgroundDrawable(getDrawable( R.drawable.shape_dialog_add_azkar));
							mDialog1.setContentView(R.layout.dialog_add_azkar_muslim);

							Button btnSave1=mDialog1.findViewById(R.id.dialog_add_azkar_muslimBtnSave);
							Button btnBack1=mDialog1.findViewById(R.id.dialog_add_azkar_muslimBtnBack);
							final EditText textZkr1=mDialog1.findViewById(R.id.dialog_add_azkar_muslimEditText);
							final EditText textRepeate1=mDialog1.findViewById(R.id.dialog_add_azkar_muslimEditTextRepeate);

							btnSave1.setOnClickListener(new OnClickListener(){

									@Override
									public void onClick(View p11) {
										if(textZkr1.getText().toString().trim().equals("")){
											textZkr1.setError("من فضلك ادخل ذكر");
										}else if(textRepeate1.getText().toString().trim().equals("")){
											textRepeate1.setError("من فضلك ادخل التكرار");
										}else{

											mDatabaseNewAzkarFromUser.insert_data(textZkr1.getText().toString(),Integer.parseInt(textRepeate1.getText().toString()));
											Azkar2.add(new List_item(textZkr1.getText().toString(),Integer.parseInt(textRepeate1.getText().toString())));
											MyAdapter m=new MyAdapter(Azkar2);
											mListView.setAdapter(m);
											mListView.setSelection(Azkar2.size());
											mDialog1.dismiss();
										}//end if
									}
								});
							//////
							btnBack1.setOnClickListener(new OnClickListener(){

									@Override
									public void onClick(View p11) {
										mDialog1.dismiss();
									}


								});
							mDialog1.show();
							
						}
						
					
				});
				
				//////// end for the azkar added from user //////
				//Toast.makeText(this, currentAzkar+"", Toast.LENGTH_LONG).show();
			}//end if 
			*/
         //   mListView.setSelection(posotionList);
			mListView.setOnScrollListener(new AbsListView.OnScrollListener(){

					@Override
					public void onScrollStateChanged(AbsListView p1, int p2) {
						
					}

					@Override
					public void onScroll(AbsListView p1, int p2, int p3, int p4) {
						
						posotionList= mListView.getFirstVisiblePosition();
						if(p2==0){

							tvActionBar.setHeight(150);
							tvActionBar.setTextSize(21);

						}else{

							tvActionBar.setTextSize(17);
							tvActionBar.setHeight(20);

						}
					}

				});//end onScrollList
				
			mListView.setOnTouchListener(new View.OnTouchListener(){

					@Override
					public boolean onTouch(View p1, MotionEvent p2) {
						
						
						return false;
					}
					
					
				});
			
			//
			btnMinFont.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1) {
									
						if(MaxAndMinFont<=14){
							Toast.makeText(getApplication(), "لايمكن تصغير الخط اكثر من ذلك", Toast.LENGTH_LONG).show();
						}else{
							MaxAndMinFont--;
							MyAdapter mm=new MyAdapter(Azkar2);
							mListView.setAdapter(mm);
							mListView.setSelection(posotionList);
							getSharedPreferences("MaxAndMinFont",MODE_PRIVATE).edit().putInt("MaxAndMinFont",MaxAndMinFont).apply();}
							
				}
					
			});
			//
			btnMaxFont.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1) {
						
						if(MaxAndMinFont>=40){
							Toast.makeText(getApplication(), "لا يمكن تكبير الخط اكثر من ذلك", Toast.LENGTH_LONG).show();
						}else{
							MaxAndMinFont++;
							MyAdapter mm=new MyAdapter(Azkar2);
							mListView.setAdapter(mm);
							mListView.setSelection(posotionList);
							getSharedPreferences("MaxAndMinFont",MODE_PRIVATE).edit().putInt("MaxAndMinFont",MaxAndMinFont).apply();}
						
					}

				});
				//
			btnDarkMode.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {
						//View v=getLayoutInflater().inflate(R.layout.raw_itm_read_zkr,null); 
						//final TextView tvZkr=v.findViewById(R.id.raw_itm_read_zkrTextView);
						if(ChangeToDarkMode==false){
							NumperTheme=0;
							ChangeToDarkMode=true;
							MyAdapter mm=new MyAdapter(Azkar2);
							mListView.setAdapter(mm);
							mListView.setSelection(posotionList);
							getSharedPreferences("ChangeToDarkMode",MODE_PRIVATE).edit().putBoolean("ChangeToDarkMode",ChangeToDarkMode).apply();
						}else{
							NumperTheme=1;
							ChangeToDarkMode=false;
							MyAdapter mm=new MyAdapter(Azkar2);
							mListView.setAdapter(mm);
							mListView.setSelection(posotionList);
							getSharedPreferences("ChangeToDarkMode",MODE_PRIVATE).edit().putBoolean("ChangeToDarkMode",ChangeToDarkMode).apply();
							//tvZkr.setBackgroundDrawable(getDrawable(R.drawable.shape_read_azkar));
						}//end if

					}
				});
				//
				
				
			btnChangeTheme.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1) {
						if(NumperTheme<6){
							NumperTheme++;
							MyAdapter mm=new MyAdapter(Azkar2);
							mListView.setAdapter(mm);
							mListView.setSelection(posotionList);
						}else{
							NumperTheme=1
							;MyAdapter mm=new MyAdapter(Azkar2);
							mListView.setAdapter(mm);
							mListView.setSelection(posotionList);}
					}
					
					
				});
			
        }catch(Exception e){Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();}
		
		
		
		
	}//end on create
    
    public class MyAdapter extends BaseAdapter {
        ArrayList<List_item> item=new ArrayList<>();
		//View v =null;

        public MyAdapter(ArrayList<List_item> item){
            this.item=item;
        }

        @Override
        public int getCount() {
            return item.size();
        }

        @Override
        public Object getItem(int p1) {
            return null;
        }

        @Override
        public long getItemId(int p1) {
            return 0;
        }

        @Override
        public View getView(final int p1,  final View vv, ViewGroup p3) {
           
			MaxAndMinFont=getSharedPreferences("MaxAndMinFont",MODE_PRIVATE).getInt("MaxAndMinFont",MaxAndMinFont);
			ChangeToDarkMode=getSharedPreferences("ChangeToDarkMode",MODE_PRIVATE).getBoolean("ChangeToDarkMode",false);
			if(ChangeToDarkMode==true){
				NumperTheme=0;
			}
			
			final View v ;
			//v=getLayoutInflater().inflate(R.layout.fragment_theme1,null);
			v=null;
			//  if(v==null){}
			if(NumperTheme==0){			
				v=getLayoutInflater().inflate(R.layout.raw_itm_read_zkr_theme0,null); 
			}else if(NumperTheme==1){
				v=getLayoutInflater().inflate(R.layout.raw_itm_read_zkr,null); 
				LinearLayout bg_readZkr=findViewById(R.id.read_zkrLinearLayout);
				bg_readZkr.setBackgroundColor(Color.parseColor("#CAEFF0D4"));
				tvActionBar.setBackgroundColor(Color.parseColor("#FFFFB847"));
			}else if(NumperTheme==2){
				v=getLayoutInflater().inflate(R.layout.raw_itm_read_zkr_theme2,null); 
				LinearLayout bg_readZkr=findViewById(R.id.read_zkrLinearLayout);
				bg_readZkr.setBackgroundColor(Color.parseColor("#CAEFF0D4"));
				tvActionBar.setBackgroundColor(Color.parseColor("#FFFFB847"));
			}else if(NumperTheme==3){
				v=getLayoutInflater().inflate(R.layout.raw_itm_read_zkr_theme3,null); 
				LinearLayout bg_readZkr=findViewById(R.id.read_zkrLinearLayout);
				bg_readZkr.setBackgroundColor(Color.parseColor("#ffffff"));
				tvActionBar.setBackgroundColor(Color.parseColor("#648CF1"));
			}else if(NumperTheme==4){
				v=getLayoutInflater().inflate(R.layout.raw_itm_read_zkr_theme4,null); 
				LinearLayout bg_readZkr=findViewById(R.id.read_zkrLinearLayout);
				bg_readZkr.setBackgroundColor(Color.parseColor("#FFFFFBEF"));
				tvActionBar.setBackgroundColor(Color.parseColor("#648CF1"));
			}else if(NumperTheme==5){
				v=getLayoutInflater().inflate(R.layout.raw_itm_read_zkr_theme5,null); 
				LinearLayout bg_readZkr=findViewById(R.id.read_zkrLinearLayout);
				bg_readZkr.setBackgroundColor(Color.parseColor("#ffffff"));
				tvActionBar.setBackgroundColor(Color.parseColor("#FF7DBAE5"));
			}else if(NumperTheme==6){
				v=getLayoutInflater().inflate(R.layout.raw_itm_read_zkr_theme6,null); 
				LinearLayout bg_readZkr=findViewById(R.id.read_zkrLinearLayout);
				bg_readZkr.setBackgroundColor(Color.parseColor("#FFFFFCF2"));
				tvActionBar.setBackgroundColor(Color.parseColor("#FF7DBAE5"));
			}
			
			
			
			final TextView tvZkr=v.findViewById(R.id.raw_itm_read_zkrTextView);
            final TextView tvRepeate=v.findViewById(R.id.raw_itm_read_zkrRepeate);
			TextView tvShare=v.findViewById(R.id.raw_itm_read_zkrShare);
            LinearLayout mLinearLayout=v.findViewById(R.id.raw_itm_read_zkrLinearLayout);
			//LinearLayout mLinearLayoutaRepateAndShare=v.findViewById(R.id.raw_itm_read_zkrLinearLayoutRepeateAndShare);
		     LinearLayout bg_readZkr=findViewById(R.id.read_zkrLinearLayout);
             // RelativeLayout mRelativeLayout=v.findViewById(R.id.raw_itm_read_zkr_RelativeLayout);
			////////////////// Settings Font and Dark Mode///////////////////
				if(ChangeToDarkMode==true){
				//tvZkr.setBackgroundDrawable(getDrawable(R.drawable.shape_read_azkar_dark_mode));
			   // tvZkr.setTextColor(Color.parseColor("#ffffff"));
				tvActionBar.setBackgroundColor(Color.parseColor("#FF460377"));
				bg_readZkr.setBackgroundColor(Color.BLACK);
				mLinearLayout_settingsZkr.setBackgroundColor(Color.parseColor("#EB191919"));
				//mLinearLayoutaRepateAndShare.setBackgroundDrawable(getDrawable(R.drawable.shape_repeat_zkr_dark_mode));
				//mRelativeLayout.setBackgroundDrawable(getDrawable(R.drawable.shape_read_azkar_dark_mode));
				//mRelativeLayout.setBackgroundColor(Color.BLACK);
				btnDarkMode.setBackgroundDrawable(getDrawable(R.drawable.ic_sabah));
				Drawable d=getResources().getDrawable(R.drawable.muslim);
				tvActionBar.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
				
				//btnDarkMode.setImageResource(R.drawable.ic_sabah);
			}else{
				//tvZkr.setBackgroundDrawable(getDrawable(R.drawable.shape_read_azkar));
				//t.setBackgroundColor(Color.parseColor("#FFFFB847"));
				//mLinearLayout2.setBackgroundColor(Color.parseColor("#CAEFF0D4"));
				//mLinearLayout2.setBackgroundColor(Color.parseColor("#ffffff"));
				mLinearLayout_settingsZkr.setBackgroundColor(Color.parseColor("#FFD7D7D7"));
				//mLinearLayoutaRepateAndShare.setBackgroundDrawable(getDrawable(R.drawable.shape_repeat_zkr));
			  //  mRelativeLayout.setBackgroundDrawable(getDrawable(R.drawable.shape_repeat_zkr));
				btnDarkMode.setBackgroundDrawable(getDrawable(R.drawable.fj));
				Drawable d=getResources().getDrawable(R.drawable.muslim);
				tvActionBar.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
				
				//tvZkr.setTextColor(Color.parseColor("#000000"));
			}
			////////////////////////////////////////////////////
			
			
			//final TextView tvZkr=v.findViewById(R.id.raw_itm_read_zkrTextView);
           // final TextView tvRepeate=v.findViewById(R.id.raw_itm_read_zkrRepeate);
           // bg_readZkr.setMinimumHeight(tvZkr.getHeight());
			tvZkr.setText(item.get(p1).TextZkr);
			tvZkr.setTextSize(MaxAndMinFont);
			tvRepeate.setText("التكرار "+item.get(p1).RepeateZkr);
			v.setOnLongClickListener(new OnLongClickListener(){

					@Override
					public boolean onLongClick(View p11) {

						PopupMenu mPopupMenu=new PopupMenu(ReadZkr.this,v);
						mPopupMenu.getMenuInflater().inflate(R.menu.menu_fragment_azkar,mPopupMenu.getMenu());
						mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

								@Override
								public boolean onMenuItemClick(MenuItem mMenuItem) {
									switch(mMenuItem.getItemId()){
										case R.id.menue_fragment_Azkar_AddZkr:

											///////////////////////////////////////////////

											final Dialog mDialog1=new Dialog(ReadZkr.this);
											mDialog1.requestWindowFeature(mDialog1.getWindow().FEATURE_NO_TITLE);
											mDialog1.getWindow().setBackgroundDrawable(getDrawable( R.drawable.shape_dialog_add_azkar));
											mDialog1.setContentView(R.layout.dialog_add_azkar_muslim);

											Button btnSave1=mDialog1.findViewById(R.id.dialog_add_azkar_muslimBtnSave);
											Button btnBack1=mDialog1.findViewById(R.id.dialog_add_azkar_muslimBtnBack);
											final EditText textZkr1=mDialog1.findViewById(R.id.dialog_add_azkar_muslimEditText);
											final EditText textRepeate1=mDialog1.findViewById(R.id.dialog_add_azkar_muslimEditTextRepeate);
											
											btnSave1.setOnClickListener(new OnClickListener(){

													@Override
													public void onClick(View p11) {
														if(textZkr1.getText().toString().trim().equals("")){
															textZkr1.setError("من فضلك ادخل ذكر");
														}else if(textRepeate1.getText().toString().trim().equals("")){
															textRepeate1.setError("من فضلك ادخل التكرار");
														}else{
															
															//if(currentAzkar>25){
																//mDatabaseNewAzkarFromUser.insert_data(textZkr1.getText().toString(),Integer.parseInt(textRepeate1.getText().toString()));
															//}else{
															mDatabaseAllAzkar.insert_data(textZkr1.getText().toString(),Integer.parseInt(textRepeate1.getText().toString()));
															//}
															Azkar2.add(new List_item(textZkr1.getText().toString(),Integer.parseInt(textRepeate1.getText().toString())));
															MyAdapter m=new MyAdapter(Azkar2);
															mListView.setAdapter(m);
															mListView.setSelection(Azkar2.size());
															mDialog1.dismiss();
           											}//end if


													}
												});
											//////
											btnBack1.setOnClickListener(new OnClickListener(){

													@Override
													public void onClick(View p1) {
														mDialog1.dismiss();
													}


												});
											mDialog1.show();

											/////////////////////////////////////

											break;
										case R.id.menue_fragment_Azkar_DeletZkr:
											int positionListView=mListView.getFirstVisiblePosition();
											/*if(currentAzkar>25){
												mDatabaseNewAzkarFromUser.delete_data(Azkar2.get(p1).TextZkr);
											}else{*/
												mDatabaseAllAzkar.delete_data(Azkar2.get(p1).TextZkr);
											//}
											
											Azkar2.remove(p1);
											MyAdapter mm=new MyAdapter(Azkar2);
											mListView.setAdapter(mm);
											mListView.setSelection(positionListView);
											break;
										case R.id.menue_fragment_Azkar_EditZkr:

											/////////////////////////////////////

											final Dialog mDialog=new Dialog(ReadZkr.this);
											mDialog.requestWindowFeature(mDialog.getWindow().FEATURE_NO_TITLE);
											mDialog.getWindow().setBackgroundDrawable(getDrawable( R.drawable.shape_dialog_add_azkar));
											mDialog.setContentView(R.layout.dialog_edit_azkar);

											Button btnSave=mDialog.findViewById(R.id.dialog_edit_azkarBtnSave);
											Button btnBack=mDialog.findViewById(R.id.dialog_edit_azkarBtnBack);
											final EditText textZkr=mDialog.findViewById(R.id.dialog_edit_azkarEditText);
											final EditText textRepeate=mDialog.findViewById(R.id.dialog_edit_azkarEditTextRepeate);

											textZkr.setText(item.get(p1).TextZkr);
											textRepeate.setText(item.get(p1).RepeateZkr+"");
											//mDialog.getWindow().setLayout(600,700);
											btnSave.setOnClickListener(new OnClickListener(){

													@Override
													public void onClick(View p11) {
														if(textZkr.getText().toString().trim().equals("")){
															textZkr.setError("من فضلك ادخل ذكر");
														}else if(textRepeate.getText().toString().trim().equals("")){
															textRepeate.setError("من فضلك ادخل التكرار");
														}else{
															int positionListView=mListView.getFirstVisiblePosition();
															String NewTextZkr=textZkr.getText().toString();
															int NewRepeateZkr=Integer.parseInt(textRepeate.getText().toString());
															if(currentAzkar>25){
																mDatabaseNewAzkarFromUser.update_data(item.get(p1).TextZkr,NewTextZkr,NewRepeateZkr);
															}else{
																mDatabaseAllAzkar.update_data(item.get(p1).TextZkr,NewTextZkr,NewRepeateZkr);
															}
															
														    Azkar2.set(p1,new List_item(NewTextZkr,NewRepeateZkr));
															MyAdapter mm=new MyAdapter(Azkar2);
															mListView.setAdapter(mm);
															mListView.setSelection(positionListView);
															mDialog.dismiss();
															
														}//end if



													}
												});

											//////
											btnBack.setOnClickListener(new OnClickListener(){

													@Override
													public void onClick(View p1) {
														mDialog.dismiss();
													}


												});
											mDialog.show();
											/////////////////////////////////////
											break;//end case edit zkr
									}
									return true;
								}


							});
						mPopupMenu.show();



						//Toast.makeText(getApplication(), "gh", Toast.LENGTH_SHORT).show();
						return true;
					}


				});
				
				
				//mListView.setons
				
			/////////////////////////////////////
			mLinearLayout.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(final View p11) {


						//int a=RepeateZkr.get(p1)-1;
						int a=item.get(p1).RepeateZkr-1;
						//RepeateZkr.set(p1,a);
						Azkar2.set(p1,new List_item(item.get(p1).TextZkr,a)); 
						tvRepeate.setText("التكرار "+a);
			    		if(a==0){
							v.startAnimation(mAnimation);
							try {
								Thread th=new Thread(){
									public void run(){
										try {
											sleep(500);
										} catch (InterruptedException e) {}
										runOnUiThread(new Runnable(){

												@Override
												public void run() {

													Azkar2.remove(p1);
													//RepeateZkr.remove(p1);
													final int lastVisible=p1;
													final int fristVisible=mListView.getFirstVisiblePosition();

													MyAdapter m2=new MyAdapter(Azkar2);
													mListView.setAdapter(m2);
													//m2.notifyDataSetChanged();
													mListView.setSelection(posotionList);
													if(Azkar2.size()==0){finish();}
												}


											});




									}
								};th.start();
							} catch (Exception e) {}
							//numperRepeate=Integer.parseInt(RepeateZkr.get(p1));
						}
						//positionZkr=mListView.getFirstVisiblePosition();
						//mListView.smoothScrollToPositionFromTop(positionZkr,0);
					}


				});// end click tvRepeate


			//
			tvShare.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p11) {
						Intent i=new Intent(Intent.ACTION_SEND);
						 i.putExtra(Intent.EXTRA_TEXT,item.get(p1).TextZkr);
						 i.setType("text/plain");
						 startActivity(Intent.createChooser(i,item.get(p1).TextZkr));
						 
					}


				});
			//
            return v;
        }


    }
	
    @Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.translate2,R.anim.translate_out2);
	}
}
