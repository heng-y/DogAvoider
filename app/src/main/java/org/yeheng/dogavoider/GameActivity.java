package org.yeheng.dogavoider;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private int playerLocation = 0;
    private SensorManager sensorManager;
    private Sensor sensor;
    private ImageView player;
    private boolean hasCalibrated = false;
    private double initial_x = 0;
    private DecimalFormat format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ImageView background = findViewById(R.id.background);
        ImageView background2 = findViewById(R.id.background2);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Log.d("GAMEACTIVITY", "window manager height = " + metrics.heightPixels);
        background2.setLayoutParams(new FrameLayout.LayoutParams(metrics.widthPixels, metrics.heightPixels));
        BackgroundScroller bs = new BackgroundScroller(background, background2, metrics.heightPixels);
        bs.start();
        player = findViewById(R.id.player1);

        Log.d("GAMEACTIVITY", "The value of player.setY is " + ((metrics.heightPixels - player.getMeasuredHeight()) - 7));
        player.setX(10 + player.getX());
        Log.d("GAMEACTIVITY", "The value of player.height is " + player.getHeight());



        Log.d("GAMEACTIVITY", "The value of player.height is now " + player.getHeight());

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);


        format = new DecimalFormat("##.##"); // Add hashes for more accuracy

        format.setRoundingMode(RoundingMode.CEILING);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!hasCalibrated) {
            initial_x = Double.parseDouble(format.format(event.values[0]));
            Log.d("GAMEACTIVITY", "Calibrated X value: " + initial_x);
            hasCalibrated = true;
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing

    }
}
