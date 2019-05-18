package com.example.listagem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Lista {

    private SQLiteDatabase conexao;
    private SQLiteHelper helper;
    private Context context;

    public Lista(Context context) {
        helper = new SQLiteHelper(context);
        conexao = helper.getWritableDatabase();
        this.context = context;
    }

    public void conectar() {
        helper = new SQLiteHelper(context);
        conexao = helper.getWritableDatabase();
    }

    public void desconectar() {
        conexao.close();
    }

    public void incluir(Livros contato) {
        conectar();
        ContentValues cv = new ContentValues();
        cv.put("id", contato.getId());
        cv.put("nome", contato.getNome());
        conexao.insertOrThrow("livro", null, cv);
        desconectar();
    }

    public void editar(Livros livros) {
        conectar();
        ContentValues cv = new ContentValues();
        cv.put("id", livros.getId());
        cv.put("nome", livros.getNome());
        String[] param = new String[1];
        param[0] = String.valueOf(livros.getId());
        conexao.update("livro", cv, "id = ?", param);
        desconectar();
    }

    public void excluir(String id) {
        conectar();
        String[] param = new String[1];
        param[0] = id;
        conexao.delete("livro", "id = ?", param);
        desconectar();

    }

    public List<Livros> listar() {
        conectar();
        List<Livros> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM livro");
        Cursor result = conexao.rawQuery(sql.toString(), null);
        if (((Cursor) result).getCount() > 0) {
            result.moveToFirst();
            do {
                Livros livros = new Livros();
                livros.setId((result.getString(result.getColumnIndex("id"))));
                livros.setNome((result.getString(result.getColumnIndex("nome"))));
                lista.add(livros);
            } while (result.moveToNext());
        }
        desconectar();
        return lista;
    }
}

