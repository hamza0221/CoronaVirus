package com.training.coronavirus;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Singleton {

    public static WebView myWebView;
    public static WebView myWebViewInformation;
    public static WebView myWebViewPrevention;


   @SuppressLint("SetJavaScriptEnabled")
   public static void setUpWebView(){
       myWebView.setVisibility(View.GONE);

       myWebView.getSettings().setJavaScriptEnabled(true);
       myWebView.loadUrl("https://elaph.com/coronavirus-statistics.html");
       myWebView.setVisibility(View.GONE);
       WebViewClient webViewClient=new WebViewClient(){
           @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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


    public static void setUpWebViewInformation(){

        myWebViewInformation.setVisibility(View.GONE);
        myWebViewInformation.getSettings().setJavaScriptEnabled(true);
        myWebViewInformation.loadUrl("https://www.who.int/ar/emergencies/diseases/novel-coronavirus-2019/advice-for-public/q-a-coronaviruses");
     //   myWebView.setVisibility(View.GONE);
        WebViewClient webViewClient=new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if(request.getUrl().toString().contains("googlesyndication"))
                    return new WebResourceResponse("text/javascript", "UTF-8", null);
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onLoadResource(WebView view, String url) {

                String script= "$(\"header\").hide(); $(\"ul\").hide();$(\"a\").hide();$(\"iframe\").hide();$(\"#PageContent_C002_Col00\").hide();$('strong:contains(\"روابط ذات الصلة\")').html('');" +
                        "$(\"footer\").hide();$( \"div\" ).each(function( index,element ) {switch($(element).attr('class')){case(\"breaking__wrapper\"):case(\"also-like mt-30\"):case(\"col-lg-12\"):case(\"img_ad \"):case(\"i-amphtml-layout-fixed\"):case(\"also-like mt-30\"):\n" +
                        "$(element).hide();   break;}});";


                myWebViewInformation.evaluateJavascript(script, null);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                myWebViewInformation.setVisibility(View.VISIBLE);
          //      myWebView.setVisibility(View.VISIBLE);


            }
        };

        myWebViewInformation.setWebViewClient(webViewClient);


    }



    public static void setUpWebViewPrevention(){
        myWebViewPrevention.setVisibility(View.GONE);

        myWebViewPrevention.getSettings().setJavaScriptEnabled(true);
        myWebViewPrevention.loadUrl("https://www.youm7.com/story/2020/3/15/%D9%88%D8%B2%D8%A7%D8%B1%D8%A9-%D8%A7%D9%84%D8%B5%D8%AD%D8%A9-%D8%AA%D9%83%D8%B4%D9%81-6-%D8%B7%D8%B1%D9%82-%D9%84%D9%84%D9%88%D9%82%D8%A7%D9%8A%D8%A9-%D9%85%D9%86-%D9%83%D9%88%D8%B1%D9%88%D9%86%D8%A7-%D8%AA%D8%B9%D8%B1%D9%81-%D8%B9%D9%84%D9%8A%D9%87%D8%A7/4672356");
        //   myWebView.setVisibility(View.GONE);
        WebViewClient webViewClient=new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if(request.getUrl().toString().contains("googlesyndication"))
                    return new WebResourceResponse("text/javascript", "UTF-8", null);
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                String script= "$(\"header\").hide(); $(\"ul\").hide();$(\"a\").hide();$(\"iframe\").hide();$(\"#PageContent_C002_Col00\").hide();$('strong:contains(\"روابط ذات الصلة\")').html('');\n" +
                        "    $('div[dir=\"auto\"]').hide();                   \n" +
                        " $(\"footer\").hide();\n" +
                        "\n" +
                        "\n" +
                        "\t\t\t\t\t \n" +
                        "\n" +
                        "\n" +
                        "\t\t\t\t\t $( \"div\" ).each(function( index,element ) {switch($(element).attr('class')){\n" +
                        "\t\t\t\t\t\t case(\"MainContentBlock\"):$(element).find('p').first().hide();break;\n" +
                        "\t\t\t\t\t\t case(\"breaking__wrapper\"):case(\"also-like mt-30\"):case(\"col-lg-12\"):case(\"img_ad \"):case(\"i-amphtml-layout-fixed\"):case(\"also-like mt-30\"):case(\"FullTrend\"):case(\"smallAdBlock1\"):case(\"sameAdBlock1\"):case(\"ByADateBlock\"):case(\"MostViewedNewBlock\"):\n" +
                        "                    case(\"Breadcumb\"):    case(\"After_F_Paragraph\"): case(\"WedjetsBlocksInDetails\"): case(\"sameAdBlock2\"): case(\"AdsNoSize\"): case(\"ADFParagraph\"): case(\"SocialGrayBlock\"):case(\"ShortUrl\"):case(\"col-lg-12\"):case(\"homeSection \"):\n" +
                        "    case(\"SamebarBG\"):     case(\"component\"):       case(\"col-lg-12 homeSection padding0\"):           case(\"RelatedNews\"): case(\"mobileAdvFooter\"): case(\"OneRelatedBlock\"): case(\"AdsNoSize sameAdBlock2\"): case(\"col-lg-12 homeSection RelatedNews\"):case(\"row margin0 new-input-comment\"):case(\"col-lg-12 homeSection RelatedNews\"):case(\"col-xs-12 form-group text-center\"):case(\"form-group form-row col-xs-12\"):\n" +
                        "case(\"selectionShareable\"):\tcase(\"TaGs\"):\tcase(\"footer-menu row margin0\"):case(\"BottomFotter\"):\t\t\t\t\t\n" +
                        "\n" +
                        "\t\t\t\t\t$(element).hide();   break;}});\n" +
                        "\t\t\t\t\t\n" +
                        "\n" +
                        "\t\t\t\t\t$( \"p\" ).each(function( index,element ) {\n" +
                        "\t\t\t\t\t\tif(index==1)\n" +
                        "\t\t\t\t\t\t\t  $(element).hide();\n" +
                        "\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\tswitch($(element).attr('class')){ case(\"coloredquoute\"):case(\"TrPlusYBar\"): case(\"selectionShareable\"):  $(element).hide();  break;}});\n" +
                        "\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t$( \"h2\" ).each(function( index,element ) {if(index==0|| index==1)    $(element).hide()});\n" +
                        "\t\t\t\t\t\t\t$( \"h1\" ).each(function( index,element ) {if(index==0|| index==1)    $(element).hide()});\n" +
                        "\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t \n";


                myWebViewPrevention.evaluateJavascript(script, null);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                      myWebViewPrevention.setVisibility(View.VISIBLE);


            }
        };

        myWebViewPrevention.setWebViewClient(webViewClient);


    }


}
