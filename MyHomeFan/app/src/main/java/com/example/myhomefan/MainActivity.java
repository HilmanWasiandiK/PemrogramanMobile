package com.example.myhomefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    final int SPEED[] = {0, 500, 300, 100};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        switchButton = (Switch) findViewById(R.id.switch1);
        imageView = (ImageView) findViewById(R.id.sharingan);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        rotateAnimator=ObjectAnimator.ofFloat(imageView, "rotation", 0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(400);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rotateAnimator.setDuration(SPEED[seekBar.getProgress()]);
                    rotateAnimator.start();
                    Log.d("Rotate", "Start Rotating!");
                } else {
                    rotateAnimator.end();
                    Log.d("Rotate", "Stop Rotating!");
                }
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchButton.isChecked()) {
                    gd.setColors(new int[]{Color.BLUE, Color.TRANSPARENT});
                    imageView.setBackground(gd);
                    Log.d("Light", "Light On!");
                } else {
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                    Log.d("Light", "Light off!");
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(toggleButton.isChecked()) {
                    rotateAnimator.setDuration(SPEED[seekBar.getProgress()]);
                    rotateAnimator.start();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}