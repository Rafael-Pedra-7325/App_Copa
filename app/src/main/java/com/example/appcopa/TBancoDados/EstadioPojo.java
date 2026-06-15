package com.example.appcopa.TBancoDados; 

public class EstadioPojo {

    // Atributos privados correspondentes às colunas do banco de dados
    private int id;
    private int codigoEstadioEnum;
    private String nome;
    private String local;
    private String descricao;
    private int capacidade;
    private String dataFundacao;
    private byte[] imagem; // BLOB mapeado como array de bytes em Java
    private String linkMaps;

    // Construtor Vazio (Necessário para instanciação genérica e boas práticas)
    public EstadioPojo() {
    }

    // Construtor Completo
    public EstadioPojo(int id, int codigoEstadioEnum, String nome, String local, String descricao,
                       int capacidade, String dataFundacao, byte[] imagem, String linkMaps) {
        this.id = id;
        this.codigoEstadioEnum = codigoEstadioEnum;
        this.nome = nome;
        this.local = local;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.dataFundacao = dataFundacao;
        this.imagem = imagem;
        this.linkMaps = linkMaps;
    }

    // --- MÉTODOS GETTERS E SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoEstadioEnum() {
        return codigoEstadioEnum;
    }

    public void setCodigoEstadioEnum(int codigoEstadioEnum) {
        this.codigoEstadioEnum = codigoEstadioEnum;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getLinkMaps() {
        return linkMaps;
    }

    public void setLinkMaps(String linkMaps) {
        this.linkMaps = linkMaps;
    }

    // Método toString() para facilitar o log/debug do seu grupo no Android Studio
    @Override
    public String toString() {
        return "EstadioPojo{" +
                "id=" + id +
                ", codigoEstadioEnum=" + codigoEstadioEnum +
                ", nome='" + nome + '\'' +
                ", local='" + local + '\'' +
                ", capacidade=" + capacidade +
                '}';
    }
}