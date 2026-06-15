package com.example.appcopa.TBancoDados; 

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcopa.ConexaoSqlite;

public class ConfiguracaoDML {

    private ConexaoSqlite conexao;

    public ConfiguracaoDML(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: INSERT - Cria a linha inicial de configurações do aplicativo
     */
    public boolean criarConfiguracaoInicial(String codLinguaPadrao, String copaPadrao) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        // Forçamos o ID 1 para garantir que só existirá uma linha de configuração global
        valores.put(ConfiguracaoDDL.COL_ID, 1);
        valores.put(ConfiguracaoDDL.COL_LINGUAS_ATIVA, codLinguaPadrao);
        valores.put(ConfiguracaoDDL.COL_NOTIFICACOES_ATIVAS, 1); // 1 = Ativadas por padrão
        valores.put(ConfiguracaoDDL.COL_MODO_NOTURNO, 0);        // 0 = Modo claro por padrão
        valores.put(ConfiguracaoDDL.COL_ULTIMA_ATUALIZACAO, "");
        valores.put(ConfiguracaoDDL.COL_COPA_ATIVA, copaPadrao);

        long resultado = db.insert(ConfiguracaoDDL.TABELA_NOME, null, valores);
        db.close();

        return resultado != -1;
    }

    /**
     * DML: UPDATE - Atualiza o idioma ativo nas configurações globais
     */
    public boolean atualizarIdioma(String codNovaLingua) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(ConfiguracaoDDL.COL_LINGUAS_ATIVA, codNovaLingua);

        // Atualiza apenas a linha de configuração mestre (ID = 1)
        int linhasAfetadas = db.update(
                ConfiguracaoDDL.TABELA_NOME,
                valores,
                ConfiguracaoDDL.COL_ID + " = 1",
                null
        );
        db.close();

        return linhasAfetadas > 0;
    }

    /**
     * DML: UPDATE - Liga ou desliga o modo noturno e as notificações
     */
    public boolean atualizarPreferenciasVisuais(int modoNoturno, int notificacoesAtivas) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(ConfiguracaoDDL.COL_MODO_NOTURNO, modoNoturno);
        valores.put(ConfiguracaoDDL.COL_NOTIFICACOES_ATIVAS, notificacoesAtivas);

        int linhasAfetadas = db.update(
                ConfiguracaoDDL.TABELA_NOME,
                valores,
                ConfiguracaoDDL.COL_ID + " = 1",
                null
        );
        db.close();

        return linhasAfetadas > 0;
    }

    /**
     * DML: UPDATE - Atualiza o carimbo de data/hora da última sincronização com o servidor
     */
    public boolean atualizarDataSincronizacao(String dataFormatada) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(ConfiguracaoDDL.COL_ULTIMA_ATUALIZACAO, dataFormatada);

        int linhasAfetadas = db.update(
                ConfiguracaoDDL.TABELA_NOME,
                valores,
                ConfiguracaoDDL.COL_ID + " = 1",
                null
        );
        db.close();

        return linhasAfetadas > 0;
    }

    /**
     * DML: SELECT - Busca as configurações globais do aplicativo (ID = 1)
     * Retorna o Cursor posicionado na primeira linha para ser consumido pelo Model
     */
    public Cursor buscarConfiguracaoGlobal() {
        SQLiteDatabase db = conexao.getReadableDatabase();

        String query = "SELECT * FROM " + ConfiguracaoDDL.TABELA_NOME + " WHERE " + ConfiguracaoDDL.COL_ID + " = 1";

        // Retorna o cursor sem fechar o banco aqui, pois o Model precisará ler os dados dele antes de fechar
        return db.rawQuery(query, null);
    }
}