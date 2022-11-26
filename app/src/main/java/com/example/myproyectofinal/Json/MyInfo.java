package com.example.myproyectofinal.Json;

public class MyInfo {

    private String Nombre;
    private String Apellido1;
    private String Apellido2;
    private String NombreUsu;
    private String Mail;
    private int Edad;
    private int Numero;
    private boolean Genero;
    private boolean Type;
    private String Contraseña;

    public String getName() {return Nombre;}

    public void setName(String name) {
        Nombre = name;
    }

    public String getFirstName() {
        return Apellido1;
    }

    public void setFirstName(String firstName) {
        this.Apellido1 = firstName;
    }

    public String getLastName() {
        return Apellido2;
    }

    public void setLastName(String lastName) {
        this.Apellido2 = lastName;
    }

    public String getUserName() {
        return NombreUsu;
    }

    public void setUserName(String userName) {
        this.NombreUsu = userName;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public int getAge() {
        return Edad;
    }

    public void setAge(int age) {Edad = age;}

    public int getNumber() {
        return Numero;
    }

    public void setNumber(int number) {
        Numero = number;
    }

    public boolean isGender() {
        return Genero;
    }

    public void setGender(boolean gender) {
        Genero = gender;
    }

    public boolean isType() {
        return Type;
    }

    public void setType(boolean type) {
        Type = type;
    }

    public String getPassword() {
        return Contraseña;
    }

    public void setPassword(String password) {Contraseña = password;}
}
