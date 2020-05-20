package com.example.lab5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    SensorEventListener mySensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        final Sensor acc_Sensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        final Sensor temp_Sensor= sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        //hooks
        final TextView text1 = findViewById(R.id.textView);
        final TextView text2 = findViewById(R.id.textView2);
        final TextView text3 = findViewById(R.id.textView3);
        final TextView text4 = findViewById(R.id.textView4);



        mySensorEventListener= new SensorEventListener(){
            public void onSensorChanged(SensorEvent sensorEvent){
                if (sensorEvent.sensor==acc_Sensor){
                    String text_one = Float. toString(sensorEvent.values[0]);
                    String text_two = Float. toString(sensorEvent.values[1]);
                    String text_three = Float. toString(sensorEvent.values[2]);
                    text1.setText(text_one);
                    text2.setText(text_two);
                    text3.setText(text_three);
                }
                if (sensorEvent.sensor==temp_Sensor){
                    String temp = Float. toString(sensorEvent.values[0]);
                    text4.setText(temp);
                }
            }
            public void onAccuracyChanged(Sensor sensor, int accuracy){
                // react to a change in accuracy here
            }
        };
        if(acc_Sensor!= null){
            sensorManager.registerListener(mySensorEventListener, acc_Sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.d("Sensor","The accelerometer is not available");
        }
        if(temp_Sensor!= null){
            sensorManager.registerListener(mySensorEventListener, temp_Sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.d("Sensor","The temperature sensor is not available");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(mySensorEventListener);
    }
}
