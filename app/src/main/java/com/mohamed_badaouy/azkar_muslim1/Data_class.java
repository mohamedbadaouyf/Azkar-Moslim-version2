package com.mohamed_badaouy.azkar_muslim1;
import android.app.Application;
import com.mohamed_badaouy.azkar_muslim1.SQLiteDatabase.DatabaseAllAzkar;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data_class extends Application
{
String text;
	String array[];
    JSONArray mJSONArray;
	 static ArrayList<String> TitelsAzkar=new ArrayList();
    static ArrayList<List_item> FavoriteList=new ArrayList();

	private DatabaseAllAzkar ChooseFavorite;
    
	public  ArrayList Azkary(){
		ArrayList<String> ar=new ArrayList();
		ar.add("tسبحان الله وبحمده");
		/*ar.add("استغفر الله العظيم");
		ar.add("لا حول ولا قوة الا بالله");
		ar.add("لا اله الا الله");*/
		ar.add("صلي على محمد");
		ar.add("يارب لك الحمد كما ينبغى لجلال وجهك ولعظيم سلطانك");
		
		return ar;
	}
	
	public ArrayList AzkarApp(){
		
		ArrayList<List_item> ar=new ArrayList();
		ar.add(new List_item("سبحان الله وبحمده",0));
		ar.add(new List_item("استغفر الله العظيم",0));
		ar.add(new List_item("لا حول ولا قوة الا بالله",0));
		ar.add(new List_item("لا اله الا الله",0));
		ar.add(new List_item("صلي على محمد",0));
		ar.add(new List_item("يارب لك الحمد كما ينبغى لجلال وجهك ولعظيم سلطانك",0));
		ar.add(new List_item("أستغفر الله واتوب اليه",0));
		ar.add(new List_item("أستغفر الله الذى لا إله الا هو الحي القيوم واتوب اليه",0));
		ar.add(new List_item("أعوذ بكلمات الله التامات من شر ما خلق",0));
		ar.add(new List_item("اللهم أعني على ذكرك وشكرك وحسن عبادتك",0));
		ar.add(new List_item("اللهم انت السلام ومنك السلام تباركت يا ذا الجلال والاكرام",0));
		ar.add(new List_item("اللهم انت ربي لا إله إلا انت، عليك توكلت وانت رب العرش العظيم",0));
		ar.add(new List_item("اللهم إني اسالك العفو والعافية فى دينى ودنياي و أهلي ومالي",0));
		ar.add(new List_item("استغفر الله",0));
		
		return ar;
	}
	
	
    @Override
    public void onCreate() {
        super.onCreate();
        try
        {
            
            InputStream in=getAssets().open("azkar3.json");
             byte[] buffer=new byte[in.available()];
             in.read(buffer);
             in.close();
            String json;
            json=new String(buffer,StandardCharsets.UTF_8);
            
            try {
                 mJSONArray=new JSONArray(json);
             
                for(int i=0;i<mJSONArray.length();i++){
                    JSONObject mJSONObject=mJSONArray.getJSONObject(i);
					if(!TitelsAzkar.contains(mJSONObject.getString("category"))==true){
					TitelsAzkar.add(mJSONObject.getString("category"));
                   }
                                       
                }
				TitelsAzkar.add("الاربعون النووية");
               
            } catch (JSONException e) {}

              
            
        } 
        catch (IOException e)
        {}  
		ChooseFavorite=new DatabaseAllAzkar(getApplicationContext(), 9000);
		FavoriteList=ChooseFavorite.read_data();
		if(FavoriteList.size()<=1){
			ChooseFavorite.insert_data("صلي على محمد",0);
			ChooseFavorite.insert_data("سبحان الله وبحمده",0);
		}
    }//end on create

    
	
	//
   
}
