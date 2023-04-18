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

    public boolean addAula(Aula aula){
        return aulas.add(aula);
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

    public Aula getAulaBy(final DataAula dataAula, final Sala salaAula) {
        for (Aula aula : aulas) {
            if (aula.getDataAula().equals(dataAula) && aula.getSala().equals(salaAula)) {
                return aula;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "idTurno='" + idTurno + '\'' +
                ", turmas='" + turmas + '\'' +
                ", numInscritos=" + numInscritos +
                ", aulas=" + aulas +
                '}';
    }
}
