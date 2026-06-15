package com.example.appcopa.TBancoDados; // Substitua pelo package real do seu projeto

public class GrupoSelecaoPojo {

    private int id;
    private int idGrupo;   // FK apontando para a tabela Grupo
    private int idSelecao; // FK apontando para a tabela Selecoes

    // Construtor Vazio
    public GrupoSelecaoPojo() {
    }

    // Construtor Completo
    public GrupoSelecaoPojo(int id, int idGrupo, int idSelecao) {
        this.id = id;
        this.idGrupo = idGrupo;
        this.idSelecao = idSelecao;
    }

    // --- MÉTODOS GETTERS E SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdSelecao() {
        return idSelecao;
    }

    public void setIdSelecao(int idSelecao) {
        this.idSelecao = idSelecao;
    }

    @Override
    public String toString() {
        return "GrupoSelecaoPojo{" +
                "id=" + id +
                ", idGrupo=" + idGrupo +
                ", idSelecao=" + idSelecao +
                '}';
    }
}