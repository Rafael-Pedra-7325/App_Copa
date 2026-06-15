package com.example.appcopa.TBancoDados; 

public class GrupoPojo {

    private int id;
    private String letra;      // Identificador do grupo (Ex: "A", "B", "C")
    private int idSelecao;     // FK para a tabela Selecoes (id)
    private String codSelecao; // FK para a tabela Selecoes (codigo)

    // Construtor Vazio
    public GrupoPojo() {
    }

    // Construtor Completo
    public GrupoPojo(int id, String letra, int idSelecao, String codSelecao) {
        this.id = id;
        this.letra = letra;
        this.idSelecao = idSelecao;
        this.codSelecao = codSelecao;
    }

    // --- MÉTODOS GETTERS E SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getIdSelecao() {
        return idSelecao;
    }

    public void setIdSelecao(int idSelecao) {
        this.idSelecao = idSelecao;
    }

    public String getCodSelecao() {
        return codSelecao;
    }

    public void setCodSelecao(String codSelecao) {
        this.codSelecao = codSelecao;
    }

    @Override
    public String toString() {
        return "GrupoPojo{" +
                "id=" + id +
                ", letra='" + letra + '\'' +
                ", idSelecao=" + idSelecao +
                ", codSelecao='" + codSelecao + '\'' +
                '}';
    }
}