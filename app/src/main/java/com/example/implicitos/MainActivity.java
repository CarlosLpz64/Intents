package com.example.implicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //IDENTIFICAR
        btn_1 = findViewById(R.id.imp1);
        btn_2 = findViewById(R.id.imp2);
        btn_3 = findViewById(R.id.imp3);
        btn_4 = findViewById(R.id.imp4);
        btn_5 = findViewById(R.id.imp5);
        btn_6 = findViewById(R.id.imp6);
        btn_7 = findViewById(R.id.imp7);
        btn_8 = findViewById(R.id.exp1);

        //LISTENERS
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imp1:
                IntentImp1();
                break;
            case R.id.imp2:
                IntentImp2();
                break;
            case R.id.imp3:
                IntentImp3();
                break;
            case R.id.imp4:
                IntentImp4();
                break;
            case R.id.imp5:
                IntentImp5();
                break;
            case R.id.imp6:
                IntentImp6("ola", 20);
                break;
            case R.id.imp7:
                IntentImp7(Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode("1st & Pike, Seattle")));
                break;
            case R.id.exp1:
                IntentExp1();
                break;
        }
    }

    //CAMBIAR DE ACTIVITY (Intent EXPLICITO)
    private void IntentExp1(){
        Intent PantExp = new Intent(getApplicationContext(), pantalla_exp.class); //pantalla_exp es la pantalla a visitar
        startActivity(PantExp);
    }

    /*INTENTS EXPLICITOS  ACTION_VIEW, ACTION_WEB_SEARCH, etc.*/

    //NAVEGAR A SITIO WEB (Intent IMPLICITO)
    private void IntentImp1(){
        Intent NavegarImp = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));
        startActivity(NavegarImp);
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/")));
    }
    //ABRIR LOS CONTACTOS
    private void IntentImp2(){
        Intent ContactosImp = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people"));
        startActivity(ContactosImp);
        //startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people/")));
    }
    //MARCAR A UN CONTACTO
    private void IntentImp3(){
        Intent MarcarImp = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:8713919228"));
        startActivity(MarcarImp);
        //startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("tel:8713919228")));
    }
    //BUSCAR ALGO EN BUSCADOR
    private void IntentImp4(){
        Intent BuscarImp= new Intent(Intent.ACTION_WEB_SEARCH );
        BuscarImp.putExtra(SearchManager.QUERY, "Bortolotti");
        startActivity(BuscarImp);
    }
    private void IntentImp5(){
        Intent VoiceImp=new Intent(Intent.ACTION_VOICE_COMMAND);
        startActivity(VoiceImp);
        //startActivity(new Intent(Intent.ACTION_VOICE_COMMAND));
    }
    //TIMER (Necesita permisos)
    //Para invocar la intent ACTION_SET_TIMER, tu app debe tener el permiso SET_ALARM:
    //<uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
    private void IntentImp6(String message, int seconds){
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void IntentImp7(Uri geoLocation){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}