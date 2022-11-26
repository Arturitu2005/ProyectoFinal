package com.example.myproyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.myproyectofinal.Json.MyInfo;
import com.example.myproyectofinal.Json.Json;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Welcome extends AppCompatActivity {

    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textview = findViewById(R.id.textView);

        try {

            int numArchivo = getIntent().getExtras().getInt("numArchivo");
            Json json = new Json();

            BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + numArchivo + ".txt")));
            String lineaTexto = file.readLine();
            String completoTexto = "";
            while(lineaTexto != null){
                completoTexto = completoTexto + lineaTexto;
                lineaTexto = file.readLine();
            }
            MyInfo datos = json.leerJson(completoTexto);
            file.close();

            textview.setText("Welcome " + datos.getFirstName());
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
            new Handler( ).postDelayed(new Runnable() {
                @Override
                public void run(){
                    Intent intent = new Intent( Welcome.this, ListaPrincipal.class);
                    intent.putExtra("numArchivo", numArchivo);
                    startActivity( intent );
                }
            } , 4000 );
        }catch(Exception e){}
    }
}