package com.example.appcopa.TBancoDados; 

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appcopa.ConexaoSqlite;

public class GrupoDML {

    private final ConexaoSqlite conexao;

    public GrupoDML(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: INSERT - Vincula uma seleção a um determinado grupo da Copa
     */
    public boolean inserirSelecaoNoGrupo(String letra, int idSelecao, String codSelecao) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(GrupoDDL.COL_LETRA, letra);
        valores.put(GrupoDDL.COL_ID_SELECAO, idSelecao);
        valores.put(GrupoDDL.COL_COD_SELECAO, codSelecao);

        long resultado = db.insert(GrupoDDL.TABELA_NOME, null, valores);
        db.close();

        return resultado != -1;
    }

    /**
     * DML: UPDATE - Altera os dados de chaveamento de um grupo pelo ID da linha
     */
    public boolean atualizarGrupo(int id, String letra, int idSelecao, String codSelecao) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(GrupoDDL.COL_LETRA, letra);
        valores.put(GrupoDDL.COL_ID_SELECAO, idSelecao);
        valores.put(GrupoDDL.COL_COD_SELECAO, codSelecao);

        String condicaoWhere = GrupoDDL.COL_ID + " = ?";
        String[] argumentosWhere = { String.valueOf(id) };

        int linhasAfetadas = db.update(GrupoDDL.TABELA_NOME, valores, condicaoWhere, argumentosWhere);
        db.close();

        return linhasAfetadas > 0;
    }

    /**
     * DML: DELETE - Remove uma linha de chaveamento pelo ID
     */
    public boolean deletarLinhaGrupo(int id) {
        SQLiteDatabase db = conexao.getWritableDatabase();

        String condicaoWhere = GrupoDDL.COL_ID + " = ?";
        String[] argumentosWhere = { String.valueOf(id) };

        int linhasEliminadas = db.delete(GrupoDDL.TABELA_NOME, condicaoWhere, argumentosWhere);
        db.close();

        return linhasEliminadas > 0;
    }

    /**
     * DML: SELECT - Busca todos os componentes de uma letra de grupo específica (Ex: "A")
     */
    public Cursor buscarChaveamentoPorLetra(String letra) {
        SQLiteDatabase db = conexao.getReadableDatabase();

        String query = "SELECT * FROM " + GrupoDDL.TABELA_NOME
                + " WHERE " + GrupoDDL.COL_LETRA + " = ?";

        return db.rawQuery(query, new String[]{letra});
    }
}