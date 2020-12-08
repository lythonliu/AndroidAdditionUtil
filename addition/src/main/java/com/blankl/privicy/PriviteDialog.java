package com.blankl.privicy;

import android.app.Dialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;


import com.bankl.addition.R;
import com.bankl.addition.databinding.DialogPriviteBinding;
import com.blankj.utilcode.util.SPUtils;


public class PriviteDialog extends Dialog {
    private final Context mContext;
    private WebViewDialog mWebViewDialog;
    private DialogPriviteBinding bind;

    public PriviteDialog(Context context, int dialog, String s1, String s2) {
        super(context, dialog);
        SPUtils.getInstance().put(C.S.FILE_AGREEMENT, s1);
        SPUtils.getInstance().put(C.S.FILE_PRIVACY, s2);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_privite);
        bind = DataBindingUtil.bind(findViewById(R.id.root));
        SpannableString before = new SpannableString(getContext().getString(R.string.用户服务协议前));
        SpannableString user = new SpannableString(getContext().getString(R.string.用户服务协议));
        SpannableString and = new SpannableString(getContext().getString(R.string.和));
        SpannableString privacy = new SpannableString(getContext().getString(R.string.隐私政策));
        SpannableString after = new SpannableString(getContext().getString(R.string.用户服务协议后));
        setCancelable(false);
        user.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String string = SPUtils.getInstance().getString(C.S.FILE_AGREEMENT);
                if (string != null) {
                    if (string.startsWith("file://")) {
                        CommonWebActivity.start(getContext(), SPUtils.getInstance().getString(C.S.FILE_AGREEMENT), "用户服务协议", true);
                    } else if (string.startsWith("http")) {
                        CommonWebActivity.start(getContext(), SPUtils.getInstance().getString(C.S.FILE_AGREEMENT), "用户服务协议", true);
                    }
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getContext().getResources().getColor(R.color.orange));
            }
        }, 0, user.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        privacy.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String string = SPUtils.getInstance().getString(C.S.FILE_PRIVACY);
                if (string != null) {
                    if (string.startsWith("file://")) {
                        CommonWebActivity.start(getContext(), SPUtils.getInstance().getString(C.S.FILE_PRIVACY), "隐私政策", true);
                    } else if (string.startsWith("http")) {
                        CommonWebActivity.start(getContext(), SPUtils.getInstance().getString(C.S.FILE_PRIVACY), "隐私政策", true);
                    }
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getContext().getResources().getColor(R.color.orange));
            }
        }, 0, privacy.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bind.tvContent.append(before);
        bind.tvContent.append(user);
        bind.tvContent.append(and);
        bind.tvContent.append(privacy);
        bind.tvContent.append(after);
        bind.tvContent.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void show(OnBind bind1){
        super.show();
        if (bind1!=null)bind1.onBind(bind);
    }

    public interface OnBind{
        void onBind(DialogPriviteBinding bind);
    }

    private void showAgreementDialog(String url, String title) {
        if (mWebViewDialog == null) {
            mWebViewDialog = new WebViewDialog(getContext(), R.style.dialog);
            mWebViewDialog.show(url, title);
        }
        mWebViewDialog.show(url, title);
    }

}