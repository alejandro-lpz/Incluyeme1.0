package com.example.hug0albert0.incluyeme10;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=(TextView)findViewById(R.id.textView2);
    }

    public void cambioApp(View view)
    {
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
    public void onButtonClick (View view)
    {
        if (view.getId()== R.id.imageButton4)
        {

            promptSpeechInput();

        }
    }
    private void promptSpeechInput()
    {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hable por favor");
        try
        {
            startActivityForResult(i,100);
        }

        catch (ActivityNotFoundException a)
        {
            Toast.makeText(MainActivity.this,"Tu celular no soporta reconocimiento de voz", Toast.LENGTH_LONG);
        }

    }
    public void onActivityResult(int request_code, int result_code, Intent i)
    {
        super.onActivityResult(request_code,result_code,i);
        switch (request_code)
        {
            case 100:if(result_code==RESULT_OK && i !=null)
            {
                ArrayList<String> resultado=i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                result.setText(resultado.get(0));

            }
                break;
        }
    }
}
