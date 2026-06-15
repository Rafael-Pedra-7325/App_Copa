package com.example.appcopa.TBancoDados;

public class ConfiguracaoDDL {
    // Nome da tabela e das colunas
    public static final String TABELA_NOME = "Configuracao";
    public static final String COL_ID = "id";
    public static final String COL_LINGUAS_ATIVA = "lingua_ativa";
    public static final String COL_NOTIFICACOES_ATIVAS = "notificacoes_ativas";
    public static final String COL_MODO_NOTURNO = "modo_noturno";
    public static final String COL_ULTIMA_ATUALIZACAO = "ultima_atualizacao";
    public static final String COL_COPA_ATIVA = "copa_ativa";

    // Script DDL para criação da tabela respeitando a chave estrangeira
    public static final String SCRIPT_CRIACAO = "CREATE TABLE IF NOT EXISTS " + TABELA_NOME + " ("
            + COL_ID + " INTEGER NOT NULL UNIQUE, "
            + COL_LINGUAS_ATIVA + " TEXT NOT NULL UNIQUE, "
            + COL_NOTIFICACOES_ATIVAS + " INTEGER, "
            + COL_MODO_NOTURNO + " INTEGER, "
            + COL_ULTIMA_ATUALIZACAO + " TEXT, "
            + COL_COPA_ATIVA + " TEXT, "
            + "PRIMARY KEY(" + COL_ID + " AUTOINCREMENT), "
            + "FOREIGN KEY(" + COL_LINGUAS_ATIVA + ") REFERENCES Linguas(cod_lingua)"
            + ");";

    public static final String SCRIPT_DELETAR = "DROP TABLE IF EXISTS " + TABELA_NOME;
}
