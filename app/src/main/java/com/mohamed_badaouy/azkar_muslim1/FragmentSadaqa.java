package com.mohamed_badaouy.azkar_muslim1;
import android.app.Fragment;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentSadaqa extends Fragment{
    
    Button btnShareWhatsApp,btnShareFacebook,btnCopyHipeirLink;
	String LinkApp="https://drive.google.com/drive/folders/1OSvFm92pTg3H55jhL_ekws0em1fWLmpz";
	String text="برنامج أذكار المسلم للأندرويد والذي يضم العديد من الأذكار والأدعية التي لا غني عنها في حياتنا اليومية";
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=getLayoutInflater().inflate(R.layout.fragment_sadaqa,container,false);
	    btnShareWhatsApp=v.findViewById(R.id.fragment_sadaqaButtonShareWhatsApp);
		btnCopyHipeirLink=v.findViewById(R.id.fragment_sadaqaButtonCopyHypeirLink);
		btnShareFacebook=v.findViewById(R.id.fragment_sadaqaButtonShareFacebook);
		
		btnShareWhatsApp.setOnClickListener(new OnClickListener(){

	      @Override
	       public void onClick(View p1) {
			   
			   Intent i=new Intent(Intent.ACTION_SEND);
			   i.setType("text/plain");
			   i.putExtra(Intent.EXTRA_TEXT,text+"\n"+LinkApp);
			   i.setPackage("com.whatsapp");
			   startActivity(i);
			   
			   
		   }
				
		 });
		///////////////////////////////////////// 
		btnCopyHipeirLink.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					ClipboardManager cli=(ClipboardManager)getContext().getSystemService(getContext().CLIPBOARD_SERVICE);
				     cli.setText(LinkApp);
					Toast.makeText(getContext(), "تم نسخ الرابط بنجاح", Toast.LENGTH_LONG).show();
					}

			});
		///////////////////////////////////////// 
		btnShareFacebook.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					Intent i=new Intent(Intent.ACTION_SEND);
					i.setType("text/plain");
					i.putExtra(Intent.EXTRA_TEXT,text+"\n"+LinkApp);
					i.setPackage("com.facebook.katana");
					startActivity(i);
				}

			});
			
		 
		 
		return v;
    
	}
	//////
	
	
		
	}
	
	

