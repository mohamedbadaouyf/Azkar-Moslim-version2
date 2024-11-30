package com.mohamed_badaouy.azkar_muslim1.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mohamed_badaouy.azkar_muslim1.List_item;
import java.util.ArrayList;

public class DatabaseAllAzkar extends SQLiteOpenHelper{
    
    ArrayList<List_item>itms=new ArrayList<>();
	public DatabaseAllAzkar(Context context,int file){
		super(context,"databaseAzkar2"+file+".db",null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		p1.execSQL("create table mytable(id INTEGER,TextZkr TEXT,RepeateZkr INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		p1.execSQL("DROP TABLE IF EXISTS mytable");
	}


	public boolean insert_data(String TextZkr,int RepeateZkr){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("TextZkr",TextZkr);
		values.put("RepeateZkr",RepeateZkr);
		long result=db.insert("mytable",null,values);
		if(result==-1){
			return false;
		}else{return true;}
	}

	public boolean update_data(String TextZkrForUpdate,String TextZkr,int RepeateZkr){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("TextZkr",TextZkr);
		values.put("RepeateZkr",RepeateZkr);
		long result=db.update("mytable",values,"TextZkr=?",new String[]{TextZkrForUpdate});
		if(result==-1){
			return false;
		}else{return true;}
	}

	public ArrayList read_data(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor res=db.rawQuery("select * from mytable",null);
		res.moveToFirst();
		while(res.isAfterLast()==false){
			String textZkr=res.getString(1);
			int RepeateZkr=res.getInt(2);
			itms.add(new List_item( textZkr,RepeateZkr));
			res.moveToNext();
		}
		return itms;
	}


	public void delete_data(String del){
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete("mytable","TextZkr=?",new String[]{del});

	}


	public int check_data(String a){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor res=db.rawQuery("select * from mytable Where TextZkr Like'"+a+"'",null);
		int aa=res.getCount();
		return aa;
	}
	
    
}
