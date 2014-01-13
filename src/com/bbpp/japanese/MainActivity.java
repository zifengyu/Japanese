package com.bbpp.japanese;

import java.util.ArrayList;

import cn.waps.AppConnect;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TabHost;

public class MainActivity extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AppConnect.getInstance(this);
        
        setContentView(R.layout.activity_main);

        TabHost tabs = (TabHost)findViewById(R.id.tab_host);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("tag1");
        spec.setContent(R.id.recite_tab);
        spec.setIndicator("", getResources().getDrawable(R.drawable.ic_action_edit));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("tag2");
        spec.setContent(R.id.settings_tab);
        spec.setIndicator("", getResources().getDrawable(R.drawable.ic_action_settings));
        tabs.addTab(spec);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

    }
    
    

    @Override
	protected void onDestroy() {
		AppConnect.getInstance(this).close();
		super.onDestroy();
	}
    
    public void updatePreference(View view) {
    	
    	SharedPreferences pref = getSharedPreferences(SettingsFragment.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		int mask = 0;
		
		int nakaRange;		
		if (((RadioButton)findViewById(R.id.radio_cur_kana)).isChecked()) {
			nakaRange = JapaneseDictionary.CUR_KANA;
		} else if (((RadioButton)findViewById(R.id.radio_ang_kana)).isChecked()) {
			nakaRange = JapaneseDictionary.ANG_KANA;
		} else {
			nakaRange = JapaneseDictionary.ANG_KANA | JapaneseDictionary.CUR_KANA;
		}
		editor.putInt(SettingsFragment.PREF_NAKA_RANGE, nakaRange);
		mask |= nakaRange;
				
		boolean line = ((CheckBox)findViewById(R.id.checkbox_line_a)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_A, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_A;
		}
		
		line = ((CheckBox)findViewById(R.id.checkbox_line_k)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_K, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_K;
		}
		
		line = ((CheckBox)findViewById(R.id.checkbox_line_s)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_S, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_S;
		}
		
		line = ((CheckBox)findViewById(R.id.checkbox_line_t)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_T, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_T;
		}
		
		line = ((CheckBox)findViewById(R.id.checkbox_line_n)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_N, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_N;
		}
		
		line = ((CheckBox)findViewById(R.id.checkbox_line_h)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_H, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_H;
		}
		
		line = ((CheckBox)findViewById(R.id.checkbox_line_m)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_M, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_M;
		}
		
		line = ((CheckBox)findViewById(R.id.checkbox_line_y)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_Y, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_Y;
		}
		
		line = ((CheckBox)findViewById(R.id.checkbox_line_r)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_R, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_R;
		}
		
		line = ((CheckBox)findViewById(R.id.checkbox_line_w)).isChecked();
		editor.putBoolean(SettingsFragment.PREF_LINE_W, line);
		if (line) {			
			mask |= JapaneseDictionary.LINE_W;
		}
				
		JapaneseDictionary.getInstance().setCharMask(mask);
				
		editor.commit();
    }



	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        static final int NUM_PAGES = Integer.MAX_VALUE;       
        
        private ArrayList<Integer> mCharList = new ArrayList<Integer>();
        
        private JapaneseDictionary mDict;

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            
            mDict = JapaneseDictionary.getInstance();
        }        

        @Override
        public Fragment getItem(int i) {
        	int charNum;
        	if (i < mCharList.size())
        		charNum = mCharList.get(i);
        	else {
        		charNum = mDict.getRandomCharacter();
        		mCharList.add(charNum);
        	}
            return ScreenSlidePageFragment.create(charNum);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }      
        
    }
}