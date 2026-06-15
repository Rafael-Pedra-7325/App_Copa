package com.example.appcopa.TBancoDados; // Substitua pelo package real do seu projeto

public class PartidasPojo {

    private int id;
    private String grupo;
    private String selecao1;
    private String selecao2;
    private String data;
    private String horario;
    private String estadio;
    private int placarSelecao1;
    private int placarSelecao2;
    private String placar; // Placar formatado (Ex: "1x2")

    // Construtor Vazio
    public PartidasPojo() {
    }

    // Construtor Completo
    public PartidasPojo(int id, String grupo, String selecao1, String selecao2, String data,
                       String horario, String estadio, int placarSelecao1, int placarSelecao2, String placar) {
        this.id = id;
        this.grupo = grupo;
        this.selecao1 = selecao1;
        this.selecao2 = selecao2;
        this.data = data;
        this.horario = horario;
        this.estadio = estadio;
        this.placarSelecao1 = placarSelecao1;
        this.placarSelecao2 = placarSelecao2;
        this.placar = placar;
    }

    // --- MÉTODOS GETTERS E SETTERS ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    public String getSelecao1() { return selecao1; }
    public void setSelecao1(String selecao1) { this.selecao1 = selecao1; }

    public String getSelecao2() { return selecao2; }
    public void setSelecao2(String selecao2) { this.selecao2 = selecao2; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getEstadio() { return estadio; }
    public void setEstadio(String estadio) { this.estadio = estadio; }

    public int getPlacarSelecao1() { return placarSelecao1; }
    public void setPlacarSelecao1(int placarSelecao1) { this.placarSelecao1 = placarSelecao1; }

    public int getPlacarSelecao2() { return placarSelecao2; }
    public void setPlacarSelecao2(int placarSelecao2) { this.placarSelecao2 = placarSelecao2; }

    public String getPlacar() { return placar; }
    public void setPlacar(String placar) { this.placar = placar; }

    @Override
    public String toString() {
        return "PartidasPojo{" +
                "id=" + id +
                ", grupo='" + grupo + '\'' +
                ", selecao1='" + selecao1 + '\'' +
                ", selecao2='" + selecao2 + '\'' +
                ", data='" + data + '\'' +
                ", horario='" + horario + '\'' +
                ", estadio='" + estadio + '\'' +
                ", placarSelecao1=" + placarSelecao1 +
                ", placarSelecao2=" + placarSelecao2 +
                ", placar='" + placar + '\'' +
                '}';
    }
}