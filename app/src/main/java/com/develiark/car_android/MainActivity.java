package com.develiark.car_android;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String ip = "127.0.0.1";
    public static String port = "9090";
    EditText edtIp;
    EditText edtPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUp = (Button) findViewById(R.id.btnUp);
        Button btnDown = (Button) findViewById(R.id.btnDown);
        Button btnRight = (Button) findViewById(R.id.btnRight);
        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        edtIp = (EditText) findViewById(R.id.editIp);
        edtPort = (EditText) findViewById(R.id.editPort);

        View.OnTouchListener otlBtnOk = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ip = edtIp.getText().toString();
                port = edtPort.getText().toString();
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

    public static String performJSONAuth(String id, String action) {
        return "{\"id\":\"" + id + "\",\"action\":\"" + action + "\"}";
    }

    void sendMessage(int id, String action) {
        MainSocket mainSocket = new MainSocket();
        mainSocket.sendMessage(performJSONAuth(Integer.toString(id), action));
    }
}
