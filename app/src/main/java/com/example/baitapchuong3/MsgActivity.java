package com.example.baitapchuong3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MsgActivity extends AppCompatActivity {

    TextView history;

    ArrayList<String> result = new ArrayList<String>();
    ArrayList<String> input = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        history = findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        if (extras != null ){

            result = extras.getStringArrayList("result");
            input = extras.getStringArrayList("input");
        }
        StringBuilder historydata = new StringBuilder();
        for (int i = result.size()-1 ; i >= 0 ; i--) {
            historydata.append(input.get(i)).append(" = ").append(result.get(i)).append("\n\n");

        }
        history.setText(historydata.toString());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (history.getText().toString()!=null)
            outState.putString("result", history.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.get("result")!=null)
            history.setText(savedInstanceState.get("result").toString());
    }
}