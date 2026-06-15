package com.example.appcopa.TBancoDados; 

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcopa.ConexaoSqlite;

import java.util.ArrayList;
import java.util.List;

public class LinguaModel {

    private ConexaoSqlite conexao;

    public LinguaModel(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: SELECT (Todos) - Recupera todas as línguas cadastradas no banco
     * @return Lista de objetos LinguaPojo
     */
    public List<LinguaPojo> buscarTodas() {
        List<LinguaPojo> listaLinguas = new ArrayList<>();
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + LinguaDDL.TABELA_NOME;
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    LinguaPojo lingua = mapearCursorParaPojo(cursor);
                    listaLinguas.add(lingua);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return listaLinguas;
    }

    /**
     * DML: SELECT (Único) - Busca uma língua específica a partir do seu ID
     * @param id ID do idioma desejado
     * @return Objeto LinguaPojo populado ou null caso não exista
     */
    public LinguaPojo buscarPorId(int id) {
        LinguaPojo lingua = null;
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + LinguaDDL.TABELA_NOME + " WHERE " + LinguaDDL.COL_ID + " = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                lingua = mapearCursorParaPojo(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return lingua;
    }

    /**
     * DML: UPDATE - Define qual idioma está ativo no aplicativo.
     * Esse método desativa todas as outras línguas (escolha = 0) e ativa apenas a selecionada (escolha = 1).
     * @param idLinguaAtiva ID do idioma que o usuário escolheu clicar
     * @return true se a operação foi concluída com sucesso
     */
    public boolean definirIdiomaAtivo(int idLinguaAtiva) {
        SQLiteDatabase db = conexao.getWritableDatabase();
        boolean sucesso = false;

        // Iniciamos uma transação para garantir que ou tudo dá certo ou nada muda no banco
        db.beginTransaction();
        try {
            // 1. Desativa todos os idiomas (coloca escolha = 0 para todo mundo)
            ContentValues valoresDesativar = new ContentValues();
            valoresDesativar.put(LinguaDDL.COL_ESCOLHA, 0);
            db.update(LinguaDDL.TABELA_NOME, valoresDesativar, null, null);

            // 2. Ativa apenas o idioma selecionado (coloca escolha = 1 para o ID escolhido)
            ContentValues valoresAtivar = new ContentValues();
            valoresAtivar.put(LinguaDDL.COL_ESCOLHA, 1);
            int linhasAfetadas = db.update(
                    LinguaDDL.TABELA_NOME,
                    valoresAtivar,
                    LinguaDDL.COL_ID + " = ?",
                    new String[]{String.valueOf(idLinguaAtiva)}
            );

            if (linhasAfetadas > 0) {
                db.setTransactionSuccessful(); // Confirma as mudanças
                sucesso = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction(); // Finaliza a transação com segurança
            db.close();
        }

        return sucesso;
    }

    /**
     * Método auxiliar privado para extrair os dados do Cursor do SQLite e popular o POJO.
     */
    private LinguaPojo mapearCursorParaPojo(Cursor cursor) {
        LinguaPojo lingua = new LinguaPojo();

        int idxId = cursor.getColumnIndexOrThrow(LinguaDDL.COL_ID);
        int idxNome = cursor.getColumnIndexOrThrow(LinguaDDL.COL_NOME);
        int idxEscolha = cursor.getColumnIndexOrThrow(LinguaDDL.COL_ESCOLHA);

        lingua.setId(cursor.getInt(idxId));
        lingua.setNome(cursor.getString(idxNome));
        lingua.setEscolha(cursor.getInt(idxEscolha));

        return lingua;
    }
}