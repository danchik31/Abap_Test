package com.example.dkurdamosov.abap_test;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    Main_Controller mainController;
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        Main_Controller mainController = new Main_Controller( this );//устанавливаем контроллер для экрана
    }

    public static Context getContext(){
        return context;
    }
}
