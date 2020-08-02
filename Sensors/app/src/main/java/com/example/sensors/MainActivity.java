package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer, mGyro, mMagno, mLight, mPressure, mTemp, mHumi;
    private TextView accXValue, accYValue, accZValue, gyroXValue, gyroYValue, gyroZValue, magnoXValue, magnoYValue, magnoZValue, light, pressure, temp, humi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        accXValue = findViewById(R.id.accXValue);
        accYValue = findViewById(R.id.accYValue);
        accZValue = findViewById(R.id.accZValue);
        gyroXValue = findViewById(R.id.gyroXValue);
        gyroYValue = findViewById(R.id.gyroYValue);
        gyroZValue = findViewById(R.id.gyroZValue);
        magnoXValue = findViewById(R.id.magnoXValue);
        magnoYValue = findViewById(R.id.magnoYValue);
        magnoZValue = findViewById(R.id.magnoZValue);
        light = findViewById(R.id.lightTV);
        pressure = findViewById(R.id.lightTV);
        temp = findViewById(R.id.tempTV);
        humi = findViewById(R.id.humiTV);

        if(mTemp != null){
            sensorManager.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            temp.setText("Not Available");
        }

        if(mHumi != null){
            sensorManager.registerListener(this, mHumi, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            humi.setText("Not Available");
        }

        if(mLight != null){
            sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            light.setText("Not Available");
        }

        if(mPressure != null){
            sensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            pressure.setText("Not Available");
        }

        if(mGyro != null){
            sensorManager.registerListener(this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            gyroXValue.setText("GyroScope ");
            gyroYValue.setText("is not ");
            gyroZValue.setText("available");
        }

        if(mMagno != null){
            sensorManager.registerListener(this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            magnoXValue.setText("Magnometer ");
            magnoYValue.setText("is not ");
            magnoZValue.setText("available");
        }


        if(accelerometer != null){
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            accXValue.setText("Accelerometer ");
            accYValue.setText("is not ");
            accZValue.setText("available");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        if(sensor.getType()== Sensor.TYPE_ACCELEROMETER){
            accXValue.setText("X="+sensorEvent.values[0]);
            accYValue.setText("Y="+sensorEvent.values[1]);
            accZValue.setText("Z="+sensorEvent.values[2]);
        }

        else if(sensor.getType()==Sensor.TYPE_GYROSCOPE){
            gyroXValue.setText("X="+sensorEvent.values[0]);
            gyroYValue.setText("Y="+sensorEvent.values[1]);
            gyroZValue.setText("Z="+sensorEvent.values[2]);
        }

        else if(sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            magnoXValue.setText("X="+sensorEvent.values[0]);
            magnoYValue.setText("Y="+sensorEvent.values[1]);
            magnoZValue.setText("Z="+sensorEvent.values[2]);
        }

        else if(sensor.getType()==Sensor.TYPE_LIGHT){
            light.setText("VALUE: "+ sensorEvent.values[0]);
        }

        else if(sensor.getType()==Sensor.TYPE_PRESSURE){
            pressure.setText("VALUE: "+ sensorEvent.values[0]);
        }

        else if(sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE){
            temp.setText("VALUE: "+ sensorEvent.values[0]);
        }

        else if(sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY){
            humi.setText("VALUE: "+ sensorEvent.values[0]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}