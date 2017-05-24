package com.example.hug0albert0.incluyeme10;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.concurrent.RunnableFuture;

public class Main2Activity extends AppCompatActivity implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener
{
    private TextToSpeech hablar;
    private EditText casilla;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        hablar=new TextToSpeech(Main2Activity.this,Main2Activity.this);
        final Button boton1 = (Button) findViewById(R.id.btn_DOS);
        final TextView txt1=(TextView)findViewById(R.id.lblHeader);
        casilla=(EditText) findViewById(R.id.et1);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(! hablar.isSpeaking())
                {
                    if(casilla.getText()!=null)
                    {
                        HashMap <String,String> stringStringHashMap=new HashMap<String, String>();
                        stringStringHashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"Hola como estás");
                        hablar.speak(casilla.getText().toString(),TextToSpeech.QUEUE_ADD,stringStringHashMap);
                        txt1.setText(casilla.getText().toString());
                        casilla.setText(null);
                     }

                }
                else
                {
                    hablar.stop();
                }
            }
        });
    }

    @Override
    public void onInit(int status)
    {

    }

    @Override
    public void onUtteranceCompleted(String s)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run() {
                //Toast.makeText(Main2Activity.this, "Completado :v ",Toast.LENGTH_LONG).show();
                Button button= (Button) findViewById(R.id.btn_DOS);
                //button.setVisibility(Button.VISIBLE);
            }
        });
    }

    //Evento de destrucción
    protected  void onDestroy()
    {
        if(hablar!=null)
        {
            hablar.stop();
            hablar.shutdown();
            hablar=null;
        }

        super.onDestroy();

    }
}
