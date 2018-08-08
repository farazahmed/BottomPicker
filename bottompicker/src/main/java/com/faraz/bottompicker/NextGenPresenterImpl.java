package com.faraz.bottompicker;

/**
 * Created by Faraz Ahmed on 8/28/2017.
 */

public class NextGenPresenterImpl implements NextGenPresenter {

    NextGenViewOperations viewOperations;

    String[] values;

    public NextGenPresenterImpl(NextGenViewOperations viewOperations, String[] values) {
        this.viewOperations = viewOperations;
        this.values = values;
    }

    @Override
    public void getValues() {
        viewOperations.showValues(values);
    }

    @Override
    public void getValuesWithIndex(int index) {
        if (values.length > index) {
            viewOperations.showValues(values, index);
        } else {
            viewOperations.showValues(values);
        }
    }

    @Override
    public void sendOnClickListener(CallbackNextGenPicker callBack, int index, String value, int type) {
        if (callBack != null) {
            callBack.onClickListener(index, value, type);
        }
    }

    @Override
    public void sendOnValueChangeListener(CallbackNextGenPicker callBack, int index, String value, int type) {
        if (callBack != null && value != null) {
            callBack.onValueChangedListener(index, value, type);
        }
    }

    @Override
    public void sendOnShowPicker(CallbackNextGenPicker callBack, int type) {
        if (callBack != null) {
            callBack.onShowPicker(type);
        }
    }

    @Override
    public void sendOnHidePicker(CallbackNextGenPicker callBack, int type) {
        if (callBack != null) {
            callBack.onHidePicker(type);
        }
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}
