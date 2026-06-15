package com.example.appcopa.TBancoDados; // Substitua pelo package real do seu projeto

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import com.example.appcopa.ConexaoSqlite;

public class PartidasModel {

    private final ConexaoSqlite conexao;

    public PartidasModel(Context context) {
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * DML: SELECT - Retorna o calendário completo de partidas em formato de Lista de POJOs
     * @return Lista de partidas ordenada por data e horário
     */
    public List<PartidasPojo> listarCalendarioCompleto() {
        List<PartidasPojo> listaPartidas = new ArrayList<>();
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + PartidasDDL.TABELA_NOME
                    + " ORDER BY " + PartidasDDL.COL_DATA + " ASC, " + PartidasDDL.COL_HORARIO + " ASC";

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    PartidasPojo partida = mapearCursorParaPojo(cursor);
                    listaPartidas.add(partida);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return listaPartidas;
    }

    /**
     * DML: SELECT - Retorna as partidas de um grupo específico em formato de Lista de POJOs
     * @param grupo Letra do grupo (Ex: "A", "B")
     * @return Lista contendo os confrontos daquela chave da Copa
     */
    public List<PartidasPojo> listarPartidasPorGrupo(String grupo) {
        List<PartidasPojo> listaPartidas = new ArrayList<>();
        SQLiteDatabase db = conexao.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + PartidasDDL.TABELA_NOME
                    + " WHERE " + PartidasDDL.COL_GRUPO + " = ?"
                    + " ORDER BY " + PartidasDDL.COL_DATA + " ASC, " + PartidasDDL.COL_HORARIO + " ASC";

            cursor = db.rawQuery(query, new String[]{grupo});

            if (cursor.moveToFirst()) {
                do {
                    PartidasPojo partida = mapearCursorParaPojo(cursor);
                    listaPartidas.add(partida);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return listaPartidas;
    }

    /**
     * Método auxiliar privado encarregado de extrair os dados das colunas do Cursor
     * e transferi-los para uma nova instância estruturada do POJO.
     */
    private PartidasPojo mapearCursorParaPojo(Cursor cursor) {
        PartidasPojo pojo = new PartidasPojo();

        int idxId = cursor.getColumnIndexOrThrow(PartidasDDL.COL_ID);
        int idxGrupo = cursor.getColumnIndexOrThrow(PartidasDDL.COL_GRUPO);
        int idxSel1 = cursor.getColumnIndexOrThrow(PartidasDDL.COL_SELECAO1);
        int idxSel2 = cursor.getColumnIndexOrThrow(PartidasDDL.COL_SELECAO2);
        int idxData = cursor.getColumnIndexOrThrow(PartidasDDL.COL_DATA);
        int idxHora = cursor.getColumnIndexOrThrow(PartidasDDL.COL_HORARIO);
        int idxEstadio = cursor.getColumnIndexOrThrow(PartidasDDL.COL_ESTADIO);
        int idxPlacar1 = cursor.getColumnIndexOrThrow(PartidasDDL.COL_PLACAR_SELECAO1);
        int idxPlacar2 = cursor.getColumnIndexOrThrow(PartidasDDL.COL_PLACAR_SELECAO2);
        int idxPlacarComposto = cursor.getColumnIndexOrThrow(PartidasDDL.COL_PLACAR);

        pojo.setId(cursor.getInt(idxId));
        pojo.setGrupo(cursor.getString(idxGrupo));
        pojo.setSelecao1(cursor.getString(idxSel1));
        pojo.setSelecao2(cursor.getString(idxSel2));
        pojo.setData(cursor.getString(idxData));
        pojo.setHorario(cursor.getString(idxHora));
        pojo.setEstadio(cursor.getString(idxEstadio));
        pojo.setPlacarSelecao1(cursor.getInt(idxPlacar1));
        pojo.setPlacarSelecao2(cursor.getInt(idxPlacar2));
        pojo.setPlacar(cursor.getString(idxPlacarComposto));

        return pojo;
    }
}