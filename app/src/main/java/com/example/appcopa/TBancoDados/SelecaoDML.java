package com.example.appcopa.TBancoDados; 

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appcopa.ConexaoSqlite;
public class SelecaoDML {

    private ConexaoSqlite conexao;

    public SelecaoDML(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: INSERT - Cadastra uma nova seleção no banco de dados
     */
    public boolean inserirSelecao(String codigo, String nome, int qtJogadores, String grupo, String confederacao) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(SelecaoDDL.COL_CODIGO, codigo);
        valores.put(SelecaoDDL.COL_NOME, nome);
        valores.put(SelecaoDDL.COL_QT_JOGADORES, qtJogadores);
        valores.put(SelecaoDDL.COL_GRUPO, grupo);
        valores.put(SelecaoDDL.COL_CONFEDERACAO, confederacao);

        long resultado = db.insert(SelecaoDDL.TABELA_NOME, null, valores);
        db.close();

        return resultado != -1; // Retorna true se inseriu com sucesso
    }

    /**
     * DML: UPDATE - Atualiza os dados de uma seleção existente pelo ID
     */
    public boolean atualizarSelecao(int id, String codigo, String nome, int qtJogadores, String grupo, String confederacao) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(SelecaoDDL.COL_CODIGO, codigo);
        valores.put(SelecaoDDL.COL_NOME, nome);
        valores.put(SelecaoDDL.COL_QT_JOGADORES, qtJogadores);
        valores.put(SelecaoDDL.COL_GRUPO, grupo);
        valores.put(SelecaoDDL.COL_CONFEDERACAO, confederacao);

        String condicaoWhere = SelecaoDDL.COL_ID + " = ?";
        String[] argumentosWhere = { String.valueOf(id) };

        int linhasAfetadas = db.update(SelecaoDDL.TABELA_NOME, valores, condicaoWhere, argumentosWhere);
        db.close();

        return linhasAfetadas > 0;
    }

    /**
     * DML: DELETE - Remove uma seleção do banco pelo ID
     */
    public boolean deletarSelecao(int id) {
        SQLiteDatabase db = conexao.getWritableDatabase();

        String condicaoWhere = SelecaoDDL.COL_ID + " = ?";
        String[] argumentosWhere = { String.valueOf(id) };

        int linhasEliminadas = db.delete(SelecaoDDL.TABELA_NOME, condicaoWhere, argumentosWhere);
        db.close();

        return linhasEliminadas > 0;
    }

    /**
     * DML: SELECT - Busca todas as seleções pertencentes a uma confederação específica (Ex: "UEFA")
     */
    public Cursor buscarPorConfederacao(String confederacao) {
        SQLiteDatabase db = conexao.getReadableDatabase();

        String query = "SELECT * FROM " + SelecaoDDL.TABELA_NOME
                + " WHERE " + SelecaoDDL.COL_CONFEDERACAO + " = ?";

        return db.rawQuery(query, new String[]{confederacao});
    }

    /**
     * DML: SELECT - Busca todas as seleções cadastradas na tabela
     */
    public Cursor buscarTodas() {
        SQLiteDatabase db = conexao.getReadableDatabase();

        String query = "SELECT * FROM " + SelecaoDDL.TABELA_NOME;

        return db.rawQuery(query, null);
    }
}