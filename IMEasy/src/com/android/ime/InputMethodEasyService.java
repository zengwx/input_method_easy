package com.android.ime;

import android.content.Intent;
import android.content.IntentFilter;
import android.inputmethodservice.InputMethodService;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import com.android.ime.interfaces.KeyboardActionListener;
import com.android.ime.keyboard.KeyBoard;
import com.android.ime.keyboard.KeyBoardID;

/**
 * 	通过调用系统的onEvaluateInputViewShow()来测试是否需要显示输入视图
 *  输入法状态改变的时候，需要调用updateInputViewShown()来重新估计一下
 */
public class InputMethodEasyService extends InputMethodService implements KeyboardActionListener {
	
	private int mMode = KeyBoardID.MODE_PHONE;
	/**
	 * http://api.apkbus.com/reference/android/view/inputmethod/InputConnection.html
	 */
	private InputConnection mConnection = null;
	/*     
	 	    beginBatchEdit();
	 	    commitText(lastTwo.charAt(1) + " ", 1);
	 	    commitCorrection
            deleteSurroundingText
            endBatchEdit();
            finishComposingText();
            
            getCursorCapsMode
            getNthPreviousWord
            getTextBeforeCursor
            
            isBelatedExpectedUpdate
            isCursorTouchingWord(mCurrentSetting
            performEditorAction
            
            sendKeyEvent
            setComposingText(batchInputText, 1);
            sameAsTextBeforeCursor(
            setSelection
            
            removeTrailingSpace();
            revertDoubleSpace()) {
            revertSwapPunctuation()) {
            resetCachesUponCursorMove
            
            
            ###
            getWindow().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	 */
	
	/**
	 * 第1步
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		/* 注册输入法广播 */
		registerIMEReceiver();
	}
	
	/**
	 * 注册一些安装，卸载的广播.
	 */
	private void registerIMEReceiver() {
		final IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		filter.addAction(AudioManager.RINGER_MODE_CHANGED_ACTION);
//		registerReceiver(mReceiver, filter);
		final IntentFilter packageFilter = new IntentFilter();
		packageFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
		packageFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
//		packageFilter.addDataScheme(SCHEME_PACKAGE);
//		registerReceiver(mDictionaryPackInstallReceiver, packageFilter);
	}
	
	/**
	 * 第2步<p>
	 * 顾名思义，它在初始化界面的时候被调用，<p>
	 * 而一般是由于配置文件的更改导致该函数的执行<p>
	 */
	@Override
	public void onInitializeInterface() {
		super.onInitializeInterface();
	}
	
	/**
	 * 第3步 <p>
	 * 非常重要的一个回调，<p>
	 * 它在编辑框中用户已经开始输入的时候调用。<p>
	 * 比如，当点击一个输入框，我们需要根据这个输入框的信息，<p>
	 * 设置输入法的一些特性，这个在Sample中很有体会。<p>
	 */
	@Override
	public void onStartInput(EditorInfo attribute, boolean restarting) {
		mConnection = getCurrentInputConnection();
		
		Log.e(">>>>", ">>>onStartInput>>>>" + attribute);
		mMode = getKeyboardMode(attribute); // 获取输入法键盘类型.
		super.onStartInput(attribute, restarting);
	}
	
	/**
	 * 获取输入法键盘类型.
	 */
	public int getKeyboardMode(final EditorInfo editorInfo) {
		//
		if (editorInfo == null) {
			return KeyBoardID.MODE_TEXT;
		}
		//
		final int inputType = editorInfo.inputType;
		final int variation = inputType & InputType.TYPE_MASK_VARIATION;
		// 根据 eidttext inputtype 返回软键盘类型.
		switch (inputType & InputType.TYPE_MASK_CLASS) {
		case InputType.TYPE_CLASS_NUMBER:
			return KeyBoardID.MODE_NUMBER;
		case InputType.TYPE_CLASS_PHONE:
			return KeyBoardID.MODE_PHONE;
		case InputType.TYPE_CLASS_DATETIME: // 日期，时间.
			 switch (variation) {
             case InputType.TYPE_DATETIME_VARIATION_DATE:
                 return KeyBoardID.MODE_DATE; // 
             case InputType.TYPE_DATETIME_VARIATION_TIME:
                 return KeyBoardID.MODE_TIME;
             default: // InputType.TYPE_DATETIME_VARIATION_NORMAL
                 return KeyBoardID.MODE_DATETIME;
             }
		default:
			return KeyBoardID.MODE_TEXT;
		}
	}
	
	
	/**
	 * 第4步 加载主要键盘布局. <p>
	 * 返回一个层次性的输入视图，<p>
	 * 而且只是在这个视图第一次显示的时候被调用
	 */
	@Override
	public View onCreateInputView() {
		View view = getLayoutInflater().inflate(R.layout.main_keyboard_view,
				null);
		return view;
	}

	/**
	 * 第5步 加载候选键盘布局.<p>
	 * 只不过创建的是候选框的视图<p>
	 */
	@Override
	public View onCreateCandidatesView() {
		return super.onCreateCandidatesView();
	}
	
	/**
	 * 第6步<p>
	 * 在输入视图被显示并且在一个新的输入框中<p>
	 * 输入已经开始的时候调用。<p>
	 */
	@Override
	public void onStartInputView(EditorInfo info, boolean restarting) {
//		getWindow().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		super.onStartInputView(info, restarting);
	}
	
	/**
	 * 另外的客户端和该输入法连接时调用<p>
	 */
	@Override
	public void onBindInput() {
		super.onBindInput();
	}
	
	/**
	 * 处理键盘输入.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
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
                 onTextInput(Integer.toString(keyCode-KeyEvent.KEYCODE_NUMPAD_0));
             }
        	 return true;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onCodeInput(int primaryCode, int x, int y) {
		 // IME_ACTION_GO: go操作，将用户带入到一个该输入框的目标的动作。确认键将不会有icon，只有label: GO
		
		  switch (primaryCode) {
	        case KeyBoard.CODE_DELETE:
	        	break;
	        // IME_ACTION_NEXT: next操作，将用户带入到该文本框的写一个输入框中。
	        case KeyBoard.CODE_ACTION_NEXT:
	        	mConnection.performEditorAction(EditorInfo.IME_ACTION_NEXT);
	        	break;
	        case KeyBoard.CODE_ACTION_ENTER:
	        	break;
		  }
		  
	}

	@Override
	public void onTextInput(CharSequence text) {
		mConnection.beginBatchEdit();
		mConnection.commitText(text, 1); // 输入文本.
		mConnection.endBatchEdit();
	}

}
