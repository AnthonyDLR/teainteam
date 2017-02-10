package com.example.anthonydelarosa.senior_design;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button timedate = (Button) findViewById(R.id.toTime);
        timedate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent next = new Intent(getApplicationContext(),timedate.class);
                startActivityForResult(next,2000);
            }
        });
    }

    public void startBrew(View view) {
        Intent next = setSpinners();
        //Later change this value depending on strength??
        next.putExtra("date", TimeUnit.MINUTES.toMillis(5));
        setResult(Activity.RESULT_OK,next);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 2000){
            if (resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                long myEtLong;
                Intent next = setSpinners();
                if (extras != null) {
                    myEtLong = extras.getLong("date");
                    next.putExtra("date",myEtLong);
                    setResult(Activity.RESULT_OK, next);
                    finish();
                }
            }
            if (requestCode == Activity.RESULT_CANCELED) {

            }

        }
    }

    private Intent setSpinners(){
        Intent next = new Intent(this, HomePage.class);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String strength = mySpinner.getSelectedItem().toString();
        next.putExtra("strength" , strength);
        Spinner teaSpinner = (Spinner) findViewById(R.id.spinner2);
        String tea = teaSpinner.getSelectedItem().toString();
        next.putExtra("tea" , tea);
        return next;
    }
}
