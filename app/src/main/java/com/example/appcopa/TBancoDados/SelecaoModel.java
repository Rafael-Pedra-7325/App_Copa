package com.example.appcopa.TBancoDados; 

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import com.example.appcopa.ConexaoSqlite;

public class SelecaoModel {

    private ConexaoSqlite conexao;

    public SelecaoModel(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: SELECT (Todos) - Busca todas as seleções da tabela
     * @return Uma lista dinâmica contendo objetos SelecaoPojo
     */
    public List<SelecaoPojo> buscarTodas() {
        List<SelecaoPojo> listaSelecoes = new ArrayList<>();
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + SelecaoDDL.TABELA_NOME;
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    SelecaoPojo selecao = mapearCursorParaPojo(cursor);
                    listaSelecoes.add(selecao);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return listaSelecoes;
    }

    /**
     * DML: SELECT (Por Filtro) - Busca seleções que pertencem a um grupo específico
     * @param grupo Nome do grupo (Ex: "Grupo A")
     * @return Lista contendo as seleções daquela chave da Copa
     */
    public List<SelecaoPojo> buscarPorGrupo(String grupo) {
        List<SelecaoPojo> listaSelecoes = new ArrayList<>();
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + SelecaoDDL.TABELA_NOME + " WHERE " + SelecaoDDL.COL_GRUPO + " = ?";
            cursor = db.rawQuery(query, new String[]{grupo});

            if (cursor.moveToFirst()) {
                do {
                    SelecaoPojo selecao = mapearCursorParaPojo(cursor);
                    listaSelecoes.add(selecao);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return listaSelecoes;
    }

    /**
     * DML: SELECT (Único) - Encontra uma seleção específica usando o código FIFA de 3 letras
     * @param codigo Sigla FIFA (Ex: "BRA")
     * @return O objeto SelecaoPojo correspondente ou null caso não exista
     */
    public SelecaoPojo buscarPorCodigo(String codigo) {
        SelecaoPojo selecao = null;
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + SelecaoDDL.TABELA_NOME + " WHERE " + SelecaoDDL.COL_CODIGO + " = ?";
            cursor = db.rawQuery(query, new String[]{codigo});

            if (cursor.moveToFirst()) {
                selecao = mapearCursorParaPojo(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return selecao;
    }

    /**
     * Método auxiliar privado encarregado de extrair as informações brutas
     * mapeadas nas colunas do Cursor e injetá-las em um novo POJO.
     */
    private SelecaoPojo mapearCursorParaPojo(Cursor cursor) {
        SelecaoPojo selecao = new SelecaoPojo();

        int idxId = cursor.getColumnIndexOrThrow(SelecaoDDL.COL_ID);
        int idxCodigo = cursor.getColumnIndexOrThrow(SelecaoDDL.COL_CODIGO);
        int idxNome = cursor.getColumnIndexOrThrow(SelecaoDDL.COL_NOME);
        int idxQt = cursor.getColumnIndexOrThrow(SelecaoDDL.COL_QT_JOGADORES);
        int idxGrupo = cursor.getColumnIndexOrThrow(SelecaoDDL.COL_GRUPO);
        int idxConf = cursor.getColumnIndexOrThrow(SelecaoDDL.COL_CONFEDERACAO);

        selecao.setId(cursor.getInt(idxId));
        selecao.setCodigo(cursor.getString(idxCodigo));
        selecao.setNome(cursor.getString(idxNome));
        selecao.setQtJogadores(cursor.getInt(idxQt));
        selecao.setGrupo(cursor.getString(idxGrupo));
        selecao.setConfederacao(cursor.getString(idxConf));

        return selecao;
    }
}
