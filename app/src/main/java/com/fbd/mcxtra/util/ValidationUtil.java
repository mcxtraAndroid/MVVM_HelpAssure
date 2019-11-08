package com.fbd.mcxtra.util;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by Gourav on 30-04-2017.
 */

public class ValidationUtil {
    ValidationUtil binding;

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void setBinding(ValidationUtil binding) {
        this.binding = binding;
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
