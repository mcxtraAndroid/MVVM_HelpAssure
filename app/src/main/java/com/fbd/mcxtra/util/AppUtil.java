package com.fbd.mcxtra.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Toast;

import com.fbd.mcxtra.base.activity.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Gourav on 15-06-2017.
 */

public class AppUtil {
    public static void showToast(BaseActivity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    public static File saveToInternalStorage(BaseActivity activity, Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(activity);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, System.currentTimeMillis() + ".png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath;
    }

    public static ProgressDialog progressDialog;

    public static void showProgress(Context context) {
        if (progressDialog == null)
            progressDialog = new ProgressDialog(context);
        progressDialog.show();
    }

    public static void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static void callIntent(BaseActivity activity, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        activity.startActivity(intent);
    }
}
