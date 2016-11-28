package com.develiark.car_android;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUp = (Button) findViewById(R.id.btnUp);
        Button btnDown = (Button) findViewById(R.id.btnDown);
        Button btnRight = (Button) findViewById(R.id.btnRight);
        Button btnLeft = (Button) findViewById(R.id.btnLeft);

        View.OnTouchListener otlBtnOk = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eventaction = event.getAction();
                int id = 1;
                switch (v.getId()) {
                    case R.id.btnUp:
                        id = 1;
                        break;
                    case R.id.btnRight:
                        id = 2;
                        break;
                    case R.id.btnDown:
                        id = 3;
                        break;
                    case R.id.btnLeft:
                        id = 4;
                        break;
                }
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN:
                        sendMessage(id, "down");
                        return true;

                    case MotionEvent.ACTION_UP:
                        sendMessage(id, "up");
                        break;
                }
                return false;
            }
        };

        btnUp.setOnTouchListener(otlBtnOk);
        btnDown.setOnTouchListener(otlBtnOk);
        btnRight.setOnTouchListener(otlBtnOk);
        btnLeft.setOnTouchListener(otlBtnOk);
    }

    void sendMessage(int id, String action) {

    }
}
