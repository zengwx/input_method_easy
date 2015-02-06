package com.android.ime.keyboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import com.android.ime.interfaces.KeyboardActionListener;

/**
 * 输入法主界面.
 */
public class MainKeyBoardView extends KeyboardView {
	
	private KeyboardActionListener mKeyboardActionListener;
	
	public MainKeyBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MainKeyBoardView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setKeyboardActionListener(final KeyboardActionListener listener) {
		mKeyboardActionListener = listener;
	}

}
