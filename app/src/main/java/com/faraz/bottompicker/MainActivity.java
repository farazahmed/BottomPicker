package com.faraz.bottompicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Faraz Ahmed on 8/6/2018.
 */

public class MainActivity extends AppCompatActivity implements CallbackNextGenPicker {

    private NextGenPicker nextGenPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.pickerGeneral);
        Button btnShow = findViewById(R.id.btnShow);
        nextGenPicker = new NextGenPicker(this, this, linearLayout);
        nextGenPicker.setType(1);
        nextGenPicker.setValues(getValues());
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextGenPicker.showPicker();
            }
        });
    }

    private String[] getValues() {
        String[] names = new String[10];
        for (int i = 0; i < names.length; i++) {
            names[i] = "Name " + i;
        }
        return names;
    }

    @Override
    public void onValueChangedListener(int index, String value, int type) {
        Log.e(MainActivity.class.getSimpleName(),"OnValue Change Listener: " + value);
    }

    @Override
    public void onClickListener(int index, String value, int type) {
        Log.e(MainActivity.class.getSimpleName(),"OnClick Change Listener: " + value);
    }

    @Override
    public void onShowPicker(int type) {

    }

    @Override
    public void onHidePicker(int type) {

    }
}
