package com.example.appcopa.TBancoDados; 

public class ConfiguracaoPojo {

    private int id;
    private int linguaAtiva; // Mapeia a coluna lingua_ativa (ID da língua estrangeira)
    private int notificacoesAtivas; // 1 para ativado, 0 para desativado
    private int modoNoturno; // 1 para ativado, 0 para desativado
    private String ultimaAtualizacao;
    private String copaAtiva;

    // Construtor Vazio
    public ConfiguracaoPojo() {
    }

    // Construtor Completo
    public ConfiguracaoPojo(int id, int linguaAtiva, int notificacoesAtivas, int modoNoturno, String ultimaAtualizacao, String copaAtiva) {
        this.id = id;
        this.linguaAtiva = linguaAtiva;
        this.notificacoesAtivas = notificacoesAtivas;
        this.modoNoturno = modoNoturno;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.copaAtiva = copaAtiva;
    }

    // --- MÉTODOS GETTERS E SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLinguaAtiva() {
        return linguaAtiva;
    }

    public void setLinguaAtiva(int linguaAtiva) {
        this.linguaAtiva = linguaAtiva;
    }

    public int getNotificacoesAtivas() {
        return notificacoesAtivas;
    }

    public void setNotificacoesAtivas(int notificacoesAtivas) {
        this.notificacoesAtivas = notificacoesAtivas;
    }

    public int getModoNoturno() {
        return modoNoturno;
    }

    public void setModoNoturno(int modoNoturno) {
        this.modoNoturno = modoNoturno;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public String getCopaAtiva() {
        return copaAtiva;
    }

    public void setCopaAtiva(String copaAtiva) {
        this.copaAtiva = copaAtiva;
    }

    @Override
    public String toString() {
        return "ConfiguracaoPojo{" +
                "id=" + id +
                ", linguaAtiva=" + linguaAtiva +
                ", notificacoesAtivas=" + notificacoesAtivas +
                ", modoNoturno=" + modoNoturno +
                ", ultimaAtualizacao='" + ultimaAtualizacao + '\'' +
                ", copaAtiva='" + copaAtiva + '\'' +
                '}';
    }
}