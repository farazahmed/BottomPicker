package com.faraz.bottompicker;

/**
 * Created by Faraz Ahmed on 8/28/2017.
 */

public interface NextGenPresenter {

    public void getValues();
    public void getValuesWithIndex(int index);
    public void sendOnClickListener(CallbackNextGenPicker callBack, int index, String value, int type);
    public void sendOnValueChangeListener(CallbackNextGenPicker callBack, int index, String value, int type);
    public void sendOnShowPicker(CallbackNextGenPicker callBack, int type);
    public void sendOnHidePicker(CallbackNextGenPicker callBack, int type);


}
