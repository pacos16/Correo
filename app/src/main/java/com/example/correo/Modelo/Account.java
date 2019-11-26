package com.example.correo.Modelo;

public class Account {
    private int id;
    private String name;
    private String firstSurname;
    private String email;

    public Account(int id, String name, String firstSurname, String email) {
        this.id = id;
        this.name = name;
        this.firstSurname = firstSurname;
        this.email = email;
    }
}
