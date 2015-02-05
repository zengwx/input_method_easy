package com.android.ime;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.View.OnClickListener;

public class InputMethodEasyService extends InputMethodService {

	/**
	 * 加载主要键盘布局.
	 */
	@Override
	public View onCreateInputView() {
		View view = getLayoutInflater().inflate(R.layout.main_keyboard_view,
				null);
		return view;
	}

	/**
	 * 加载候选键盘布局.
	 */
	@Override
	public View onCreateCandidatesView() {
		View view = getLayoutInflater().inflate(R.layout.cand_keyboard_view,
				null);
		view.findViewById(R.id.button1).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						
					}
				});
		return view;
	}

}
