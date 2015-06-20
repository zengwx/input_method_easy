package com.opensource.im;

import android.inputmethodservice.InputMethodService;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

public class OpenSourceIMService extends InputMethodService {

	private InputConnection mConnection = null;
	private View mRootView;
	private View mCandView;
	private View number_lay;
	private View defualt_lay;
	
	@Override
	public View onCreateInputView() {
		LayoutInflater inflater = getLayoutInflater();
		mRootView = inflater.inflate(R.layout.skb_container, null);
		number_lay = mRootView.findViewById(R.id.number_lay);
		defualt_lay = mRootView.findViewById(R.id.defualt_lay);
		//
		return mRootView;
	}

	@Override
	public View onCreateCandidatesView() {
		LayoutInflater inflater = getLayoutInflater();
		mCandView = inflater.inflate(R.layout.skb_cand_container, null);
		return mCandView;
	}

	@Override
	public void onStartInputView(EditorInfo editorInfo, boolean restarting) {
		mConnection = getCurrentInputConnection();
		setCandidatesViewShown(true);
		getKeyboardMode(editorInfo);
		super.onStartInputView(editorInfo, restarting);
	}

	/**
	 * 获取输入法键盘类型.
	 */
	public int getKeyboardMode(final EditorInfo editorInfo) {
		if (editorInfo == null) {
			return KeyBoardID.MODE_TEXT;
		}
		//
		final int inputType = editorInfo.inputType;
		final int variation = inputType & InputType.TYPE_MASK_VARIATION;
		// 根据 eidttext inputtype 返回软键盘类型.
		switch (inputType & InputType.TYPE_MASK_CLASS) {
		case InputType.TYPE_CLASS_NUMBER: // 数字键盘.
			// return KeyBoardID.MODE_NUMBER;
		case InputType.TYPE_CLASS_PHONE: // 显示符号键盘. phone拨号.
			// return KeyBoardID.MODE_PHONE;
		case InputType.TYPE_CLASS_DATETIME: // 日期，时间. 文本输入框要求输入数字或日期时.
			defualt_lay.setVisibility(View.GONE);
			number_lay.setVisibility(View.VISIBLE);
			switch (variation) {
			case InputType.TYPE_DATETIME_VARIATION_DATE: // 多一个 "/"
				return KeyBoardID.MODE_DATE;
			case InputType.TYPE_DATETIME_VARIATION_TIME: // 多一个 ":"
				return KeyBoardID.MODE_TIME;
			default: // InputType.TYPE_DATETIME_VARIATION_NORMAL
				return KeyBoardID.MODE_DATETIME; // 多两个 "/"，":"
			}
		case InputType.TYPE_CLASS_TEXT: // 显示输入字母的软键盘.
			number_lay.setVisibility(View.GONE);
			defualt_lay.setVisibility(View.VISIBLE);
			if (KeyBoardUtils.isEmailVariation(variation)) {
				return KeyBoardID.MODE_EMAIL;
			} else if (variation == InputType.TYPE_TEXT_VARIATION_URI) {
				return KeyBoardID.MODE_URL;
			} else if (variation == InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE) {
				return KeyBoardID.MODE_IM;
			} else if (variation == InputType.TYPE_TEXT_VARIATION_FILTER) {
				return KeyBoardID.MODE_TEXT;
			} else {
				return KeyBoardID.MODE_TEXT;
			}
		default:
			defualt_lay.setVisibility(View.VISIBLE);
			number_lay.setVisibility(View.GONE);
			return KeyBoardID.MODE_TEXT;
		}
	}

	@Override
	public void onFinishInputView(boolean finishingInput) {
		super.onFinishInputView(finishingInput);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onEvaluateFullscreenMode() {
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (true)
			return false;
		switch (keyCode) {
		case KeyEvent.KEYCODE_NUMPAD_0:
		case KeyEvent.KEYCODE_NUMPAD_1:
		case KeyEvent.KEYCODE_NUMPAD_2:
		case KeyEvent.KEYCODE_NUMPAD_3:
		case KeyEvent.KEYCODE_NUMPAD_4:
		case KeyEvent.KEYCODE_NUMPAD_5:
		case KeyEvent.KEYCODE_NUMPAD_6:
		case KeyEvent.KEYCODE_NUMPAD_7:
		case KeyEvent.KEYCODE_NUMPAD_8:
		case KeyEvent.KEYCODE_NUMPAD_9:
			if (event.isNumLockOn()) {
				onTextInput(Integer.toString(keyCode
						- KeyEvent.KEYCODE_NUMPAD_0));
			}
			return true;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (true)
			return false;
		return super.onKeyUp(keyCode, event);
	}

	public void onTextInput(CharSequence text) {
		mConnection.beginBatchEdit();
		mConnection.commitText(text, 1); // 输入文本.
		mConnection.endBatchEdit();
	}

	public void onCodeInput(int primaryCode, int x, int y) {
		// IME_ACTION_GO: go操作，将用户带入到一个该输入框的目标的动作。确认键将不会有icon，只有label: GO
		switch (primaryCode) {
		case KeyBoardID.CODE_DELETE:
			break;
		// IME_ACTION_NEXT: next操作，将用户带入到该文本框的写一个输入框中。
		case KeyBoardID.CODE_ACTION_NEXT:
			mConnection.performEditorAction(EditorInfo.IME_ACTION_NEXT);
			break;
		case KeyBoardID.CODE_ACTION_ENTER:
			break;
		}

	}

}
