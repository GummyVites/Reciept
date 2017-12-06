package com.example.kevinlee.buttonboy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class venmoPay extends AppCompatActivity {

    WebView wv;

    @Override
    public void onBackPressed(){
        if(wv.canGoBack()){
            wv.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venmo_pay);

        wv = (WebView) findViewById(R.id.wv);
        //enable javascript
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setFocusable(true);
        wv.setFocusableInTouchMode(true);
        //Set Render Priority
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.getSettings().setDomStorageEnabled(true);
        wv.getSettings().setDatabaseEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String url = "https://venmo.com/?txn=charge&amount=24.25&note=for+dinner&recipients=hamilton@venmo.com,646.863.9557,john";
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient());
    }
}
