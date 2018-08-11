package com.example.dogu2.aydinlatbizi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity {

    TextView status;
    ImageButton btnSwitch;
    boolean pressed=true;
    boolean on_off;
    CameraManager mCameraManager;
    String mCameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        btnSwitch = (ImageButton) findViewById(R.id.btnSwitch);
        status = (TextView) findViewById(R.id.durum);

        final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.nope);

        btnSwitch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if(pressed){
                    mp.start();
                    turnOnFlash();
                    btnSwitch.setImageResource(R.drawable.on);
                    pressed=false;
                    status.setText("ON");
                    status.setTextColor(Color.parseColor("#00FC1D"));

                }else{
                    mp.start();
                    turnOffFlashLight();
                    btnSwitch.setImageResource(R.drawable.off);
                    pressed=true;
                    status.setText("OFF");
                    status.setTextColor(Color.parseColor("#FC2D00"));


                }
            }
        });
    }



    public void turnOnFlash(){

        try{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                mCameraManager.setTorchMode("0",true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void turnOffFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode("0", false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




