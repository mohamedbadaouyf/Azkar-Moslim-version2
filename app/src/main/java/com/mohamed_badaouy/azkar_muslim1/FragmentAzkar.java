package com.mohamed_badaouy.azkar_muslim1;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.mohamed_badaouy.azkar_muslim1.List_item;
import com.mohamed_badaouy.azkar_muslim1.R;
import com.mohamed_badaouy.azkar_muslim1.ReadZkr;
import com.mohamed_badaouy.azkar_muslim1.SQLiteDatabase.DatabaseAllAzkar;
import java.util.ArrayList;
import android.widget.SearchView;
import android.graphics.Color;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ScrollView;


public class FragmentAzkar extends Fragment {

   static ArrayList<String> titelAzkar=new ArrayList<>();
	static ArrayList<List_item> AddTitleAzkarList=new ArrayList<>();
	static ArrayList<String> ListSearch=new ArrayList<>();
	
    Data_class mData_class=new Data_class();
    ListView mListView;
	Context c;
	TextView tvActionbar;
	static DatabaseAllAzkar AddTitleAzkar;
	static int a=0;
	
	SearchView mSearchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=getLayoutInflater().inflate(R.layout.fragment_azkar,container,false);
       c=getContext();
        mListView=v.findViewById(R.id.fragment_azkarListView);
        tvActionbar=v.findViewById(R.id.fragment_azkarActionBar);
		mSearchView=v.findViewById(R.id.fragment_azkarSearchView);
		
		titelAzkar=mData_class.TitelsAzkar;
		/*AddTitleAzkar=new DatabaseAllAzkar(getContext(),1238888);
		AddTitleAzkarList.clear();
		AddTitleAzkarList.addAll(AddTitleAzkar.read_data());
		for (int i = 0; i < AddTitleAzkarList.size(); i++) {
		    if(!titelAzkar.contains(AddTitleAzkarList.get(i).TextZkr)==true){
				titelAzkar.add( AddTitleAzkarList.get(i).TextZkr);
			}

		}*/
		
		MyAdapter m=new MyAdapter(titelAzkar);
        mListView.setAdapter(m);
		mListView.setSelection(-1);
		mListView.setDividerHeight(0);
		mListView.setScrollBarSize(20);
		mListView.setScrollbarFadingEnabled(false);
		mListView.setScrollBarStyle(android.R.style.Theme);
		//Toast.makeText(getContext(),titelAzkar.size()+ "", Toast.LENGTH_SHORT).show();
		
