package com.example.anthonydelarosa.senior_design;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class HomePage extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    TextView dateType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        Bundle extras = getIntent().getExtras();
        String myEtText;
        TextView strengthType = (TextView) findViewById(R.id.strengthType);
        TextView teaType = (TextView) findViewById(R.id.teaType);
        dateType = (TextView) findViewById(R.id.time_left);
        if (extras != null) {
            myEtText = extras.getString("strength");
            strengthType.setText(myEtText);
            myEtText = extras.getString("tea");
            teaType.setText(myEtText);
            long myEtLong = extras.getLong("date");
            final CounterClass timer = new CounterClass(myEtLong, 1000);
            timer.start();

        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    public class CounterClass extends CountDownTimer{
        public CounterClass(long millisInFuture, long countDownInterval){
            super (millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished){
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds((millis)) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            dateType.setText(hms);
        }

        @Override
        public void onFinish(){
            dateType.setText("00:00:00");
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("HomePage Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
    public void toSettings(View view){
        Intent next = new Intent(this, Settings.class);
        startActivity(next);
        finish();
    }
    public void EndApp(View view){
        finish();
        System.exit(0);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
