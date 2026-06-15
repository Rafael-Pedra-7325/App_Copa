package com.example.appcopa.TBancoDados; 

public class SelecaoDDL {
    // Nome da tabela e das colunas
    public static final String TABELA_NOME = "Selecoes";
    public static final String COL_ID = "id";
    public static final String COL_CODIGO = "codigo";
    public static final String COL_NOME = "nome";
    public static final String COL_QT_JOGADORES = "qt_jogadores";
    public static final String COL_GRUPO = "grupo";
    public static final String COL_CONFEDERACAO = "confederacao";

    // Script DDL puro e sem constraints complexas para o SQLite Browser
    public static final String SCRIPT_CRIACAO = "CREATE TABLE IF NOT EXISTS " + TABELA_NOME + " ("
            + COL_ID + " INTEGER NOT NULL UNIQUE, "
            + COL_CODIGO + " TEXT NOT NULL UNIQUE, "
            + COL_NOME + " TEXT NOT NULL UNIQUE, "
            + COL_QT_JOGADORES + " INTEGER NOT NULL, "
            + COL_GRUPO + " TEXT NOT NULL, "
            + COL_CONFEDERACAO + " TEXT NOT NULL, "
            + "PRIMARY KEY(" + COL_ID + " AUTOINCREMENT)"
            + ");";

    public static final String SCRIPT_DELETAR = "DROP TABLE IF EXISTS " + TABELA_NOME;
}