package com.example.appcopa.TBancoDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcopa.ConexaoSqlite;

import java.util.ArrayList;
import java.util.List;

public class EstadioModel {

    private ConexaoSqlite conexao;
    private SQLiteDatabase db;

    // Construtor da classe Model
    public EstadioModel(Context context) {
        // Inicializa a classe de conexão que criamos anteriormente
        this.conexao = new ConexaoSqlite(context);
    }

    /**
     * Consulta um único estádio a partir do seu ID.
     * @param id ID do estádio desejado
     * @return Objeto EstadioPojo populado ou null caso não encontre
     */
    public EstadioPojo buscarPorId(int id) {
        EstadioPojo estadio = null;
        Cursor cursor = null;

        try {
            // Abre o banco em modo leitura
            db = conexao.getReadableDatabase();

            // Monta a query filtrando pelo ID de forma segura (prevenindo SQL Injection)
            String query = "SELECT * FROM " + ConexaoSqlite.TABELA_ESTADIOS
                    + " WHERE " + ConexaoSqlite.COL_ID + " = ?";

            cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

            // Se encontrou o registro, move o cursor para a primeira posição
            if (cursor.moveToFirst()) {
                estadio = mapearCursorParaPojo(cursor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Garante o fechamento do cursor e do banco de dados
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }

        return estadio;
    }

    /**
     * Recupera todos os estádios cadastrados na tabela.
     * @return Lista contendo os objetos EstadioPojo
     */
    public List<EstadioPojo> buscarTodos() {
        List<EstadioPojo> listaEstadios = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Abre o banco em modo leitura
            db = conexao.getReadableDatabase();

            // Consulta todos os registros da tabela
            String query = "SELECT * FROM " + ConexaoSqlite.TABELA_ESTADIOS;
            cursor = db.rawQuery(query, null);

            // Percorre o cursor enquanto houver um próximo registro
            if (cursor.moveToFirst()) {
                do {
                    EstadioPojo estadio = mapearCursorParaPojo(cursor);
                    listaEstadios.add(estadio);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Garante o fechamento do cursor e do banco de dados
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }

        return listaEstadios;
    }

    /**
     * Método auxiliar privado para extrair os dados do Cursor e popular o POJO.
     * Evita repetição de código nos métodos de busca.
     */
    private EstadioPojo mapearCursorParaPojo(Cursor cursor) {
        EstadioPojo estadio = new EstadioPojo();

        // Recupera o índice de cada coluna para garantir a busca correta dos tipos
        int idxId = cursor.getColumnIndexOrThrow(ConexaoSqlite.COL_ID);
        int idxCodigo = cursor.getColumnIndexOrThrow(ConexaoSqlite.COL_CODIGO_ENUM);
        int idxNome = cursor.getColumnIndexOrThrow(ConexaoSqlite.COL_NOME);
        int idxLocal = cursor.getColumnIndexOrThrow(ConexaoSqlite.COL_LOCAL);
        int idxDescricao = cursor.getColumnIndexOrThrow(ConexaoSqlite.COL_DESCRICAO);
        int idxCapacidade = cursor.getColumnIndexOrThrow(ConexaoSqlite.COL_CAPACIDADE);
        int idxDataFundacao = cursor.getColumnIndexOrThrow(ConexaoSqlite.COL_DATA_FUNDACAO);
        int idxImagem = cursor.getColumnIndexOrThrow(ConexaoSqlite.COL_IMAGEM);
        int idxLinkMaps = cursor.getColumnIndexOrThrow(ConexaoSqlite.COL_LINK_MAPS);

        // Popula o objeto com os dados da linha atual do banco
        estadio.setId(cursor.getInt(idxId));
        estadio.setCodigoEstadioEnum(cursor.getInt(idxCodigo));
        estadio.setNome(cursor.getString(idxNome));
        estadio.setLocal(cursor.getString(idxLocal));
        estadio.setDescricao(cursor.getString(idxDescricao));
        estadio.setCapacidade(cursor.getInt(idxCapacidade));
        estadio.setDataFundacao(cursor.getString(idxDataFundacao));
        estadio.setImagem(cursor.getBlob(idxImagem)); // Retorna o array de bytes (BLOB)
        estadio.setLinkMaps(cursor.getString(idxLinkMaps));

        return estadio;
    }
}