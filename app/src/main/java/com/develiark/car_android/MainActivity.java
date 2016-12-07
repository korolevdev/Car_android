package com.develiark.car_android;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import java.io.IOException;
import java.net.Socket;

import static android.net.sip.SipErrorCode.SERVER_ERROR;

public class MainActivity extends AppCompatActivity {


    public static final String HOST = "192.168.0.168";
    public static final int PORT = 9092;
    public static Socket socket;

    public static MainSocket mainSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        WebView webView = (WebView) findViewById(R.id.webView);
        Button btnUp = (Button) findViewById(R.id.btnUp);
        Button btnDown = (Button) findViewById(R.id.btnDown);
        Button btnRight = (Button) findViewById(R.id.btnRight);
        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnConnect = (Button) findViewById(R.id.btnConnect);


        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        String summary = "<!Doctype html>" +
                "<html> \n" +
                "<body style=\"margin: 0; padding: 0\"> \n" +
                "<center>\n" +
                "<img src=\"http://192.168.0.168:8081/?action=stream\" height=\"100%\" width=\"100%\"> \n" +
                "</center> \n" +
                "</body> \n" +
                "</html>";
        webView.loadDataWithBaseURL("file:///android_asset/ ",
                summary, "text/html", "utf-8", "");

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
                        id = 4;
                        break;
                    case R.id.btnDown:
                        id = 2;
                        break;
                    case R.id.btnLeft:
                        id = 3;
                        break;
                }
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("down");
                        sendMessage(id, "down");
                        return true;

                    case MotionEvent.ACTION_UP:
                        System.out.println("up");
                        sendMessage(id, "up");
                        break;
                }
                return false;
            }
        };

        View.OnClickListener oclBtnConnect = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectServer();
                System.out.println("12");
            }
        };

        btnUp.setOnTouchListener(otlBtnOk);
        btnDown.setOnTouchListener(otlBtnOk);
        btnRight.setOnTouchListener(otlBtnOk);
        btnLeft.setOnTouchListener(otlBtnOk);

        btnConnect.setOnClickListener(oclBtnConnect);
    }

    public static int encode(int id, int speed, int myo) {
        return  id << 24 |  speed << 16 | myo & 0xffff;
    }

    void connectServer() {
        startService(new Intent(getApplicationContext(),
                MainService.class));
    }

    void sendMessage(int id, String action) {
        //int speed = 50;
        System.out.println(Integer.toString(encode(id, 100, 0)));
        if (action.contains("down"))
            mainSocket.sendMessage(Integer.toString(encode(id, 100, 0)));
        else
            mainSocket.sendMessage(Integer.toString(encode(0, 100, 0)));
    }
}
