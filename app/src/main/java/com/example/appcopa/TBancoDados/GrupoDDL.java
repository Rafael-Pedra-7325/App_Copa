package com.example.appcopa.TBancoDados; 

public class GrupoDDL {
    // Nome da tabela e das colunas
    public static final String TABELA_NOME = "Grupo";
    public static final String COL_ID = "id";
    public static final String COL_LETRA = "letra";
    public static final String COL_ID_SELECAO = "id_selecao";
    public static final String COL_COD_SELECAO = "cod_selecao";

    // Script DDL de criação contendo as chaves estrangeiras apontando para a tabela Selecoes
    public static final String SCRIPT_CRIACAO = "CREATE TABLE IF NOT EXISTS " + TABELA_NOME + " ("
            + COL_ID + " INTEGER NOT NULL UNIQUE, "
            + COL_LETRA + " TEXT NOT NULL, "
            + COL_ID_SELECAO + " INTEGER NOT NULL, "
            + COL_COD_SELECAO + " TEXT NOT NULL, "
            + "PRIMARY KEY(" + COL_ID + " AUTOINCREMENT), "
            + "FOREIGN KEY(" + COL_COD_SELECAO + ") REFERENCES Selecoes(codigo), "
            + "FOREIGN KEY(" + COL_ID_SELECAO + ") REFERENCES Selecoes(id)"
            + ");";

    public static final String SCRIPT_DELETAR = "DROP TABLE IF EXISTS " + TABELA_NOME;
}