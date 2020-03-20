package com.training.coronavirus;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

public class Singleton {

    public static WebView myWebView;


   @SuppressLint("SetJavaScriptEnabled")
   public static void setUpWebView(){


       myWebView.getSettings().setJavaScriptEnabled(true);
       myWebView.loadUrl("https://elaph.com/coronavirus-statistics.html");
       myWebView.setVisibility(View.GONE);
       WebViewClient webViewClient=new WebViewClient(){
           @Nullable
           @Override
           public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
               if(request.getUrl().toString().contains("googlesyndication"))
                   return new WebResourceResponse("text/javascript", "UTF-8", null);
               return super.shouldInterceptRequest(view, request);
           }

           @Override
           public void onLoadResource(WebView view, String url) {
               String script= "$(\"header\").hide(); $(\"p\").hide(); $(\"ul\").hide();$(\"iframe\").hide();" +
                       "$(\"footer\").hide();$( \"div\" ).each(function( index,element ) {switch($(element).attr('class')){case(\"breaking__wrapper\"):case(\"also-like mt-30\"):case(\"col-lg-12\"):case(\"img_ad \"):case(\"i-amphtml-layout-fixed\"):case(\"also-like mt-30\"):\n" +
                       "$(element).hide();   break;}});";


               myWebView.evaluateJavascript(script, null);

           }

           @Override
           public void onPageFinished(WebView view, String url) {
               myWebView.setVisibility(View.VISIBLE);


           }
       };

       myWebView.setWebViewClient(webViewClient);


   }


}
