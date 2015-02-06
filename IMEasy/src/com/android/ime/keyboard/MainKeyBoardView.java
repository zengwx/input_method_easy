package com.android.ime.keyboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import com.android.ime.interfaces.KeyboardActionListener;

public class MainKeyBoardView extends KeyboardView {

	public MainKeyBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MainKeyBoardView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@SuppressLint("NewApi")
	public MainKeyBoardView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}
	
	private KeyboardActionListener mKeyboardActionListener;
	
	public void setKeyboardActionListener(final KeyboardActionListener listener) {
		mKeyboardActionListener = listener;
	}

}
