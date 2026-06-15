package com.example.appcopa.TBancoDados; // Substitua pelo package real do seu projeto

public class PartidasDDL {
    // Nome da tabela e das colunas
    public static final String TABELA_NOME = "Partidas";
    public static final String COL_ID = "id";
    public static final String COL_GRUPO = "grupo";
    public static final String COL_SELECAO1 = "selecao1";
    public static final String COL_SELECAO2 = "selecao2";
    public static final String COL_DATA = "data";
    public static final String COL_HORARIO = "horario";
    public static final String COL_ESTADIO = "estadio";
    public static final String COL_PLACAR_SELECAO1 = "placar_selecao1";
    public static final String COL_PLACAR_SELECAO2 = "placar_selecao2";
    public static final String COL_PLACAR = "placar";

    // Script DDL limpo para criação da tabela no SQLite Browser
    public static final String SCRIPT_CRIACAO = "CREATE TABLE IF NOT EXISTS " + TABELA_NOME + " ("
            + COL_ID + " INTEGER NOT NULL UNIQUE, "
            + COL_GRUPO + " TEXT NOT NULL, "
            + COL_SELECAO1 + " TEXT NOT NULL, "
            + COL_SELECAO2 + " TEXT NOT NULL, "
            + COL_DATA + " TEXT NOT NULL, "
            + COL_HORARIO + " TEXT NOT NULL, "
            + COL_ESTADIO + " TEXT NOT NULL, "
            + COL_PLACAR_SELECAO1 + " INTEGER NOT NULL, "
            + COL_PLACAR_SELECAO2 + " INTEGER NOT NULL, "
            + COL_PLACAR + " TEXT NOT NULL, "
            + "PRIMARY KEY(" + COL_ID + " AUTOINCREMENT)"
            + ");";

    public static final String SCRIPT_DELETAR = "DROP TABLE IF EXISTS " + TABELA_NOME;
}