package com.bbpp.japanese;

import cn.waps.AdView;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class SettingsFragment extends Fragment {
	
	public final static String PREF_NAME			= "com.bbpp.japanese";
	public final static String PREF_NAKA_RANGE	= "com.bbpp.japanese.cur_naka";
	public final static String PREF_LINE_A		= "com.bbpp.japanese.line_a";
	public final static String PREF_LINE_K		= "com.bbpp.japanese.line_k";
	public final static String PREF_LINE_S		= "com.bbpp.japanese.line_s";
	public final static String PREF_LINE_T		= "com.bbpp.japanese.line_t";
	public final static String PREF_LINE_N		= "com.bbpp.japanese.line_n";
	public final static String PREF_LINE_H		= "com.bbpp.japanese.line_h";
	public final static String PREF_LINE_M		= "com.bbpp.japanese.line_m";
	public final static String PREF_LINE_Y		= "com.bbpp.japanese.line_y";
	public final static String PREF_LINE_R		= "com.bbpp.japanese.line_r";
	public final static String PREF_LINE_W		= "com.bbpp.japanese.line_w";
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.setting_fragment_layout, container, false);		
		
		loadPreferences(rootView);
		
		new AdView(getActivity(), (LinearLayout)rootView.findViewById(R.id.settings_layout)).DisplayAd();

		return rootView;
	}
	
	private void loadPreferences(ViewGroup rootView) {
		SharedPreferences pref = getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		
		int mask = 0;
		
		int nakaRange = pref.getInt(PREF_NAKA_RANGE, JapaneseDictionary.ANG_KANA | JapaneseDictionary.CUR_KANA);		
		if (nakaRange == JapaneseDictionary.CUR_KANA) {
			((RadioButton)rootView.findViewById(R.id.radio_cur_kana)).setChecked(true);
		} else if (nakaRange == JapaneseDictionary.ANG_KANA) {
			((RadioButton)rootView.findViewById(R.id.radio_ang_kana)).setChecked(true);
		} else {
			((RadioButton)rootView.findViewById(R.id.radio_both_kana)).setChecked(true);
		}
		mask |= nakaRange;
		
		boolean line = pref.getBoolean(PREF_LINE_A, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_a)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_A;
		}
		
		line = pref.getBoolean(PREF_LINE_K, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_k)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_K;
		}
		
		line = pref.getBoolean(PREF_LINE_S, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_s)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_S;
		}
		
		line = pref.getBoolean(PREF_LINE_T, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_t)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_T;
		}
		
		line = pref.getBoolean(PREF_LINE_N, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_n)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_N;
		}
		
		line = pref.getBoolean(PREF_LINE_H, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_h)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_H;
		}
		
		line = pref.getBoolean(PREF_LINE_M, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_m)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_M;
		}
		
		line = pref.getBoolean(PREF_LINE_Y, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_y)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_Y;
		}
		
		line = pref.getBoolean(PREF_LINE_R, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_r)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_R;
		}
		
		line = pref.getBoolean(PREF_LINE_W, true);
		((CheckBox)rootView.findViewById(R.id.checkbox_line_w)).setChecked(line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_W;
		}	
		
		JapaneseDictionary.getInstance().setCharMask(mask);
	}
	
}
