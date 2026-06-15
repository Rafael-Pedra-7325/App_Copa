package com.example.appcopa.TBancoDados; // Substitua pelo package real do seu projeto

public class GrupoSelecaoDDL {
    public static final String TABELA_NOME = "GrupoSelecao";
    public static final String COL_ID = "id";
    public static final String COL_ID_GRUPO = "id_grupo";
    public static final String COL_CODIGO_SELECAO = "codigo_selecao_enum";
    public static final String COL_PONTOS = "pontos";
    public static final String COL_JOGOS = "jogos";
    public static final String COL_VITORIAS = "vitorias";
    public static final String COL_EMPATES = "empates";
    public static final String COL_DERROTAS = "derrotas";
    public static final String COL_GOLS_PRO = "gols_pro";
    public static final String COL_GOLS_CONTRA = "gols_contra";
    public static final String COL_SALDO_GOLS = "saldo_gols";

    public static final String SCRIPT_CRIACAO = "CREATE TABLE IF NOT EXISTS " + TABELA_NOME + " ("
            + COL_ID + " INTEGER NOT NULL UNIQUE, "
            + COL_ID_GRUPO + " INTEGER NOT NULL, "
            + COL_CODIGO_SELECAO + " TEXT NOT NULL, "
            + COL_PONTOS + " INTEGER NOT NULL, "
            + COL_JOGOS + " INTEGER NOT NULL, "
            + COL_VITORIAS + " INTEGER NOT NULL, "
            + COL_EMPATES + " INTEGER NOT NULL, "
            + COL_DERROTAS + " INTEGER NOT NULL, "
            + COL_GOLS_PRO + " INTEGER NOT NULL, "
            + COL_GOLS_CONTRA + " INTEGER NOT NULL, "
            + COL_SALDO_GOLS + " INTEGER NOT NULL, "
            + "PRIMARY KEY(" + COL_ID + " AUTOINCREMENT)"
            + ");";

    public static final String SCRIPT_DELETAR = "DROP TABLE IF EXISTS " + TABELA_NOME;
}