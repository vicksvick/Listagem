package com.example.listagem;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Tela1 extends AppCompatActivity {
    private TextView mTextMessage;

    List<String> itens;
    ArrayAdapter<String> Adapter;
    ListView ltLista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nvInicio:
                    carregarpagina1();
                    return true;
                case R.id.nvGeral:
                    carregarpagina2();
                    return true;
                case R.id.nvFavo:
                    carregarpagina3();
                    return true;
            }
            return false;
        }

        private void carregarpagina1() {
            Intent it = new Intent(Tela1.this, Pagina1.class);
            startActivity(it);

        }



        private void carregarpagina2() {
            Intent it = new Intent(Tela1.this, Pagina2.class);
            startActivity(it);

        }



        private void carregarpagina3() {
            Intent it = new Intent(Tela1.this, Pagina3.class);
            startActivity(it);

        }


    };

}
