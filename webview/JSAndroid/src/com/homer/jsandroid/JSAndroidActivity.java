package com.homer.jsandroid;

import java.net.URLEncoder;
import java.text.RuleBasedCollator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class JSAndroidActivity extends Activity {
	
	private Activity mActivity = null;
	private WebView mWebView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mActivity = this;
		
		showWebView();
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void showWebView(){		// webView‰∏éjs‰∫§‰∫í‰ª£Á†Å
		try {
			mWebView = new WebView(this);
			setContentView(mWebView);
			
			mWebView.requestFocus();
			
			mWebView.setWebChromeClient(new WebChromeClient(){
				@Override
				public void onProgressChanged(WebView view, int progress){
					JSAndroidActivity.this.setTitle("Loading...");
					JSAndroidActivity.this.setProgress(progress);
					
					if(progress >= 80) {
						JSAndroidActivity.this.setTitle("JsAndroid Test");
					}
				}
			});
			
			mWebView.setOnKeyListener(new View.OnKeyListener() {		// webview can go back
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					if(keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
						mWebView.goBack();
						return true;
					}
					return false;
				}
			});
			
			WebSettings webSettings = mWebView.getSettings();
			webSettings.setJavaScriptEnabled(true);
			webSettings.setDefaultTextEncodingName("utf-8");

			mWebView.addJavascriptInterface(getHtmlObject(), "jsObj");
			//mWebView.loadUrl("http://192.168.4.107/index.html");
			//mWebView.loadUrl("http://192.168.4.107:8080/vsg-portal-root-war/index_mock.html?deviceId=00:01:02:03:04:05&type=plain");
			//mWebView.loadUrl("file:///android_asset/index.html");
			
			
			//load image in webview
			//mWebView.loadUrl("file:///android_asset/testimage.png"); 
			
		
			
			
			//loadData and loadDataWithBaseURL  «º”‘ÿhtml ∆¨∂Œ
			StringBuilder sb = new StringBuilder();
			sb.append("<HTML><HEAD><LINK href=\"page.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD><body>");
			sb.append("<input type='button' value='HtmlcallJava' onclick='alert(\"click ed\")' />");
			sb.append("<input id=\"id_input\" style=\"width: 90"+URLEncoder.encode("%")+"\" type=\"text\" value=\"null\" /> />");
			sb.append("</body></HTML>");
			
			//String html = URLEncoder.encode(sb.toString());
			String html = sb.toString();
			mWebView.loadDataWithBaseURL( "file:///android_asset/", html, "text/html", "utf-8", null );
			
			//mWebView.loadData(html, "text/html", "utf-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Object getHtmlObject(){
		Object insertObj = new Object(){
			public String HtmlcallJava(){
				return "Html call Java";
			}
			
			public String HtmlcallJava2(final String param){
				return "Html call Java : " + param;
			}
			
			public void JavacallHtml(){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mWebView.loadUrl("javascript: showFromHtml()");
						Toast.makeText(JSAndroidActivity.this, "clickBtn", Toast.LENGTH_SHORT).show();
					}
				});
			}
			
			public void JavacallHtml2(){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mWebView.loadUrl("javascript: showFromHtml2('IT-homer blog')");
						Toast.makeText(JSAndroidActivity.this, "clickBtn2", Toast.LENGTH_SHORT).show();
					}
				});
			}
		};
		
		return insertObj;
	}

	
}
