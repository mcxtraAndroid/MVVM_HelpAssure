package com.fbd.mcxtra.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fbd.mcxtra.R;
import com.fbd.mcxtra.base.activity.BaseActivity;
import com.fbd.mcxtra.base.fragment.BaseFragment;
import com.fbd.mcxtra.databinding.FragmentSignInBinding;
import com.fbd.mcxtra.util.ValidationUtil;
import com.fbd.mcxtra.viewmodel.SignInViewModel;

public class SignInFragment extends BaseFragment {
    SignInViewModel vm;
    FragmentSignInBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = new SignInViewModel((BaseActivity) getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sign_in, container, false);
        binding.setVm(vm);

    /*    ValidationUtil handler = new ValidationUtil();
        handler.setBinding(binding);
        binding.setHandler(handler);
*/



        return binding.getRoot();
    }

    public static void addFragment(BaseActivity activity) {
        activity.replaceAsFirstFragment(new SignInFragment());
    }
}
