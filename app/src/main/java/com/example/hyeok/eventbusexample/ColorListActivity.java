package com.example.hyeok.eventbusexample;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.greenrobot.eventbus.EventBus;

public class ColorListActivity extends AppCompatActivity {

    Button formButton;
    Button colorAmountButton;
    Spinner colorSpinner;
    EditText inputAmount;
    ConstraintLayout inputFormLayout;
    LinearLayout colorListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);

        formButton = (Button)findViewById(R.id.show_color_input_form);
        colorAmountButton = (Button)findViewById(R.id.add_color_amount);
        colorSpinner = (Spinner)findViewById(R.id.select_color_list);
        inputAmount = (EditText)findViewById(R.id.input_amount);
        inputFormLayout = (ConstraintLayout)findViewById(R.id.input_color_amount_form);
        colorListLayout = (LinearLayout)findViewById(R.id.container_list_color_amount);
    }

    public void createColorInputForm(View view){
        view.setVisibility(View.GONE);
        inputFormLayout.setVisibility(View.VISIBLE);
    }

    public void addColorAmount(View view){
        String selectedColor = colorSpinner.getSelectedItem().toString();
        Integer colorHexCode = convertColorNameToHexCode(selectedColor);
        Integer amount = Integer.parseInt(inputAmount.getText().toString());

        inputFormLayout.setVisibility(View.GONE);
        formButton.setVisibility(View.VISIBLE);

        ButtonInflaterUtil.inflateNewButton(this, colorListLayout, selectedColor, amount);

        EventBus.getDefault().post(new ColorEvent(colorHexCode, amount));
    }

    public Integer convertColorNameToHexCode(String colorName){
        Integer hexCode = 0;

        switch (colorName){
            case "빨강" :
                hexCode = ResourcesCompat.getColor(getResources(), R.color.red, null);
                break;
            case "주황" :
                hexCode = ResourcesCompat.getColor(getResources(), R.color.orange, null);
                break;
            case "노랑" :
                hexCode = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
                break;
            case "초록" :
                hexCode = ResourcesCompat.getColor(getResources(), R.color.green, null);
                break;
            case "파랑" :
                hexCode = ResourcesCompat.getColor(getResources(), R.color.blue, null);
                break;
            case "남색" :
                hexCode = ResourcesCompat.getColor(getResources(), R.color.navy, null);
                break;
            case "보라" :
                hexCode = ResourcesCompat.getColor(getResources(), R.color.purple, null);
                break;
            default :
                break;
        }

        return hexCode;
    }
}
