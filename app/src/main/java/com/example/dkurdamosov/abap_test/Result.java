package com.example.dkurdamosov.abap_test;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView perc=(TextView) findViewById(R.id.textPercent);

        TextView textCountTrue=(TextView) findViewById(R.id.textCountTrue);
        TextView textCountFalse =(TextView) findViewById(R.id.textCountFalse);
        textCountTrue.setText(Integer.toString(Main_Controller.getCountTrue()));
        textCountFalse.setText(Integer.toString(Main_Controller.getCountFalse()));
        perc.setText(Main_Controller.getPercent()+" %");
    }
}