		mSearchView.setOnQueryTextListener(new OnQueryTextListener(){

				@Override
				public boolean onQueryTextSubmit(String p1) {
					SearchAzkar(p1);
					return true;
				}

				@Override
				public boolean onQueryTextChange(String p1) {
					SearchAzkar(p1);
					if(p1.trim().equals("")){
						ListSearch.clear();
						MyAdapter m=new MyAdapter(titelAzkar);
						mListView.setAdapter(m);
					}
					return true;
				}
				
			
		});
		
		
		mListView.setOnScrollListener(new AbsListView.OnScrollListener(){

				@Override
				public void onScrollStateChanged(AbsListView p1, int p2) {
				}

				@Override
				public void onScroll(AbsListView p1, int p2, int p3, int p4) {
					 a=mListView.getFirstVisiblePosition();
					if(p2==0){
						tvActionbar.setHeight(200);
						tvActionbar.setTextSize(25);
						//tvActionbar.setElevation(100);
						mListView.setPadding(0,80,0,0);
					}else{
						tvActionbar.setHeight(100);
						tvActionbar.setTextSize(17);
						mListView.setPadding(0,23,0,0); 
					}
				}
				
				
			});
			
			
        mListView.setOnItemClickListener(new OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
               
					if(ListSearch.size()>0){ 
						for (int i = 0; i < titelAzkar.size(); i++) {
							if(titelAzkar.get(i).equals( ListSearch.get(p3))){
								ReadZkr.currentAzkar=i;
							}
						}
					}else{
						ReadZkr.currentAzkar=p3;
					}//end if
					Intent i=new Intent(c,ReadZkr.class);
					i.putExtra("TitleAzkar",titelAzkar.get(p3));
					ReadZkr.category=titelAzkar.get(p3);
					startActivity(i);
                }
                
            
        });
        
		/*mListView.setOnItemLongClickListener(new OnItemLongClickListener(){

				@Override
				public boolean onItemLongClick(AdapterView<?> p12, View p2, final int p1, long p4) {
					
					PopupMenu mPopupMenu=new PopupMenu(getContext(),p2);
					mPopupMenu.getMenuInflater().inflate(R.menu.menu_fragemend_azkar_new_azkar_from_user,mPopupMenu.getMenu());
					mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

							@Override
							public boolean onMenuItemClick(MenuItem mMenuItem) {
								switch(mMenuItem.getItemId()){
									case R.id.menue_fragment_Azkar_AddZkr:

										///////////////////////////////////////////////

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
													}else if(titelAzkar.contains(textZkr.getText().toString())==true){
														Toast.makeText(getContext(), "البيانات موجوده بالفعل", Toast.LENGTH_LONG).show();
													}else{

														AddTitleAzkar.insert_data(textZkr.getText().toString(),0);
													    titelAzkar.add(textZkr.getText().toString());
														MyAdapter m=new MyAdapter(titelAzkar);
														mListView.setAdapter(m);
														mListView.setSelection(titelAzkar.size());
														mDialog.dismiss();
													}//end if

												}


											});
										btnBack.setOnClickListener(new OnClickListener(){

												@Override
												public void onClick(View p11) {
													mDialog.dismiss();
												}


											});
										mDialog.show();

										/////////////////////////////////////

										break;
									case R.id.menue_fragment_Azkar_DeletZkr:
										try{
										if(p1>25){ 
											AddTitleAzkar.delete_data(titelAzkar.get(p1));
											//Toast.makeText(getContext(),titelAzkar.get(p1), Toast.LENGTH_LONG).show();
											titelAzkar=mData_class.TitelsAzkar;
											titelAzkar.remove(p1);
											//titelAzkar.clear();
											MyAdapter m=new MyAdapter(titelAzkar);
											mListView.setAdapter(m);
											mListView.setSelection(a);
										}else{
											Toast.makeText(getContext(), "لا يمكن حذف أذكار التطبيق", Toast.LENGTH_LONG).show();
										}
										}catch(Exception e){Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_LONG).show();}
										break;
																	}
								return true;
							}


						});
					mPopupMenu.show();
					
					
					return false;
				}
				
			
		});*/
	
		
        return v;
    }
    
   public class MyAdapter extends BaseAdapter {
        ArrayList<String> item=new ArrayList<>();
        public MyAdapter(ArrayList<String> item){
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
        public View getView(int p1, View p2, ViewGroup p3) {
            View v=getLayoutInflater().inflate(R.layout.raw_item_fragment_azkar,null);
            TextView tvZkr=v.findViewById(R.id.raw_item_fragment_azkarTextView);
            tvZkr.setText(item.get(p1));
		    if(ListSearch.size()<1){
			if(p1==0){
				Drawable d=getResources().getDrawable(R.drawable.sabah1);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==1){
				Drawable d=getResources().getDrawable(R.drawable.masaa2);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==3){
				Drawable d=getResources().getDrawable(R.drawable.alarm);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==1){
				Drawable d=getResources().getDrawable(R.drawable.masaa);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==1){
				Drawable d=getResources().getDrawable(R.drawable.masaa);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==6){
				Drawable d=getResources().getDrawable(R.drawable.wodoa1);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==7){
				Drawable d=getResources().getDrawable(R.drawable.wodoa1);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==1){
				Drawable d=getResources().getDrawable(R.drawable.masaa);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==000){
				Drawable d=getResources().getDrawable(R.drawable.mosq);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==1){
				Drawable d=getResources().getDrawable(R.drawable.masaa);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==1){ 
				Drawable d=getResources().getDrawable(R.drawable.masaa);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==10||p1==11||p1==12){
			Drawable d=getResources().getDrawable(R.drawable.mosque1);
			tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==13){
				Drawable d=getResources().getDrawable(R.drawable.azan);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}else if(p1==14||p1==15||p1==16||p1==17){
				Drawable d=getResources().getDrawable(R.drawable.thop1);
				tvZkr.setCompoundDrawablesRelativeWithIntrinsicBounds(d,null,null,null);
			}
			}//end if check from listSearch
            return v;
        }
        
        
    }
	
	//////////// methods ///////////
	
	public void SearchAzkar(String textSearch){
		ListSearch.clear();
		
		for (int i = 0; i < titelAzkar.size(); i++) {
             String item=titelAzkar.get(i).toString();
			 if(item.contains(textSearch)==true){
				 ListSearch.add(item);
			 }
		}
		MyAdapter m=new MyAdapter(ListSearch);
		mListView.setAdapter(m);
	}
     //////////****//////////
	
    
}
