package com.example.hyeok.eventbusexample.ui;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hyeok.eventbusexample.event.ColorEvent;
import com.example.hyeok.eventbusexample.event.LayoutDeliverEvent;
import com.example.hyeok.eventbusexample.R;
import com.example.hyeok.eventbusexample.event.RenderLayoutEvent;
import com.example.hyeok.eventbusexample.util.ButtonInflaterUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ColorListActivity extends AppCompatActivity {

    ConstraintLayout containerLayout;
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

        containerLayout = (ConstraintLayout)findViewById(R.id.root_color_list_container);
        formButton = (Button)findViewById(R.id.show_color_input_form);
        colorAmountButton = (Button)findViewById(R.id.add_color_amount);
        colorSpinner = (Spinner)findViewById(R.id.select_color_list);
        inputAmount = (EditText)findViewById(R.id.input_amount);
        inputFormLayout = (ConstraintLayout)findViewById(R.id.input_color_amount_form);
        colorListLayout = (LinearLayout)findViewById(R.id.container_list_color_amount);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().post(new LayoutDeliverEvent(colorListLayout));
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRenderLayoutEvent(RenderLayoutEvent renderLayoutEvent){
        int childSize = renderLayoutEvent.getLinearLayout().getChildCount();
        Log.d("ColorListActivity", colorListLayout.getParent().toString() + childSize + " " + colorListLayout.getChildCount() + " " + (renderLayoutEvent.getLinearLayout().getChildAt(0) instanceof Button));
        Button colorAmountInfo[] = new Button[childSize];

        for(int i = 0; i < childSize; i++){
            colorAmountInfo[i] = (Button)renderLayoutEvent.getLinearLayout().getChildAt(i);
        }
        renderLayoutEvent.getLinearLayout().removeAllViews();
        for(int i = 0; i < childSize; i++){
            colorListLayout.addView(colorAmountInfo[i]);
        }
    }

    public void createColorInputForm(View view){
        view.setVisibility(View.GONE);
        inputFormLayout.setVisibility(View.VISIBLE);
    }

    public void addColorAmount(View view){
        String selectedColor = colorSpinner.getSelectedItem().toString();
        Integer colorHexCode = convertColorNameToHexCode(selectedColor);
        Integer amount = Integer.parseInt(inputAmount.getText().toString());

        if(amount > 10){
            Toast.makeText(this, "10개 이하로 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            inputFormLayout.setVisibility(View.GONE);
            formButton.setVisibility(View.VISIBLE);

            ButtonInflaterUtil.inflateNewButton(this, colorListLayout, selectedColor, amount);

            EventBus.getDefault().post(new ColorEvent(colorHexCode, amount));
        }
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
