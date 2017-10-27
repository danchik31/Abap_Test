package com.dkurdamosov.abap_test;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
public class Result extends AppCompatActivity {
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //Баннер
        mAdView = (AdView) findViewById(R.id.adViewResult);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        TextView perc=(TextView) findViewById(R.id.textPercent);

        TextView textCountTrue=(TextView) findViewById(R.id.textCountTrue);
        TextView textCountFalse =(TextView) findViewById(R.id.textCountFalse);
        textCountTrue.setText(Integer.toString(Main_Controller.getCountTrue()));
        textCountFalse.setText(Integer.toString(Main_Controller.getCountFalse()));
        perc.setText(Main_Controller.getPercent()+" %");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdView.destroy();
    }
}
