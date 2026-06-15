package com.example.appcopa.TBancoDados; 

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import com.example.appcopa.ConexaoSqlite;

public class GrupoModel {

    private final ConexaoSqlite conexao;

    public GrupoModel(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: SELECT - Consulta e retorna todas as seleções ligadas a uma letra de grupo específica
     * @param letra A letra do grupo desejado (Ex: "A")
     * @return Uma lista de objetos GrupoPojo populados
     */
    public List<GrupoPojo> listarIntegrantesDoGrupo(String letra) {
        List<GrupoPojo> listaIntegrantes = new ArrayList<>();
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + GrupoDDL.TABELA_NOME + " WHERE " + GrupoDDL.COL_LETRA + " = ?";
            cursor = db.rawQuery(query, new String[]{letra});

            if (cursor.moveToFirst()) {
                do {
                    GrupoPojo integrante = mapearCursorParaPojo(cursor);
                    listaIntegrantes.add(integrante);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return listaIntegrantes;
    }

    /**
     * Método auxiliar privado encarregado de ler a linha atual do Cursor
     * e transformá-la diretamente no POJO mapeado coluna-atributo.
     */
    private GrupoPojo mapearCursorParaPojo(Cursor cursor) {
        GrupoPojo grupo = new GrupoPojo();

        int idxId = cursor.getColumnIndexOrThrow(GrupoDDL.COL_ID);
        int idxLetra = cursor.getColumnIndexOrThrow(GrupoDDL.COL_LETRA);
        int idxIdSelecao = cursor.getColumnIndexOrThrow(GrupoDDL.COL_ID_SELECAO);
        int idxCodSelecao = cursor.getColumnIndexOrThrow(GrupoDDL.COL_COD_SELECAO);

        grupo.setId(cursor.getInt(idxId));
        grupo.setLetra(cursor.getString(idxLetra));
        grupo.setIdSelecao(cursor.getInt(idxIdSelecao));
        grupo.setCodSelecao(cursor.getString(idxCodSelecao));

        return grupo;
    }
}