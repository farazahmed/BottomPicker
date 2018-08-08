package com.faraz.bottompicker;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.NumberPicker;


/**
 * Created by Faraz Ahmed on 8/28/2017.
 * Next Generation Picker
 */

public class NextGenPicker extends LinearLayout implements NextGenViewOperations {

    public static final int PICKER_NONE = 0;
    private static final long WAIT_TIME = 1000;
    private ViewGroup parentView;
    private CallbackNextGenPicker callBack;
    private int type = 0;
    Context context;
    String[] values;
    private int index;
    private NextGenPresenterImpl presenter;
    LinearLayout rootView;
    private NumberPicker numberPicker;
    private Animation animSlideInBottom;
    private Animation animSlideOutBottom;
    private boolean showAnimations = true;
    private Handler handler;


    public NextGenPicker(Context context, CallbackNextGenPicker callBack, ViewGroup parentView) {

        super(context);
        this.context = context;
        this.callBack = callBack;
        this.parentView = parentView;
        initViews();
        initPresenters();
    }


    public NextGenPicker(Context context) {
        super(context);
        initViews();
    }

    public NextGenPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public NextGenPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }


    private void initViews() {
        try {
            handler = new Handler();
            parentView.addView(this);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = null;
            if (inflater != null) {
                view = inflater.inflate(R.layout.layout_generic_single_picker, this, true);
                rootView = (LinearLayout) view.findViewById(R.id.genericSinglePicker);
                numberPicker = (NumberPicker) view.findViewById(R.id.picker_generic);
            }
            rootView.setVisibility(VISIBLE);
            animSlideInBottom = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            animSlideOutBottom = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showValues(final String[] values) {

        if (values != null && values.length > 0) {
            index = 0;
            numberPicker.setValue(index);
            numberPicker.setDisplayedValues(null);
            numberPicker.setMinValue(0);
            numberPicker.setSaveFromParentEnabled(false);
            numberPicker.setSaveEnabled(true);
            numberPicker.setMaxValue(values.length - 1);
            numberPicker.setDisplayedValues(values);
            numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            numberPicker.setWrapSelectorWheel(false);
            numberPicker.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    hidePicker();
                }
            });

            numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
                @Override
                public void onScrollStateChange(NumberPicker view, int scrollState) {
                    if (scrollState == SCROLL_STATE_IDLE) {
                        startWaitTimer();
                        presenter.sendOnClickListener(getCallBack(), index, values[index], getType());
                    }
                }
            });

            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    index = newVal;
                    presenter.sendOnValueChangeListener(getCallBack(), index, values[index], getType());
                }
            });
        }
    }

    @Override
    public void showValues(final String[] values, int selectedIndex) {
        if (values != null && values.length > 0) {
            index = selectedIndex;
            numberPicker.setValue(index);
            numberPicker.setDisplayedValues(null);
            numberPicker.setMinValue(0);
            numberPicker.setSaveFromParentEnabled(false);
            numberPicker.setSaveEnabled(true);
            numberPicker.setMaxValue(values.length - 1);
            numberPicker.setDisplayedValues(values);
            numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            numberPicker.setWrapSelectorWheel(false);
            //presenter.sendOnClickListener(getCallBack(), index, values[index], getType());
            numberPicker.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    hidePicker();
                    presenter.sendOnClickListener(getCallBack(), index, values[index], getType());
                }
            });

            numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
                @Override
                public void onScrollStateChange(NumberPicker view, int scrollState) {
                    if (scrollState == SCROLL_STATE_IDLE) {
                        startWaitTimer();
                        presenter.sendOnClickListener(getCallBack(), index, values[index], getType());
                    }
                }
            });

            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    index = newVal;
                    presenter.sendOnValueChangeListener(getCallBack(), index, values[index], getType());
                }
            });
        }
    }

    @Override
    public void showPicker() {
        if (parentView != null && parentView.getVisibility() == GONE) {
            if (index == 0) {
                presenter.getValues();
            } else {
                presenter.getValuesWithIndex(index);
            }
            parentView.setVisibility(View.VISIBLE);
            if (isShowAnimations()) {
                parentView.startAnimation(animSlideInBottom);
            }
            presenter.sendOnShowPicker(getCallBack(), type);
        }
    }

    @Override
    public void hidePicker() {
        try {
            if (parentView != null && parentView.getVisibility() == VISIBLE) {
                parentView.setVisibility(View.GONE);
                if (isShowAnimations()) {
                    parentView.startAnimation(animSlideOutBottom);
                }
                presenter.sendOnHidePicker(getCallBack(), type);
                setType(PICKER_NONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initPresenters() {
        presenter = new NextGenPresenterImpl(this, values);
        presenter.getValues();
    }

    public CallbackNextGenPicker getCallBack() {
        return callBack;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setValues(String[] values) {
        this.values = values;
        this.presenter.setValues(values);
        this.index = 0;
    }

    public void setValuesWithIndex(String[] values, int index) {
        this.values = values;
        this.index = index;
        this.presenter.setValues(values);
    }

    private void startWaitTimer() {
        try {
            // VALIDATE TIME
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    hidePicker();
                }
            };
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(runnable, WAIT_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isShowAnimations() {
        return showAnimations;
    }

}
