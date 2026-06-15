package com.example.appcopa.TBancoDados; // Substitua pelo package real do seu projeto

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appcopa.ConexaoSqlite;

public class GrupoSelecaoDML {

    private final ConexaoSqlite conexao;

    public GrupoSelecaoDML(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: INSERT - Insere uma linha completa de estatísticas baseada no JSON de testes
     */
    public boolean inserirDoJson(int idGrupo, String codigoSelecao, int pontos, int jogos,
                                 int vitorias, int empates, int derrotas,
                                 int golsPro, int golsContra, int saldoGols) {

        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(GrupoSelecaoDDL.COL_ID_GRUPO, idGrupo);
        valores.put(GrupoSelecaoDDL.COL_CODIGO_SELECAO, codigoSelecao);
        valores.put(GrupoSelecaoDDL.COL_PONTOS, pontos);
        valores.put(GrupoSelecaoDDL.COL_JOGOS, jogos);
        valores.put(GrupoSelecaoDDL.COL_VITORIAS, vitorias);
        valores.put(GrupoSelecaoDDL.COL_EMPATES, empates);
        valores.put(GrupoSelecaoDDL.COL_DERROTAS, derrotas);
        valores.put(GrupoSelecaoDDL.COL_GOLS_PRO, golsPro);
        valores.put(GrupoSelecaoDDL.COL_GOLS_CONTRA, golsContra);
        valores.put(GrupoSelecaoDDL.COL_SALDO_GOLS, saldoGols);

        long resultado = db.insert(GrupoSelecaoDDL.TABELA_NOME, null, valores);
        db.close();

        return resultado != -1;
    }

    /**
     * DML: SELECT - Busca a tabela de classificação de um grupo ordenando por pontos decrescente
     * (Ideal para montar a tela de classificação da Copa do Mundo)
     */
    public Cursor buscarClassificacaoDoGrupo(int idGrupo) {
        SQLiteDatabase db = conexao.getReadableDatabase();

        // Query que busca o grupo filtrado e joga os líderes (mais pontos e melhor saldo) para cima
        String query = "SELECT * FROM " + GrupoSelecaoDDL.TABELA_NOME
                + " WHERE " + GrupoSelecaoDDL.COL_ID_GRUPO + " = ?"
                + " ORDER BY " + GrupoSelecaoDDL.COL_PONTOS + " DESC, "
                + GrupoSelecaoDDL.COL_SALDO_GOLS + " DESC";

        return db.rawQuery(query, new String[]{String.valueOf(idGrupo)});
    }

    /**
     * DML: DELETE - Limpa os dados de teste da tabela se necessário
     */
    public void limparTabela() {
        SQLiteDatabase db = conexao.getWritableDatabase();
        db.delete(GrupoSelecaoDDL.TABELA_NOME, null, null);
        db.close();
    }
}