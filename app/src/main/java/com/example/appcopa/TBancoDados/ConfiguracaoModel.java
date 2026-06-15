package com.example.appcopa.TBancoDados; 

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcopa.ConexaoSqlite;
import com.example.appcopa.TBancoDados.LinguaDML;

public class ConfiguracaoModel {

    private final ConexaoSqlite conexao;
    private ConfiguracaoDML config;
    public ConfiguracaoModel(Context context) {
        this.conexao = new ConexaoSqlite(context);
        this.config=new ConfiguracaoDML(context);
    }

    /**
     * DML: SELECT - Busca as configurações globais do aplicativo (geralmente ID = 1)
     * @return Um objeto ConfiguracaoPojo populado ou null caso não exista registros
     */
    public ConfiguracaoPojo buscarConfiguracao() {
        ConfiguracaoPojo config = null;
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Como tabelas de configuração só guardam 1 linha com o estado do app, filtramos pelo ID = 1
            String query = "SELECT * FROM " + ConfiguracaoDDL.TABELA_NOME + " WHERE " + ConfiguracaoDDL.COL_ID + " = 1";
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                config = mapearCursorParaPojo(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return config;
    }

    /**
     * DML: UPDATE - Atualiza todas as configurações do aplicativo de uma vez usando os dados de um POJO
     * @param config Objeto ConfiguracaoPojo contendo as novas preferências
     * @return true se a atualização foi bem sucedida
     */
    public boolean salvarConfiguracao(ConfiguracaoPojo config) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(ConfiguracaoDDL.COL_LINGUAS_ATIVA, config.getLinguaAtiva());
        valores.put(ConfiguracaoDDL.COL_NOTIFICACOES_ATIVAS, config.getNotificacoesAtivas());
        valores.put(ConfiguracaoDDL.COL_MODO_NOTURNO, config.getModoNoturno());
        valores.put(ConfiguracaoDDL.COL_ULTIMA_ATUALIZACAO, config.getUltimaAtualizacao());
        valores.put(ConfiguracaoDDL.COL_COPA_ATIVA, config.getCopaAtiva());

        // Atualiza a linha mestre (ID = 1)
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
     * Método auxiliar privado (conforme o modelo solicitado) responsável por mapear
     * a linha atual do Cursor do SQLite diretamente para os atributos do POJO.
     */
    private ConfiguracaoPojo mapearCursorParaPojo(Cursor cursor) {
        ConfiguracaoPojo config = new ConfiguracaoPojo();

        int idxId = cursor.getColumnIndexOrThrow(ConfiguracaoDDL.COL_ID);
        int idxLingua = cursor.getColumnIndexOrThrow(ConfiguracaoDDL.COL_LINGUAS_ATIVA);
        int idxNotif = cursor.getColumnIndexOrThrow(ConfiguracaoDDL.COL_NOTIFICACOES_ATIVAS);
        int idxModo = cursor.getColumnIndexOrThrow(ConfiguracaoDDL.COL_MODO_NOTURNO);
        int idxAtualizacao = cursor.getColumnIndexOrThrow(ConfiguracaoDDL.COL_ULTIMA_ATUALIZACAO);
        int idxCopa = cursor.getColumnIndexOrThrow(ConfiguracaoDDL.COL_COPA_ATIVA);

        config.setId(cursor.getInt(idxId));
        config.setLinguaAtiva(cursor.getInt(idxLingua));
        config.setNotificacoesAtivas(cursor.getInt(idxNotif));
        config.setModoNoturno(cursor.getInt(idxModo));
        config.setUltimaAtualizacao(cursor.getString(idxAtualizacao));
        config.setCopaAtiva(cursor.getString(idxCopa));

        return config;
    }

    public void SalvarLingua(String codIdioma){
        buscarConfiguracao();
        if(buscarConfiguracao()==null)
        {
            config.criarConfiguracaoInicial(codIdioma,"2026");
        }
        else
        {
            config.atualizarIdioma(codIdioma);
        }
    }
}