package com.example.baitapchuong3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result, input;
    MaterialButton btnC, btnOpen, btnClose;
    MaterialButton btnChia, btnNhan, btnCong, btnTru, btnAC, btnBang;
    MaterialButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    MaterialButton btnDot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        input = findViewById(R.id.input);

        assignId(btnC,R.id.button_a);
        assignId(btnOpen,R.id.button_b);
        assignId(btnClose,R.id.button_c);
        assignId(btnChia,R.id.button_d);
        assignId(btnNhan,R.id.button_x);
        assignId(btnCong,R.id.button_plus);
        assignId(btnTru,R.id.button_sub);
        assignId(btnBang,R.id.button_equal);
        assignId(btnAC,R.id.button_ac);
        assignId(btn1,R.id.button_1);
        assignId(btn2,R.id.button_2);
        assignId(btn3,R.id.button_3);
        assignId(btn4,R.id.button_4);
        assignId(btn5,R.id.button_5);
        assignId(btn6,R.id.button_6);
        assignId(btn7,R.id.button_7);
        assignId(btn8,R.id.button_8);
        assignId(btn9,R.id.button_9);
        assignId(btn0,R.id.button_0);
        assignId(btnDot,R.id.button_dot);

    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String inputData = input.getText().toString();

        if(buttonText.equals("AC")){
            input.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            input.setText(result.getText());
            return;
        }
        if(buttonText.equals("C")){
            inputData = inputData.substring(0,inputData.length()-1);
        }else{
            inputData = inputData+buttonText;
        }

        input.setText(inputData);

        String finalResult = getResult(inputData);

        if(!finalResult.equals("Error")){
            result.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}