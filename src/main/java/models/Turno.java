package models;

import java.util.LinkedList;
import java.util.List;

public class Turno {

    private final String idTurno;
    private final String turmas;
    private final Integer numInscritos;
    private List<Aula> aulas;

    public Turno(String idTurno, String turmas, Integer numInscritos) {
        this.idTurno = idTurno;
        this.turmas = turmas;
        this.numInscritos = numInscritos;
        this.aulas = new LinkedList<>();
    }

    public String getIdTurno() {
        return idTurno;
    }

    public String getTurmas() {
        return turmas;
    }

    public Integer getNumInscritos() {
        return numInscritos;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }
}
