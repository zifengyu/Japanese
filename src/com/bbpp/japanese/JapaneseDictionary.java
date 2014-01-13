package com.bbpp.japanese;

import java.util.ArrayList;
import java.util.Random;

public class JapaneseDictionary {	
	
	public static final int CUR_KANA	= 1;
	public static final int ANG_KANA	= 2;
	public static final int LINE_A	= 4;
	public static final int LINE_K	= 8;
	public static final int LINE_S	= 16;
	public static final int LINE_T	= 32;
	public static final int LINE_N	= 64;
	public static final int LINE_H	= 128;
	public static final int LINE_M	= 256;
	public static final int LINE_Y	= 512;
	public static final int LINE_R	= 1024;
	public static final int LINE_W	= 2048;
	
	private static JapaneseDictionary instance;
	
	private Random mRand = new Random();
	
	ArrayList<Integer> mCharList = new ArrayList<Integer>();
	
	public static JapaneseDictionary getInstance() {
		
		if (instance == null) {
			instance = new JapaneseDictionary();
		}
		
		return instance;
		
	}
	
	private JapaneseDictionary() {
		setCharMask(0xFFFFFFFF);
	}
	
	public void setCharMask(int mask) {
		mCharList.clear();
		boolean isCurKana = (mask & CUR_KANA) > 0;
		boolean isAngKana = (mask & ANG_KANA) > 0;	
		
		//Add n character
		addChar(1, 0, isCurKana,isAngKana);
				
		for (int i = LINE_A, line = 1; i <= LINE_W; i = i * 2, ++line) {
			if ((mask & i) > 0) {
				if (i == LINE_Y) {
					addChar(8, 1, isCurKana, isAngKana);
					addChar(8, 3, isCurKana, isAngKana);
					addChar(8, 5, isCurKana, isAngKana);
				} else if (i == LINE_W) {
					addChar(10, 1, isCurKana, isAngKana);					
					addChar(10, 5, isCurKana, isAngKana);					
				} else {
					for (int j = 1; j <= 5; ++j)
					addChar(line, j, isCurKana, isAngKana);
				}
			}
		}
		
	}	
	
	public int getRandomCharacter() {
		if (mCharList == null || mCharList.size() == 0)
			return -1;
		
		return mCharList.get(mRand.nextInt(mCharList.size()));
	}
	
	private void addChar(int l, int c, boolean cur, boolean ang) {
		int num = (l - 1) * 5 + c;
		if (cur) {
			mCharList.add(num * 2);
		}
		
		if (ang) {
			mCharList.add(num * 2 + 1);
		}
	}

}
