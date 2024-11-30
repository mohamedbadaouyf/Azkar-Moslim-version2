package com.mohamed_badaouy.azkar_muslim1;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Map;
import com.mohamed_badaouy.azkar_muslim1.SQLiteDatabase.DatabaseAllAzkar;

 public class FragmentShowAzkar extends Fragment {
    
	 Fragment mFragment;
     ListView mListView;
     ArrayList<String> arrayList=new ArrayList();
	ArrayList<String> choosefavorite=new ArrayList();
	
	ArrayList<String> arrayListAzkary=new ArrayList();
	
     Button btn_azkary,btn_azkar_app;
	TextView btn_add_azkar;
	 
	static   String CheckFromListShowZkr="AzkaryApp";
	Data_class mData_class=new Data_class();
     Dialog mDialog;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=getLayoutInflater().inflate(R.layout.fragment_show_azkar,container,false);
	btn_azkar_app=v.findViewById(R.id.fragment_show_btn_azkar_app);
        btn_azkary=v.findViewById(R.id.fragment_show_btn_azkary);
		btn_add_azkar=v.findViewById(R.id.fragment_show_azkar_txtAddAzkar);
	
		//choosefavorite=mData_class.Favorite();
		//try{
        btn_azkar_app.setBackgroundDrawable(getResources().getDrawable( R.drawable.shape_btn_azkary_blue));
		btn_azkary.setBackgroundDrawable(getResources().getDrawable( R.drawable.shape_btn_azkary_wight));
		btn_add_azkar.setVisibility(View.INVISIBLE);
		
		//getContext().startService(new Intent(getContext(),MyServiceforeground.class));
		
		
		mFragment=new FragmentAzkarApp();
		FragmentManager manger=getFragmentManager();
		FragmentTransaction ft=manger.beginTransaction();
		ft.replace(R.id.fragment_show_azkarFrameLayout,mFragment);
		ft.commit();
		
		//}catch(Exception e){Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();}
		
              btn_azkar_app.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                   btn_azkar_app.setBackgroundDrawable(getResources().getDrawable( R.drawable.shape_btn_azkary_blue));
					btn_azkary.setBackgroundDrawable(getResources().getDrawable( R.drawable.shape_btn_azkary_wight));
					btn_add_azkar.setVisibility(View.INVISIBLE);
					mFragment=new FragmentAzkarApp();
					FragmentManager manger=getFragmentManager();
					FragmentTransaction ft=manger.beginTransaction();
					ft.replace(R.id.fragment_show_azkarFrameLayout,mFragment);
					ft.commit();
					
                }
                
            
        });
        btn_azkary.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
					btn_azkar_app.setBackgroundDrawable(getResources().getDrawable( R.drawable.shape_btn_azkary_wight));
					btn_azkary.setBackgroundDrawable(getResources().getDrawable( R.drawable.shape_btn_azkary_blue));
					btn_add_azkar.setVisibility(View.VISIBLE);
					mFragment=new FragmentAzkary();
					FragmentManager manger=getFragmentManager();
					FragmentTransaction ft=manger.beginTransaction();
					ft.replace(R.id.fragment_show_azkarFrameLayout,mFragment);
					ft.commit();
                   
                }


            });
			
		btn_add_azkar.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
							
					final Dialog mDialog=new Dialog(getContext());
					mDialog.requestWindowFeature(mDialog.getWindow().FEATURE_NO_TITLE);
					mDialog.getWindow().setBackgroundDrawable(getContext().getDrawable( R.drawable.shape_dialog_add_azkar));
					mDialog.setContentView(R.layout.dialog_add_azkar);
					mDialog.getWindow().setLayout(600,450);
					Button btnSave=mDialog.findViewById(R.id.dialog_add_azkarBtnSave);
					Button btnBack=mDialog.findViewById(R.id.dialog_add_azkarBtnBack);
					final EditText textZkr=mDialog.findViewById(R.id.dialog_add_azkarEditText);
					btnSave.setOnClickListener(new OnClickListener(){

							@Override
							public void onClick(View p1) {
								if(textZkr.getText().toString().trim().equals("")){
									textZkr.setError("من فضلك ادخل ذكر");
								}else{
								
								DatabaseAllAzkar addZkr=new DatabaseAllAzkar(getContext(), 8000);
								addZkr.insert_data(textZkr.getText().toString(),0);
								
								mDialog.dismiss();
								btn_azkar_app.setBackgroundDrawable(getResources().getDrawable( R.drawable.shape_btn_azkary_wight));
								btn_azkary.setBackgroundDrawable(getResources().getDrawable( R.drawable.shape_btn_azkary_blue));
								btn_add_azkar.setVisibility(View.VISIBLE);
								mFragment=new FragmentAzkary();
								FragmentManager manger=getFragmentManager();
								FragmentTransaction ft=manger.beginTransaction();
								ft.replace(R.id.fragment_show_azkarFrameLayout,mFragment);
								ft.commit();}//end if
								
							}
							
						
					});
					btnBack.setOnClickListener(new OnClickListener(){

							@Override
							public void onClick(View p1) {
								mDialog.dismiss();
							}


						});
					mDialog.show();
					
				}
				
			
		});
		
				
        return v;
        
    }
    
	
}
