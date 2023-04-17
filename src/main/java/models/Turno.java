package models;

import java.util.Set;
import java.util.TreeSet;

public class Turno {

    private final String idTurno;
    private final String turmas;
    private final Integer numInscritos;
    private Set<Aula> aulas;

    public Turno(String idTurno, String turmas, Integer numInscritos) {
        this.idTurno = idTurno;
        this.turmas = turmas;
        this.numInscritos = numInscritos;
        this.aulas = new TreeSet<>();
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

    public Set<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(Set<Aula> aulas) {
        this.aulas = aulas;
    }
}
