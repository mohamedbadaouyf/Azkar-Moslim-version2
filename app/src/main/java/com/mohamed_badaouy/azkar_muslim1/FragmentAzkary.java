package com.mohamed_badaouy.azkar_muslim1;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.mohamed_badaouy.azkar_muslim1.R;
import com.mohamed_badaouy.azkar_muslim1.SQLiteDatabase.DatabaseAllAzkar;
import java.util.ArrayList;

public class FragmentAzkary extends Fragment {
	ArrayList<List_item> arrayListAzkary=new ArrayList<>();
	static ArrayList<List_item> choosefavorite=new ArrayList();
	static DatabaseAllAzkar ChooseFavorite;
	static DatabaseAllAzkar addZkr;
	static int positionList=0;
	ListView mListView;
	Data_class mData_class=new Data_class();
	Dialog mDialoe;
	FragmentShowAzkar fragmentShowAzkar=new FragmentShowAzkar();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v=getLayoutInflater().inflate(R.layout.fragment_azkary,container,false);
		mListView=v.findViewById(R.id.fragment_azkaryListView);
		mListView.setDividerHeight(0);
		mListView.setScrollBarSize(0);
		ChooseFavorite=new DatabaseAllAzkar(getContext(), 9000);
		addZkr=new DatabaseAllAzkar(getContext(), 8000);

		arrayListAzkary = addZkr.read_data();
		choosefavorite=ChooseFavorite.read_data();
		MyAdapter m=new MyAdapter(arrayListAzkary);
		mListView.setAdapter(m);
		mListView.setSelection(positionList);
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
        public View getView( final int p1, View p2, ViewGroup p3) {
            final View v=getLayoutInflater().inflate(R.layout.raw_itm_listview,null,false);
			final TextView zkr=v.findViewById(R.id.raw_itm_TextViewZkr);
            final CheckBox mCheckBox=v.findViewById(R.id.raw_itm_listviewCheckBox);
			
			for (int i = 0; i < choosefavorite.size(); i++) {
				if(choosefavorite.get(i).TextZkr.equals(item.get(p1).TextZkr)){
					mCheckBox.setChecked(true);
					zkr.setTextColor(Color.parseColor("#FF207EEE"));

				}
			}
			
			
			mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton p11, boolean p2) {
						positionList=mListView.getFirstVisiblePosition();
						FragmentAzkary	mFragment=new FragmentAzkary();
						FragmentManager manger=getFragmentManager();
						FragmentTransaction ft=manger.beginTransaction();
						ft.replace(R.id.fragment_show_azkarFrameLayout,mFragment);
						ft.commit();
						a(mCheckBox,p2,item,p1,zkr);
					}
					
				});

			//////
			v.setOnClickListener(new OnClickListener(){

			 @Override
			 public void onClick(final View p11) {
			 PopupMenu pop=new PopupMenu(getContext(),v);
			 pop.getMenuInflater().inflate(R.menu.menu1,pop.getMenu());
			 pop.setOnMenuItemClickListener(new PopupMenu. OnMenuItemClickListener(){

			 @Override
			 public boolean onMenuItemClick(MenuItem p11) {
			     
				 switch(p11.getItemId()){
			          
			          case R.id.delete:
						 FragmentAzkary	mFragment=new FragmentAzkary();
						 FragmentManager manger=getFragmentManager();
						 FragmentTransaction ft=manger.beginTransaction();
						 ft.replace(R.id.fragment_show_azkarFrameLayout,mFragment);
						 ft.commit();
						 if(choosefavorite.size()<=1&&mCheckBox.isChecked()){
							 Toast.makeText(getContext(),"حتى يستمر التطبيق بالعمل يجب تفعيل ذكر واحد علي الاقل", Toast.LENGTH_LONG).show();
							 mCheckBox.setChecked(true);
						 }else{
							 ChooseFavorite.delete_data(item.get(p1).TextZkr);
							 addZkr.delete_data(item.get(p1).TextZkr);
							 arrayListAzkary.remove(p1);
							 MyAdapter m=new MyAdapter(arrayListAzkary);
							 mListView.setAdapter(m);

						 }
						  
						  
							
			          break;
			          case R.id.share:
						  Intent i=new Intent(Intent.ACTION_SEND);
						  i.putExtra(Intent.EXTRA_TEXT,item.get(p1).TextZkr);
						  i.setType("text/*");
						  startActivity(Intent.createChooser(i,item.get(p1).TextZkr));
						  
			            break;

			   }

			      return true;
			 }
			 });
			 pop.show();
			 
			 }

			 });
			/////


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
			if(choosefavorite.size()<=1){
				Toast.makeText(getContext(),"حتى يستمر التطبيق بالعمل يجب تفعيل ذكر واحد علي الاقل", Toast.LENGTH_LONG).show();
				mCheckBox.setChecked(true);
			}else{
				ChooseFavorite.delete_data(item.get(position).TextZkr);
				
			}
		    
		}
	}//
	
    
}
