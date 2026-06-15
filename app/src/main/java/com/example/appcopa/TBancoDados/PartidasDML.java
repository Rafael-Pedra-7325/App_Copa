package com.example.appcopa.TBancoDados; // Substitua pelo package real do seu projeto

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appcopa.ConexaoSqlite;

public class PartidasDML {

    private ConexaoSqlite conexao;

    public PartidasDML(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: INSERT - Insere uma partida contendo as informações e resultados do JSON de teste
     */
    public boolean inserirDoJson(String grupo, String selecao1, String selecao2, String data,
                                 String horario, String estadio, int placarSelecao1,
                                 int placarSelecao2, String placar) {

        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(PartidasDDL.COL_GRUPO, grupo);
        valores.put(PartidasDDL.COL_SELECAO1, selecao1);
        valores.put(PartidasDDL.COL_SELECAO2, selecao2);
        valores.put(PartidasDDL.COL_DATA, data);
        valores.put(PartidasDDL.COL_HORARIO, horario);
        valores.put(PartidasDDL.COL_ESTADIO, estadio);
        valores.put(PartidasDDL.COL_PLACAR_SELECAO1, placarSelecao1);
        valores.put(PartidasDDL.COL_PLACAR_SELECAO2, placarSelecao2);
        valores.put(PartidasDDL.COL_PLACAR, placar);

        long resultado = db.insert(PartidasDDL.TABELA_NOME, null, valores);
        db.close();

        return resultado != -1; // Retorna true se a inserção ocorreu com sucesso
    }

    /**
     * DML: SELECT - Retorna os jogos filtrados por um grupo específico (Ex: "A"), ordenados por data e hora
     */
    public Cursor buscarPartidasPorGrupo(String grupo) {
        SQLiteDatabase db = conexao.getReadableDatabase();

        String query = "SELECT * FROM " + PartidasDDL.TABELA_NOME
                + " WHERE " + PartidasDDL.COL_GRUPO + " = ?"
                + " ORDER BY " + PartidasDDL.COL_DATA + " ASC, " + PartidasDDL.COL_HORARIO + " ASC";

        return db.rawQuery(query, new String[]{grupo});
    }

    /**
     * DML: SELECT - Retorna o calendário completo com todos os confrontos salvos
     */
    public Cursor buscarCalendarioCompleto() {
        SQLiteDatabase db = conexao.getReadableDatabase();

        String query = "SELECT * FROM " + PartidasDDL.TABELA_NOME
                + " ORDER BY " + PartidasDDL.COL_DATA + " ASC, " + PartidasDDL.COL_HORARIO + " ASC";

        return db.rawQuery(query, null);
    }

    /**
     * DML: DELETE - Limpa as partidas salvas na tabela
     */
    public void limparTabela() {
        SQLiteDatabase db = conexao.getWritableDatabase();
        db.delete(PartidasDDL.TABELA_NOME, null, null);
        db.close();
    }
}