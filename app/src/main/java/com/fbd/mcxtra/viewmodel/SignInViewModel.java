package com.fbd.mcxtra.viewmodel;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.fbd.mcxtra.R;
import com.fbd.mcxtra.api.ApiCallback;
import com.fbd.mcxtra.api.SignInApi;
import com.fbd.mcxtra.base.activity.BaseActivity;
import com.fbd.mcxtra.util.AppUtil;
import com.fbd.mcxtra.util.BindableBoolean;
import com.fbd.mcxtra.util.BindableString;

public class SignInViewModel {
    BaseActivity activity;
    public BindableString mobileNumber = new BindableString();
    public BindableString countryCode = new BindableString();


    // public BindableString password = new BindableString();
    public BindableBoolean isApiRunning = new BindableBoolean();
    public boolean loginExecuted;

    public SignInViewModel(BaseActivity activity) {
        this.activity = activity;
    }



    public void onSignIn(View view) {

             isApiRunning.set(true);
            //   SignInApi api = new SignInApi(mobileNumber.get(), password.get());

            SignInApi api = new SignInApi("9664286017", "91");

            api.call(activity, new ApiCallback() {
                @Override
                public void onResponse(Object o) {

                    isApiRunning.set(false);
                }


                @Override
                public void onError(String error) {
                    isApiRunning.set(false);
                    AppUtil.showToast(activity, error);
                }
            });
        }


    public static void mobileNoValidator(EditText editText, String password) {
        // ignore infinite loops
        int minimumLength = 7;
        if (TextUtils.isEmpty(password)) {
            editText.setError(null);
            return;
        }
        if (editText.getText().toString().length() < minimumLength) {
            editText.setError("Password must be minimum " + minimumLength + " length");
        } else editText.setError(null);
    }



}
