package com.android.ime.keyboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.android.ime.InputMethodEasyService;
import com.android.ime.R;

public class KeyboardSwitcher {

	private static final String TAG = KeyboardSwitcher.class.getSimpleName();

	private static final KeyboardSwitcher sInstance = new KeyboardSwitcher();
	private MainKeyBoardView mKeyboardView;
	private InputView mCurrentInputView;
	private Context mContext = null;
	private InputMethodEasyService mInputMethodEasyService;

	/**
	 * 单例模式
	 */
	public static KeyboardSwitcher getInstance() {
		return sInstance;
	}

	public void initInternal(Context context,
			InputMethodEasyService inputMethodEasyService) {
		mContext = context;
		mInputMethodEasyService = inputMethodEasyService;
	}

	public void initInternal(Context context) {
		this.mContext = context;
	}

	public View onCreateInputView(boolean isHardwareAcceleratedDrawingEnabled) {
		if (mContext == null)
			return null;
		if (mKeyboardView != null) {
			mKeyboardView.closing();
		}
		/* */
		mCurrentInputView = (InputView) LayoutInflater.from(mContext).inflate(
				R.layout.main_keyboard_view, null);
		mKeyboardView = (MainKeyBoardView) mCurrentInputView
				.findViewById(R.id.keyboard_view);
		/* 是否开启硬件加速 */
		if (isHardwareAcceleratedDrawingEnabled) {
			mKeyboardView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		}
		/* 初始化事件 */
		mKeyboardView.setKeyboardActionListener(mInputMethodEasyService);
		return mCurrentInputView;
	}

}
