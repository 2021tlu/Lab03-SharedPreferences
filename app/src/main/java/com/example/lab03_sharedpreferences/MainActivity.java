package com.example.lab03_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tLeft;
    TextView tRight;
    TextView bLeft;
    TextView bRight;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    SeekBar seekBar;

    View.OnClickListener corner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String key = ""+v.getTag();
            int currVal = sharedPreferences.getInt(key,0);
            editor.putInt(key,currVal+1);
            editor.apply();

            String toast = v.getTag() + " pressed " + sharedPreferences.getInt(key,-1) + " times!";
            Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tLeft = findViewById(R.id.tLeft);
        tRight = findViewById(R.id.tRight);
        bLeft = findViewById(R.id.bLeft);
        bRight = findViewById(R.id.bRight);

        tLeft.setOnClickListener(corner);
        tRight.setOnClickListener(corner);
        bLeft.setOnClickListener(corner);
        bRight.setOnClickListener(corner);

        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        // initialize to 0... Or not, if I want to keep it across sessions
        /*editor.putInt(getString(R.string.tlTag),0);
        editor.putInt(getString(R.string.trTag),0);
        editor.putInt(getString(R.string.blTag),0);
        editor.putInt(getString(R.string.brTag),0);*/

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float newSize = ((progress / (float) seekBar.getMax()) * 10) + 25;
                tLeft.setTextSize(newSize);
                tRight.setTextSize(newSize);
                bLeft.setTextSize(newSize);
                bRight.setTextSize(newSize);
                Log.i("seekBar","TextSize is "+newSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                String onStartMsg = "Seekbar touch started!";
                Log.i("seekBar",onStartMsg);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String onStopMsg = "Seekbar touch stopped.";
                Log.i("seekBar",onStopMsg);
            }
        });
    }
}