package com.example.dkurdamosov.abap_test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by dkurdamosov on 24.10.2017.
 */

public class Start extends AppCompatActivity implements View.OnClickListener{
private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        start=(Button) start.findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}