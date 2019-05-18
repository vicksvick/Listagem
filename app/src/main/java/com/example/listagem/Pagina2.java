package com.example.listagem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Pagina2 extends AppCompatActivity {

    private DialogInterface.OnClickListener dialogClickListenner;

    public class Tela1 extends AppCompatActivity {

        ListView listViewLivros;
        ArrayAdapter<Livros> adapter;
        List<Livros> listaLivros;
        Livros livrosSelecionado;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_pagina2);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    //      .setAction("Action", null).show();

                    Intent intent = new Intent(Pagina2.this, Tela1.class);
                    startActivity(intent);

                }
            });

            listViewLivros = (ListView) findViewById(R.id.listViewLivros);
            listViewLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Livros livros = adapter.getItem(position);
                    Intent intent = new Intent(Pagina2.this, Tela1.class);
                    intent.putExtra("edicao", true);
                    intent.putExtra("posicao", position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("livro", livros);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });

            listViewLivros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    livrosSelecionado = adapter.getItem(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Pagina2.this);
                    builder.setMessage("Confirma a exclusão?")
                            .setPositiveButton("Sim", dialogClickListenner)
                            .setNegativeButton("Não", dialogClickListenner).show();
                    return true;
                }
            });
            atualizarLista();

        }

        @Override
        protected void onResume() {
            super.onResume();
            adapter.notifyDataSetChanged();
            atualizarLista();
        }

        private void atualizarLista() {
            Lista lista = new Lista(Pagina2.this);
            listaLivros = lista.listar();
            adapter = new ArrayAdapter<>(Pagina2.this, android.R.layout.simple_list_item_1, listaLivros);
            listViewLivros.setAdapter(adapter);
        }

        DialogInterface.OnClickListener dialogClickListenner = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Lista lista = new Lista(Pagina2.this);
                        lista.excluir(livrosSelecionado.getId());
                        atualizarLista();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(Pagina2.this, "Operação Cancelada.",
                                Toast.LENGTH_SHORT).show();
                }
            }
        };

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();


            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        };
    }
}