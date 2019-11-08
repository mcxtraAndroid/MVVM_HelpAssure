package com.fbd.mcxtra.api;

/**
 * Created by Gourav on 08-02-2017.
 */

public interface ApiCallback {
    void onResponse(Object o);
    void onError(String error);
}
