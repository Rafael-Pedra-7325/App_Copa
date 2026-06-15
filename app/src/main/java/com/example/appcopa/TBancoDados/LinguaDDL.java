package com.example.appcopa.TBancoDados; 

public class LinguaDDL {
    // Nome da tabela e das colunas como constantes para evitar erros de digitação
    public static final String TABELA_NOME = "Linguas";
    public static final String COL_ID = "id";
    public static final String COL_NOME = "nome";
    public static final String COL_ESCOLHA = "escolha";

    public static final String COL_COD_LINGA="cod_lingua";

    // O script DDL puro formatado para o SQLite do Android
    public static final String SCRIPT_CRIACAO = "CREATE TABLE IF NOT EXISTS " + TABELA_NOME + " ("
            + COL_ID + " INTEGER NOT NULL UNIQUE, "
            + COL_NOME + " TEXT NOT NULL UNIQUE, "
            + COL_ESCOLHA + " INTEGER, "
            + COL_COD_LINGA + "TEXT NOT NULL,"
            + "PRIMARY KEY(" + COL_ID + " AUTOINCREMENT)"
            + ");";

    public static final String SCRIPT_DELETAR = "DROP TABLE IF EXISTS " + TABELA_NOME;
}