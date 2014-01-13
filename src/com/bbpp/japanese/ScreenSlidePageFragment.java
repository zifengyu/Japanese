package com.bbpp.japanese;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by yua2 on 10/22/13.
 */
public class ScreenSlidePageFragment extends Fragment {
	
	public static final String CHAR_NUMBER = "com.bbpp.japanese.characterNumber";

	public static ScreenSlidePageFragment create(int characterNumber) {
		ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
		Bundle args = new Bundle();   
		args.putInt(CHAR_NUMBER, characterNumber);
		fragment.setArguments(args);
		return fragment;
	}

	//private 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final int charNum = getArguments() != null ? getArguments().getInt(CHAR_NUMBER, 0) : 0;
		
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);		
		Button btn = (Button)rootView.findViewById(R.id.char_button);
		btn.setBackgroundResource(CharacterResource.resId[charNum]);		
		btn.setOnTouchListener(new ImageButton.OnTouchListener(){  
			@Override  
			public boolean onTouch(View arg0, MotionEvent arg1) {  
				  
				if(arg1.getAction() == MotionEvent.ACTION_DOWN){  
					arg0.setBackgroundResource(CharacterResource.resId_P[charNum]);  
				}  
				else if(arg1.getAction() == MotionEvent.ACTION_CANCEL || arg1.getAction() == MotionEvent.ACTION_UP){  
					arg0.setBackgroundResource(CharacterResource.resId[charNum]);  
				}  

				return false;  
			}
		});

		return rootView;
	}
}