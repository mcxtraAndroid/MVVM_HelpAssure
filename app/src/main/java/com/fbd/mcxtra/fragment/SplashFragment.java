package com.fbd.mcxtra.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fbd.mcxtra.R;
import com.fbd.mcxtra.base.activity.BaseActivity;
import com.fbd.mcxtra.base.fragment.BaseFragment;

public class SplashFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, null);
        return view;
    }

    public static void addFragment(BaseActivity activity) {
        activity.replaceAsFirstFragment(new SplashFragment());
    }
}
