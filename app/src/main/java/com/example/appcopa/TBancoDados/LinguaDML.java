package com.example.appcopa.TBancoDados; 

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcopa.ConexaoSqlite;

public class LinguaDML {

    private ConexaoSqlite conexao;

    public LinguaDML(Context context) {
        // Inicializa o utilitário auxiliar de conexão do seu projeto
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: INSERT - Insere um novo idioma disponível
     */
    public boolean inserirLingua(String nome, int escolha,String cod_lingua) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(LinguaDDL.COL_NOME, nome);
        valores.put(LinguaDDL.COL_ESCOLHA, escolha);
        valores.put(LinguaDDL.COL_COD_LINGA,cod_lingua);

        long resultado = db.insert(LinguaDDL.TABELA_NOME, null, valores);
        db.close();

        return resultado != -1; // Retorna true se inseriu com sucesso
    }

    /**
     * DML: UPDATE - Modifica o estado de seleção de um idioma pelo ID
     */
    public boolean atualizarEscolha(int idLingua, int novaEscolha) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(LinguaDDL.COL_ESCOLHA, novaEscolha);

        String condicaoWhere = LinguaDDL.COL_ID + " = ?";
        String[] argumentosWhere = { String.valueOf(idLingua) };

        int linhasAfetadas = db.update(LinguaDDL.TABELA_NOME, valores, condicaoWhere, argumentosWhere);
        db.close();

        return linhasAfetadas > 0;
    }

    /**
     * DML: SELECT - Procura e retorna o nome da língua que está ativa (escolha = 1)
     */
    public String buscarLinguaAtiva() {
        String idiomaAtivo = "en"; // Idioma de segurança caso o banco esteja vazio
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Monta a query: SELECT nome FROM Linguas WHERE escolha = 1 LIMIT 1
            String query = "SELECT " + LinguaDDL.COL_NOME + " FROM " + LinguaDDL.TABELA_NOME
                    + " WHERE " + LinguaDDL.COL_ESCOLHA + " = 1 LIMIT 1";

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                int idxNome = cursor.getColumnIndexOrThrow(LinguaDDL.COL_NOME);
                idiomaAtivo = cursor.getString(idxNome);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return idiomaAtivo;
    }

    /**
     * DML: DELETE - Remove um idioma do banco pelo ID
     */
    public boolean deletarLingua(int idLingua) {
        SQLiteDatabase db = conexao.getWritableDatabase();

        String condicaoWhere = LinguaDDL.COL_ID + " = ?";
        String[] argumentosWhere = { String.valueOf(idLingua) };

        int linhasEliminadas = db.delete(LinguaDDL.TABELA_NOME, condicaoWhere, argumentosWhere);
        db.close();

        return linhasEliminadas > 0;
    }
}