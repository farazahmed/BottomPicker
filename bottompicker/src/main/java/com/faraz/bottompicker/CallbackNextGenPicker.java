package com.faraz.bottompicker;

/**
 * Created by Faraz Ahmed on 8/28/2017.
 */

public interface CallbackNextGenPicker {

    public void onValueChangedListener(int index, String value, int type);
    public void onClickListener(int index, String value, int type);
    public void onShowPicker(int type);
    public void onHidePicker(int type);

}
