package english.dictionary.app.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

@SuppressLint("Instantiatable")
public class NewWebView extends WebView {

    @SuppressLint("Instantiatable")
    public NewWebView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public void enableCrossdomain41() {
        try {
            Field webviewclassic_field = WebView.class.getDeclaredField("mProvider");
            webviewclassic_field.setAccessible(true);
            Object webviewclassic = webviewclassic_field.get(this);
            Field webviewcore_field = webviewclassic.getClass().getDeclaredField("mWebViewCore");
            webviewcore_field.setAccessible(true);
            Object mWebViewCore = webviewcore_field.get(webviewclassic);
            Field nativeclass_field = webviewclassic.getClass().getDeclaredField("mNativeClass");
            nativeclass_field.setAccessible(true);
            Object mNativeClass = nativeclass_field.get(webviewclassic);

            Method method = mWebViewCore.getClass().getDeclaredMethod("nativeRegisterURLSchemeAsLocal", new Class[]{int.class, String.class});
            method.setAccessible(true);
            method.invoke(mWebViewCore, mNativeClass, "http");
            method.invoke(mWebViewCore, mNativeClass, "https");
        } catch (Exception e) {
            Log.d("wokao", "enablecrossdomain error");
            e.printStackTrace();
        }
    }
}
