package org.ieee.connected.learning.modules;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.ieee.connected.learning.R;


/***
 *  Created by Sangram on 20/3/15.
 */
public class DigitalToolbox extends FrameLayout{
    private final FrameLayout mRootView;
        ProgressBar progressBar;
        public DigitalToolbox(Context context) {
            this(context, null);
        }

        public DigitalToolbox(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public DigitalToolbox(final Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            LayoutInflater inflater = LayoutInflater.from(context);
            mRootView = (FrameLayout) inflater.inflate(R.layout.digitaltoolbox, this, false);
            WebView webView = (WebView)mRootView.findViewById(R.id.webView);
            webView.getSettings().setAllowFileAccess( true );
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            webView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
            webView.setWebViewClient(new MyWebViewClient());
            Toast.makeText(context, " Loading... ", Toast.LENGTH_LONG).show();
            webView.loadUrl("http://m.ieee.org/publications_standards/publications/authors/authors_journals.html");
            webView.setDownloadListener(new DownloadListener() {
                public void onDownloadStart(String url, String userAgent,
                                            String contentDisposition, String mimetype,
                                            long contentLength) {

                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    getContext().startActivity(intent);
                }
            });
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
