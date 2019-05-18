package com.example.listagem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class Livros implements Serializable {

    private  static int sequencia = 0;
    private  String id;
    private  String nome;


    public Livros(){
        this.id = String.valueOf(++sequencia);
    }

    public Livros(String id, String nome) {
        this.id = id;
        this.nome = nome;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String toString() {
        return id + " | " + nome + " | " ;

    }

}

