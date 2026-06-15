package com.example.appcopa.TBancoDados; 

public class LinguaPojo {

    private int id;
    private String nome;
    private int escolha;// 1 para o idioma ativo, 0 para inativo
    private String cod_lingua;

    // Construtor Vazio
    public LinguaPojo() {
    }

    // Construtor Completo
    public LinguaPojo(int id, String nome, int escolha, String cod_lingua) {
        this.id = id;
        this.nome = nome;
        this.escolha = escolha;
        this.cod_lingua=cod_lingua;
    }

    // --- MÉTODOS GETTERS E SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEscolha() {
        return escolha;
    }

    public void setEscolha(int escolha) {
        this.escolha = escolha;
    }

    public String getcod_lingua(String cod_lingua){ return cod_lingua; }

    public void setCod_lingua(String cod_lingua)
    {
        this.cod_lingua=cod_lingua;
    }

    // Método toString para ajudar nos logs/debugs do grupo
    @Override
    public String toString() {
        return "LinguaPojo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", escolha=" + escolha + '\'' +
                ", cod_lingua="+cod_lingua +
                '}';
    }
}