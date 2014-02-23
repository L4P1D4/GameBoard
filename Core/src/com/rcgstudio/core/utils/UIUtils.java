package com.rcgstudio.core.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class UIUtils {
	public static int dpToPixels(Context context, int valueDP) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) ((valueDP * scale) + 0.5f);
	}
	
	public static int spToPixels(Context context, int valueSP) {
		float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) ((valueSP * scale) + 0.5f);
	}
	
	public static void closeKeyboard(Context context, View view) {
		try {
			InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		} catch (Exception ex) {
		}
	}
}
