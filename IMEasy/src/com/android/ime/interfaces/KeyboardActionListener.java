package com.android.ime.interfaces;

public interface KeyboardActionListener {

	public void onCodeInput(int primaryCode, int x, int y);

	public void onTextInput(CharSequence text);
	
}
