package com.example.myproyectofinal.Json;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproyectofinal.Cifrado.Des;
import com.google.gson.Gson;

public class Json extends AppCompatActivity{

    public static String crearJson(String Name , String firstName , String lastName , String userName , String Mail , int Age , int Number , boolean Gender , boolean Type , String Password ) {
        MyInfo datos = new MyInfo();
        Gson gson = new Gson();
        Des myDes = new Des();

        datos.setName(Name);
        datos.setFirstName(firstName);
        datos.setLastName(lastName);
        datos.setUserName(userName);
        datos.setMail(Mail);
        datos.setAge(Age);
        datos.setNumber(Number);
        datos.setGender(Gender);
        datos.setType(Type);
        datos.setPassword(Password);

        String nuevojson = myDes.cifrar(gson.toJson(datos));

        return nuevojson;
    }

    public static MyInfo leerJson(String textoJson){
        Gson gson = new Gson();
        Des myDes = new Des();
        MyInfo datos = gson.fromJson(myDes.desCifrar(textoJson), MyInfo.class);

        return datos;
    }

    public static String crearJsonCuenta(String Name , String Password , int Image) {
        MyCuenta datos = new MyCuenta();
        Gson gson = new Gson();
        Des myDes = new Des();

        datos.setNameCuenta(Name);
        datos.setPassCuenta(Password);
        datos.setImage(Image);

        String nuevojson = myDes.cifrar(gson.toJson(datos));

        return nuevojson;
    }

    public static MyCuenta leerJsonCuenta(String textoJson){
        Gson gson = new Gson();
        Des myDes = new Des();
        MyCuenta datos = gson.fromJson(myDes.desCifrar(textoJson), MyCuenta.class);

        return datos;
    }
}
