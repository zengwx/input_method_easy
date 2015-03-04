package com.android.ime.keyboard;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.android.ime.R;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

/**
 * 解析一些XML布局文件.
 */
public class KeyBoardBuild {
	
	protected final Resources mResources;
	private Context mContext;
	
	public KeyBoardBuild(final Context context) {
		 mContext = context;
		 mResources = mContext.getResources();
	}
	
	/**
	 * 加载XML键盘布局.
	 */
	public void load(int xmlId) {
		final XmlResourceParser parser = mResources.getXml(xmlId);
		try {
			parseKeyboard(parser);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			parser.close();
		}
	}
	
	private void parseKeyboard(final XmlPullParser parser)
            throws XmlPullParserException, IOException {
        int event;
        while ((event = parser.next()) != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {
                final String tag = parser.getName();
                int sid = R.styleable.Keyboard_Key_keyLabel;
//                if (TAG_KEYBOARD.equals(tag)) {
//                    parseKeyboardAttributes(parser);
//                    startKeyboard();
//                    parseKeyboardContent(parser, false);
//                    break;
//                } else {
//                    throw new XmlParseUtils.IllegalStartTag(parser, TAG_KEYBOARD);
//                }
            }
        }
    }
	
}
