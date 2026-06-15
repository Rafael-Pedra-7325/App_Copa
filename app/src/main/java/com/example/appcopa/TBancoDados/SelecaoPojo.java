package com.example.appcopa.TBancoDados; 

public class SelecaoPojo {

    private int id;
    private String codigo; // Sigla de 3 letras da FIFA (Ex: "BRA")
    private String nome;   // Nome do país (Ex: "Brasil")
    private int qtJogadores;
    private String grupo;  // Letra do grupo na Copa (Ex: "Grupo A")
    private String confederacao; // Nome da confederação (Ex: "CONMEBOL")

    // Construtor Vazio
    public SelecaoPojo() {
    }

    // Construtor Completo
    public SelecaoPojo(int id, String codigo, String nome, int qtJogadores, String grupo, String confederacao) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.qtJogadores = qtJogadores;
        this.grupo = grupo;
        this.confederacao = confederacao;
    }

    // --- MÉTODOS GETTERS E SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtJogadores() {
        return qtJogadores;
    }

    public void setQtJogadores(int qtJogadores) {
        this.qtJogadores = qtJogadores;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getConfederacao() {
        return confederacao;
    }

    public void setConfederacao(String confederacao) {
        this.confederacao = confederacao;
    }

    // Auxiliar para depuração via Logcat
    @Override
    public String toString() {
        return "SelecaoPojo{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", qtJogadores=" + qtJogadores +
                ", grupo='" + grupo + '\'' +
                ", confederacao='" + confederacao + '\'' +
                '}';
    }
}