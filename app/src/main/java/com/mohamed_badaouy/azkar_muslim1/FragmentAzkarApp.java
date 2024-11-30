package com.mohamed_badaouy.azkar_muslim1;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.mohamed_badaouy.azkar_muslim1.R;
import com.mohamed_badaouy.azkar_muslim1.SQLiteDatabase.DatabaseAllAzkar;
import java.util.ArrayList;
import java.util.Collection;

public class FragmentAzkarApp extends Fragment {
	
	ArrayList<List_item> arrayList=new ArrayList();
	static ArrayList<List_item> choosefavorite2=new ArrayList();
	ListView mListView;
	Data_class mData_class=new Data_class();
	Dialog mDialog;
	DatabaseAllAzkar ChooseFavorite;
	static int positionList=0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v=getLayoutInflater().inflate(R.layout.fragment_azkar_app,container,false);
		mListView=v.findViewById(R.id.fragment_azkar_appListView);
		mListView.setDividerHeight(0);
		mListView.setScrollBarSize(0);
	;    ChooseFavorite=new DatabaseAllAzkar(getContext(),9000);
	    // arrayList.add("45");
		try{
		arrayList=mData_class.AzkarApp();
		choosefavorite2=ChooseFavorite.read_data();
		
	
		MyAdapter m=new MyAdapter(arrayList);
		  mListView.setAdapter(m);
		 mListView.setSelection(positionList);
		
		}catch(Exception e){Toast.makeText(getContext(),e.getMessage()+"", Toast.LENGTH_LONG).show();}
		
		
		
		return v;
	}
    
    public class MyAdapter extends BaseAdapter {

        ArrayList<List_item> item=new ArrayList();
		boolean checkedZkr=false;

        public MyAdapter(ArrayList<List_item> item1){
            this.item=item1;
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
        public View getView(final int p1, View p2, ViewGroup p3) {
            final View v=getLayoutInflater().inflate(R.layout.raw_itm_listview,null,false);
			final TextView zkr=v.findViewById(R.id.raw_itm_TextViewZkr);
            final CheckBox mCheckBox=v.findViewById(R.id.raw_itm_listviewCheckBox);
			Collection<List_item>f;
		
			for (int i = 0; i < choosefavorite2.size(); i++) {
                   if(choosefavorite2.get(i).TextZkr.equals(item.get(p1).TextZkr)){
					   mCheckBox.setChecked(true);
					   zkr.setTextColor(Color.parseColor("#FF207EEE"));
					   
				   }
			}
			
			
			
			mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton p11, boolean p2) {
						
						positionList=mListView.getFirstVisiblePosition();
						FragmentAzkarApp 	mFragment=new FragmentAzkarApp();
						FragmentManager manger=getFragmentManager();
						FragmentTransaction ft=manger.beginTransaction();
						ft.replace(R.id.fragment_show_azkarFrameLayout,mFragment);
						ft.commit();
						a(mCheckBox,p2,item,p1,zkr);
					}
				});


            zkr.setText(item.get(p1).TextZkr);

            return v;
        }
		
    }//end my adapter
	
	//
	public void a(CheckBox mCheckBox,boolean CheckBox,ArrayList<List_item> item,int position,TextView zkr){

		if(CheckBox==true){
			
		zkr.setTextColor(Color.parseColor("#FF207EEE"));
		ChooseFavorite.insert_data(item.get(position).TextZkr,0);
			
		}else{
			 
			zkr.setTextColor(Color.parseColor("#FF838383"));
			if(choosefavorite2.size()<=1){
				Toast.makeText(getContext(),"حتى يستمر التطبيق بالعمل يجب تفعيل ذكر واحد علي الاقل", Toast.LENGTH_LONG).show();
				mCheckBox.setChecked(true);
			}else{
				ChooseFavorite.delete_data(item.get(position).TextZkr);
                
			}
			
			
			
			 }
		
	}//
	
	
}
