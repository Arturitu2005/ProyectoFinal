package com.example.myproyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myproyectofinal.Cifrado.Sha1;
import com.example.myproyectofinal.Json.MyInfo;
import com.example.myproyectofinal.Json.Json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void Registrarse (View v){

        EditText Name = (EditText) findViewById(R.id.editTextELName);
        EditText firstName = (EditText) findViewById(R.id.editTextElPassword);
        EditText lastName = (EditText) findViewById(R.id.editTextRlastName);
        EditText userName = (EditText) findViewById(R.id.editTextRuserName);
        EditText Mail = (EditText) findViewById(R.id.editTextRMail);
        EditText Age = (EditText) findViewById(R.id.editTextRAge);
        EditText Number = (EditText) findViewById(R.id.editTextRNumber);
        RadioButton Gender1 = (RadioButton) findViewById(R.id.radioButtonEL1);
        RadioButton Gender2 = (RadioButton) findViewById(R.id.radioButtonEL2);
        RadioButton Type1 = (RadioButton) findViewById(R.id.radioButtonRType1);
        RadioButton Type2 = (RadioButton) findViewById(R.id.radioButtonRType2);
        EditText Password = (EditText) findViewById(R.id.editTextRPassword);

        String mensaje = "";

        if("".equals(Name.getText().toString()) || "".equals(firstName.getText().toString()) ||
                "".equals(lastName.getText().toString()) || "".equals(userName.getText().toString()) ||
                "".equals(Mail.getText().toString()) || "".equals(Age.getText().toString()) ||
                false == Gender1.isChecked() & false == Gender2.isChecked() ||
                false == Type1.isChecked() & false == Type2.isChecked() ||
                "".equals(Number.getText().toString()) || "".equals(Password.getText().toString()))
        {
            mensaje = "Falta un Parametro";
        }
        else {
            boolean TipoCorreo = false;
            String Correo = "";
            for(int x = 0 ; x < Mail.length(); x++){
                if(Mail.getText().charAt(x) == '@'){
                    for(int y = x ; y < Mail.length(); y++){
                        Correo = Correo + Mail.getText().charAt(y);
                    }
                    if("@gmail.com".equals(Correo) || "@hotmail.com".equals(Correo) || "@outlook.com".equals(Correo)){
                        TipoCorreo = true;
                    }
                    break;
                }
            }
            if(Name.length() > 22 || firstName.length() > 15 || lastName.length() > 15 || userName.length() > 20 ||
                    TipoCorreo == false || Mail.length() > 25 || Age.length() > 2 || Number.length() != 8 || Password.length() > 30){
                mensaje = "Parametro Erroneo";
                if(Name.length() > 22){mensaje = "Nombre Muy Largo";}
                if(firstName.length() > 15){mensaje = "Apellido Paterno Muy Largo";}
                if(lastName.length() > 15){mensaje = "Apellido Materno Muy Largo";}
                if(userName.length() > 20){mensaje = "Nombre de Usuario Muy Largo";}
                if(TipoCorreo == false){mensaje = "Correo Invalido";}
                if(Mail.length() > 25){mensaje = "Correo Muy Largo";}
                if(Age.length() > 2){mensaje = "Edad Invalida";}
                if(Number.length() != 8){mensaje = "Numero Invalido";}
                if(Password.length() > 30){mensaje = "Contraseña Muy Larga";}
            }else{
                try {

                    Sha1 digest = new Sha1();
                    byte[] txtByte = digest.createSha1(userName.getText().toString() + Password.getText().toString());
                    String Sha1Password = digest.bytesToHex(txtByte);

                    String ValorName = Name.getText().toString();
                    String ValorfirstName = firstName.getText().toString();
                    String ValorlastName = lastName.getText().toString();
                    String ValoruserName = userName.getText().toString();
                    String ValorMail = Mail.getText().toString();
                    int ValorAge = Integer.parseInt(Age.getText().toString());
                    int ValorNumber = Integer.parseInt(Number.getText().toString());
                    boolean ValorGender = Gender1.isChecked();
                    boolean ValorType = Type1.isChecked();
                    String ValorPassword = Sha1Password;

                    Json json = new Json();
                    String textoJson = json.crearJson(ValorName, ValorfirstName, ValorlastName, ValoruserName, ValorMail,
                            ValorAge, ValorNumber, ValorGender, ValorType, ValorPassword);

                    boolean BucleArchivo = true;
                    int x = 1;
                    while (BucleArchivo) {
                        File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + x + ".txt");
                        if (Cfile.exists()) {
                            BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                            String lineaTexto = file.readLine();
                            String completoTexto = "";
                            while(lineaTexto != null){
                                completoTexto = completoTexto + lineaTexto;
                                lineaTexto = file.readLine();
                            }
                            file.close();

                            MyInfo datos = json.leerJson(completoTexto);
                            String ValoruserName2 = datos.getUserName();
                            String ValorMail2 = datos.getMail();
                            int ValorNumber2 = datos.getNumber();

                            if (ValoruserName.equals(ValoruserName2) || ValorMail.equals(ValorMail2) || ValorNumber == ValorNumber2) {
                                if(ValorMail.equals(ValorMail2)){mensaje = "Correo Ya Registrado";}
                                if(ValorNumber == ValorNumber2){mensaje = "Numero Ya Registrado";}
                                if(ValoruserName.equals(ValoruserName2)){mensaje = "Usuario Ya Existente";}
                                BucleArchivo = false;
                            } else {
                                x = x + 1;
                            }
                        } else {
                            BufferedWriter file = new BufferedWriter(new OutputStreamWriter(openFileOutput("Archivo" + x + ".txt", Context.MODE_PRIVATE)));
                            file.write(textoJson);
                            file.close();
                            mensaje = "Usuario Registrado";
                            Name.setText("");
                            firstName.setText("");
                            lastName.setText("");
                            userName.setText("");
                            Mail.setText("");
                            Age.setText("");
                            Number.setText("");
                            Gender1.setChecked(false);
                            Gender2.setChecked(false);
                            Type1.setChecked(false);
                            Type2.setChecked(false);
                            Password.setText("");
                            BucleArchivo = false;
                        }
                    }

                } catch (Exception e) {
                    mensaje = "Error al Hacer Registro";
                }
            }
        }
        Toast.makeText(Registro.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void Volver (View v){
        Intent intent = new Intent (Registro.this, Login.class);
        startActivity( intent );
    }
}