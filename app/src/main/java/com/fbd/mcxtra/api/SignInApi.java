package com.fbd.mcxtra.api;

import android.content.Context;

import com.fbd.mcxtra.util.JSONKeys;
import com.fbd.mcxtra.util.URLs;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInApi {
    JSONObject param = new JSONObject();

    public SignInApi(String mobile_no, String ext) {
        try {
            param.put(JSONKeys.code, mobile_no);
            param.put(JSONKeys.price, ext);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void call(Context context, ApiCallback callback) {
        ApiHelper.getInstance(context).doPostRequest(URLs.SIGN_IN, null, param, callback);
    }
}
