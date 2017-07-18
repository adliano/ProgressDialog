package com.warriorsapp.progressdialog;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{

    ProgressDialog dialog;
    Handler handler; // used for demonstration
    Runnable runnable; // used for demonstration
    Timer timer; // used for demonstration
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(MainActivity.this,R.style.DialogTheme);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIndeterminate(false);
        dialog.setTitle(R.string.dialogTitle);
        dialog.setMessage(getResources().getString(R.string.dialogMsg));
        dialog.setProgress(0);
        dialog.setMax(100);
        dialog.show();

        handler = new Handler();

        // not using Lambda
        /*
        runnable = new Runnable()
        {
            @Override
            public void run()
            {

            }
        };
        */

        // using Lambda (Better Performance)
        runnable = () -> {
            i += 5; // percentage progress
            if(i <= 100) dialog.setProgress(i);
            else
            {
                timer.cancel();
                dialog.cancel();
                i = 0;
            }
        };

        timer = new Timer();

        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                handler.post(runnable);
            }
        },8000,1000);
    }
}
