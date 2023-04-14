package models;

import java.util.List;

public class Turno {

    private final String id;
    private String turma;
    private int inscritos;
    private List<AulaUCTurno> aulas;

    private List<UnidadeCurricular> ucs;

    public Turno(String id, String turma, int inscritos, String dia, String horaInicio, String horaFim, String data, String sala, String lotacao) {
        this.id = id;
        this.turma = turma;
        this.inscritos = inscritos;
    }

    public String getId() {
        return id;
    }

    public String getTurma() {
        return turma;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    @Override
    public String toString() {
        return "Turno{" + "id=" + id + ", turma=" + turma + ", inscritos=" + inscritos + '}';
    }


}
