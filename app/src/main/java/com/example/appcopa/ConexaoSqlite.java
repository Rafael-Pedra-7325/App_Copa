package com.example.appcopa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appcopa.TBancoDados.ConfiguracaoDDL;
import com.example.appcopa.TBancoDados.GrupoDDL;
import com.example.appcopa.TBancoDados.GrupoSelecaoDDL;
import com.example.appcopa.TBancoDados.LinguaDDL;
import com.example.appcopa.TBancoDados.SelecaoDDL;

public class ConexaoSqlite extends SQLiteOpenHelper {

    // Configurações do Banco de Dados
    private static final String NOME_BANCO = "DB\\CopaApp.db";
    private static final int VERSAO_BANCO = 1;

    // Constantes da Tabela Estadios
    public static final String TABELA_ESTADIOS = "Estadios";
    public static final String COL_ID = "Id";
    public static final String COL_CODIGO_ENUM = "Codigo_estadio_enum";
    public static final String COL_NOME = "Nome";
    public static final String COL_LOCAL = "Local";
    public static final String COL_DESCRICAO = "Descricao";
    public static final String COL_CAPACIDADE = "Capacidade";
    public static final String COL_DATA_FUNDACAO = "data_fundacao"; // Alterado hífen para underline (boa prática)
    public static final String COL_IMAGEM = "imagem";
    public static final String COL_LINK_MAPS = "link_maps"; // Alterado hífen para underline

    // Construtor da classe
    public ConexaoSqlite(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    //Configuração pra foreign key
    @Override
    public void onConfigure(SQLiteDatabase db)
    {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    // Método chamado na primeira vez que o banco de dados é criado
    @Override
    public void onCreate(SQLiteDatabase db) {
        String scriptCriacaoTabela = "CREATE TABLE IF NOT EXISTS " + TABELA_ESTADIOS + " ("
                + COL_ID + " INTEGER NOT NULL UNIQUE, "
                + COL_CODIGO_ENUM + " INTEGER NOT NULL UNIQUE, "
                + COL_NOME + " TEXT NOT NULL, "
                + COL_LOCAL + " TEXT NOT NULL, "
                + COL_DESCRICAO + " TEXT NOT NULL, "
                + COL_CAPACIDADE + " INTEGER NOT NULL, "
                + COL_DATA_FUNDACAO + " TEXT NOT NULL, "
                + COL_IMAGEM + " BLOB NOT NULL UNIQUE, "
                + COL_LINK_MAPS + " TEXT NOT NULL, "
                + "PRIMARY KEY(" + COL_ID + " AUTOINCREMENT)" // Define o Id como autoincremento
                + ");";

        // Executa o script SQL de criação
        db.execSQL(scriptCriacaoTabela);
        db.execSQL(LinguaDDL.SCRIPT_CRIACAO);
        db.execSQL(ConfiguracaoDDL.SCRIPT_CRIACAO);
        db.execSQL(SelecaoDDL.SCRIPT_CRIACAO);
        db.execSQL(GrupoDDL.SCRIPT_CRIACAO);
        db.execSQL(GrupoSelecaoDDL.SCRIPT_CRIACAO);
    }

    // Método chamado quando você altera a VERSAO_BANCO (útil para atualizações futuras)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Por enquanto, deleta a tabela antiga e recria uma nova
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ESTADIOS);
        db.execSQL(LinguaDDL.SCRIPT_DELETAR);
        db.execSQL(ConfiguracaoDDL.SCRIPT_DELETAR);
        db.execSQL(SelecaoDDL.SCRIPT_DELETAR);
        db.execSQL(GrupoDDL.SCRIPT_DELETAR);
        db.execSQL(GrupoSelecaoDDL.SCRIPT_DELETAR);
        onCreate(db);
    }
}