package com.faraz.bottompicker;

/**
 * Created by Faraz Ahmed on 8/28/2017.
 */

public interface NextGenViewOperations extends BaseViewOperations {

    public void showValues(String[] values);
    public void showValues(String[] values, int selectedIndex);
    public void showPicker();
    public void hidePicker();

}
