package com.example.asus.torchtilt;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    CameraManager cm;
    SensorManager sm;
    Sensor s;
    private boolean camera=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cm=(CameraManager)getSystemService(CAMERA_SERVICE);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];
        int x1=(int)x;
        int y1=(int)y;
        int z1=(int)z;
        if(x1!=0){
           try{
               String s1=cm.getCameraIdList()[0];
               cm.setTorchMode(s1,true);
               camera=true;
           }
           catch (CameraAccessException e){

           }
        }
        else{
            try{
                String s1=cm.getCameraIdList()[0];
                cm.setTorchMode(s1,false);
                camera=true;
            }
            catch (CameraAccessException e){

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
