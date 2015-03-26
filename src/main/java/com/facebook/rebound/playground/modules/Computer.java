package com.facebook.rebound.playground.modules;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.rebound.playground.R;


/***
 *  Created by Sangram on 20/3/15.
 */
public class Computer extends FrameLayout {

        private final FrameLayout mRootView;
        ProgressBar progressBar;
        public Computer(Context context) {
            this(context, null);
        }

        public Computer(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public Computer(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            LayoutInflater inflater = LayoutInflater.from(context);
            mRootView = (FrameLayout) inflater.inflate(R.layout.computer, this, false);
            WebView webView = (WebView)mRootView.findViewById(R.id.webView);
            CookieManager.getInstance().setAcceptCookie(true);
            webView.getSettings().setAllowFileAccess( true );
            webView.getSettings().setAppCacheEnabled( true );
            webView.getSettings().setJavaScriptEnabled( true );
            webView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );
            webView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
            webView.setWebViewClient(new MyWebViewClient());
            Toast.makeText(context, " Loading... ", Toast.LENGTH_LONG).show();
            webView.loadUrl("http://www.computer.org/web/publications/authors");
            webView.setOnKeyListener(new OnKeyListener()
            {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event)
                {
                    if(event.getAction() == KeyEvent.ACTION_DOWN)
                    {
                        WebView webView = (WebView) v;

                        switch(keyCode)
                        {
                            case KeyEvent.KEYCODE_BACK:
                                if(webView.canGoBack())
                                {
                                    webView.goBack();
                                    return true;
                                }
                                break;
                        }
                    }

                    return false;
                }
            });
            addView(mRootView);
        }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            view.loadUrl("about:blank");
            Toast.makeText(getContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            progressBar = (ProgressBar) mRootView.findViewById(R.id.progressBar);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            progressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }
    }

  }
