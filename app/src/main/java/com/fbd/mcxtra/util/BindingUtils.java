package com.fbd.mcxtra.util;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TimePicker;

import com.fbd.mcxtra.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

/**
 * Created by Gourav on 09-02-2017.
 */

public class BindingUtils {

    @BindingConversion
    public static String convertBindableToString(BindableString bindableString) {
        return bindableString.get();
    }


    @BindingAdapter({"app:binding"})
    public static void bindEditText(EditText view, final BindableString bindableString) {
        Pair<BindableString, TextWatcherAdapter> pair = (Pair) view.getTag(R.id.bound_observable);
        if (pair == null || pair.first != bindableString) {
            if (pair != null) {
                view.removeTextChangedListener(pair.second);
            }
            TextWatcherAdapter watcher = new TextWatcherAdapter() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bindableString.set(s.toString());
                }
            };
            view.setTag(R.id.bound_observable, new Pair<>(bindableString, watcher));
            view.addTextChangedListener(watcher);
        }
        String newValue = bindableString.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }

    @BindingAdapter({"app:bindVisibility"})
    public static void bindVisibility(View view, final BindableBoolean bindableBoolean) {
        if (bindableBoolean.get()) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({"app:bindVisibility"})
    public static void bindVisibility(View view, boolean bindableBoolean) {
        if (bindableBoolean) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("setRecyclerViewAdapter")
    public static void setRecyclerViewAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setHasFixedSize(true);
        view.setItemViewCacheSize(100);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(mLayoutManager);
        view.setAdapter(adapter);
    }

    @BindingAdapter("setRecyclerViewGridAdapter")
    public static void setRecyclerViewGridAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        view.setLayoutManager(mLayoutManager);
        view.setAdapter(adapter);
    }

    @BindingAdapter({"android:src"})
    public static void bindImageRes(ImageView view, int imgRes) {
        view.setImageResource(imgRes);
    }

    @BindingAdapter({"android:src"})
    public static void bindImageRes(ImageView view, BindableString imgRes) {
        view.setImageResource(Integer.parseInt(imgRes.get()));
    }

    @BindingAdapter({"listener", "status"})
    public static void listener(SwitchCompat view, CompoundButton.OnCheckedChangeListener checkedChangeListener, BindableBoolean bindableBoolean) {
        view.setOnCheckedChangeListener(checkedChangeListener);
        view.setChecked(bindableBoolean.get());
    }

    @BindingAdapter({"loadUrl"})
    public static void loadUrl(ImageView view, BindableString imgUrl) {
        if (imgUrl.get().length() > 0) {
            view.setTag(imgUrl.get());
            Picasso.with(view.getContext()).load(imgUrl.get()).placeholder(R.color.colorAccent).into(view);
        }
    }

    @BindingAdapter("setImageRecyclerViewAdapter")
    public static void setImageRecyclerViewAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        view.setLayoutManager(mLayoutManager);
        view.setAdapter(adapter);
    }

    @BindingAdapter({"app:bindTextWatcher"})
    public static void bindTextWatcher(EditText view, TextWatcher textWatcher) {
        view.addTextChangedListener(textWatcher);
    }

    @BindingAdapter({"app:setRadioGroupListener"})
    public static void setRadioGroupListener(RadioGroup v, RadioGroup.OnCheckedChangeListener onCheckedChangeListener) {
        v.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @BindingAdapter({"android:onDateChanged"})
    public static void setDate(DatePicker view, DatePicker.OnDateChangedListener listener) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        view.init(year, month, day, listener);
        view.updateDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        view.setMinDate(calendar.getTimeInMillis() - 1000);
    }

    @BindingAdapter({"android:OnTimeChangedListener"})
    public static void setTime(TimePicker view, TimePicker.OnTimeChangedListener listener) {
        view.setOnTimeChangedListener(listener);
    }

    @BindingAdapter({"setAdapter"})
    public static void setAdapter(ViewPager viewPager, FragmentStatePagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({"setExpandableAdapter", "setOnChildClickListener"})
    public static void setExpandableAdapter(ExpandableListView expandableListView, BaseExpandableListAdapter adapter, ExpandableListView.OnChildClickListener onChildClickListener) {
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(onChildClickListener);
    }

    @BindingAdapter({"app:setSpinnerAdapter"})
    public static void setSpinnerAdapter(AppCompatSpinner spinner, ArrayAdapter<String> adapter) {
        spinner.setAdapter(adapter);
    }

    @BindingAdapter({"app:setAutoAdapter"})
    public static void setAutoAdapter(AutoCompleteTextView autoCompleteTextView, ArrayAdapter adapter) {
        autoCompleteTextView.setAdapter(adapter);
    }

    @BindingAdapter({"app:setCheckBoxListener"})
    public static void setCheckBoxListener(AppCompatCheckBox checkBox, final OnCheckListener onCheckListener) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onCheckListener.onCheckChanged(isChecked, (String) buttonView.getText());
            }
        });
    }

    @BindingAdapter({"app:setRatingBarChangeListener", "app:setRating"})
    public static void setRatingBarChangeListener(AppCompatRatingBar appCompatRatingBar, RatingBar.OnRatingBarChangeListener listener, BindableString rating) {
        appCompatRatingBar.setRating(Float.parseFloat(rating.get()));
        appCompatRatingBar.setOnRatingBarChangeListener(listener);
    }

    @BindingAdapter({"app:setMax", "app:setCompleted"})
    public static void setProgressBar(ProgressBar progressBar, BindableString max, BindableString completed) {
        progressBar.setMax(Integer.parseInt(max.get()));
        progressBar.setProgress(Integer.parseInt(completed.get()));
    }

    @BindingAdapter({"app:setOnItemLongItemClick"})
    public static void setOnItemLongItemClick(View view, View.OnLongClickListener onLongClickListener) {
        view.setOnLongClickListener(onLongClickListener);
    }
}