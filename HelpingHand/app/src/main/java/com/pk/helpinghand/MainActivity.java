package com.pk.helpinghand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    LinearLayout l1,l2;
    TextView t1;
    String f1,f2;
    TextToSpeech t;
    String message="";
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        t1=findViewById(R.id.tv1);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
            }
        });
        t=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t.setLanguage(Locale.UK);
                }
            }
        });
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                say();   //Your code here
            }
        }, 0, 1*10*1000);

    }
    private void say()
    {
        StringRequest request=new StringRequest(Request.Method.POST, "http://myvoice.atwebpages.com/android.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                String[] words=response.split("split123split");

                f1=words[0];
                f2=words[1];
                f1=f1.trim();
                f2=f2.trim();
                message="Fetching";
                if(Integer.parseInt(f1)<20 && Integer.parseInt(f2)>20)
                {
                    message="Hi";
                }
                else if(Integer.parseInt(f2)<20 && Integer.parseInt(f1)>20)
                {
                    message="Hello";
                }
                else if(Integer.parseInt(f1)>20  && Integer.parseInt(f2)>20)
                {
                    message="Namaste";
                }
                else if(Integer.parseInt(f1)<20 && Integer.parseInt(f2)<20)
                {
                    message="Bye";
                }
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);


                t1.setText(message);
                String toSpeak=message;
                t.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                
/*              Log.d("Login", "Error: " + error
                        + "\nStatus Code " + error.networkResponse.statusCode
                        + "\nResponse Data " + error.networkResponse.data
                        + "\nCause " + error.getCause()
                        + "\nmessage" + error.getMessage());*/

            }


        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("data","data");

                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);


    }
    public void onPause(){
        if(t !=null){
            t.stop();
            t.shutdown();
        }
        super.onPause();
    }
}