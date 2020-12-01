package com.blankl.privicy;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;

import androidx.databinding.DataBindingUtil;

import com.bankl.addition.R;
import com.bankl.addition.databinding.DialogWebViewBinding;

public class WebViewDialog extends Dialog {
    DialogWebViewBinding bind;

    public WebViewDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_web_view);
        bind = DataBindingUtil.bind(findViewById(R.id.root));
        WebSettings settings = bind.webView.getSettings();
        settings.setBlockNetworkImage(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        settings.setDefaultFontSize(16);
        bind.ivBack.setOnClickListener(v -> dismiss());
    }

    public void show(String url, String title) {
        show();
        bind.webView.loadUrl(url);
        bind.tvTitle.setText(title);

    }

}