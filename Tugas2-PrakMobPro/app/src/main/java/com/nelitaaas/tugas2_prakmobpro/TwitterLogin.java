package com.nelitaaas.tugas2_prakmobpro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by nelitaaas on 29/09/16.
 */
public class TwitterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_twitter);

        WebView browser = (WebView) findViewById(R.id.wvTwitter);
        WebSettings settings = browser.getSettings();
        settings.setJavaScriptEnabled(true);

        browser.setWebChromeClient(new WebChromeClient());
        browser.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        browser.loadUrl("https://twitter.com/login");
    }
}
