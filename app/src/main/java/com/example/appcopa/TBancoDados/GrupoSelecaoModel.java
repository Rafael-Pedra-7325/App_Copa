package com.example.appcopa.TBancoDados; // Substitua pelo package real do seu projeto

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import com.example.appcopa.ConexaoSqlite;

public class GrupoSelecaoModel {

    private final ConexaoSqlite conexao;

    public GrupoSelecaoModel(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: SELECT - Retorna a lista de todas as relações salvas na tabela GrupoSelecao
     */
    public List<GrupoSelecaoPojo> listarRelacoes() {
        List<GrupoSelecaoPojo> listaRelacoes = new ArrayList<>();
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + GrupoSelecaoDDL.TABELA_NOME;
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    GrupoSelecaoPojo relacao = mapearCursorParaPojo(cursor);
                    listaRelacoes.add(relacao);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return listaRelacoes;
    }

    /**
     * Método auxiliar privado encarregado de ler a linha atual do Cursor
     * e transformá-la diretamente no POJO mapeado coluna-atributo.
     */
    private GrupoSelecaoPojo mapearCursorParaPojo(Cursor cursor) {
        GrupoSelecaoPojo grupoSelecao = new GrupoSelecaoPojo();

        int idxId = cursor.getColumnIndexOrThrow(GrupoSelecaoDDL.COL_ID);
        int idxGrupo = cursor.getColumnIndexOrThrow(GrupoSelecaoDDL.COL_ID_GRUPO);
        int idxSelecao = cursor.getColumnIndexOrThrow(GrupoSelecaoDDL.COL_ID_SELECAO);

        grupoSelecao.setId(cursor.getInt(idxId));
        grupoSelecao.setIdGrupo(cursor.getInt(idxGrupo));
        grupoSelecao.setIdSelecao(cursor.getInt(idxSelecao));

        return grupoSelecao;
    }
}