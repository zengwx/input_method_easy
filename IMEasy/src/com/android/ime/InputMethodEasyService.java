package com.android.ime;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import com.android.ime.interfaces.KeyboardActionListener;
import com.android.ime.keyboard.KeyBoard;

/**
 * 	通过调用系统的onEvaluateInputViewShow()来测试是否需要显示输入视图
 *  输入法状态改变的时候，需要调用updateInputViewShown()来重新估计一下
 */
public class InputMethodEasyService extends InputMethodService implements KeyboardActionListener {
	
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
	 */
	
	/**
	 * 第1步
	 */
	@Override
	public void onCreate() {
		super.onCreate();
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
		super.onStartInput(attribute, restarting);
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
//		mConnection = getCurrentInputConnection();
		return view;
	}

	/**
	 * 第5步 加载候选键盘布局.<p>
	 * 只不过创建的是候选框的视图<p>
	 */
	@Override
	public View onCreateCandidatesView() {
		View view = getLayoutInflater().inflate(R.layout.cand_keyboard_view,
				null);
		view.findViewById(R.id.button1).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getCurrentInputConnection();
					}
				});
		return view;
	}
	
	/**
	 * 第6步<p>
	 * 在输入视图被显示并且在一个新的输入框中<p>
	 * 输入已经开始的时候调用。<p>
	 */
	@Override
	public void onStartInputView(EditorInfo info, boolean restarting) {
		super.onStartInputView(info, restarting);
	}
	
	/**
	 * 它在另外的客户端和该输入法连接时调用<p>
	 */
	@Override
	public void onBindInput() {
		super.onBindInput();
	}
	
	@Override
	public void onCodeInput(int primaryCode, int x, int y) {
		 // IME_ACTION_GO: go操作，将用户带入到一个该输入框的目标的动作。确认键将不会有icon，只有label: GO
		
		  switch (primaryCode) {
	        case KeyBoard.CODE_DELETE:
	        	break;
	        // IME_ACTION_NEXT: next操作，将用户带入到该文本框的写一个输入框中。
	        case KeyBoard.CODE_ACTION_NEXT:
	        	// EditorInfo.IME_ACTION_NEXT
	        	mConnection.performEditorAction(EditorInfo.IME_ACTION_NEXT);
	        	break;
	        case KeyBoard.CODE_ACTION_ENTER:
	        	break;
		  }
	}

}
