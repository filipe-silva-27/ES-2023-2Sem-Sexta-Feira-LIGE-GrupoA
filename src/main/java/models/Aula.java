package models;

import java.util.Objects;

public class Aula implements Comparable<Aula>{

    private UnidadeCurricular uc;
    private String turno;
    private String turma;
    private Integer numInscritos;
    private DataAula dataAula = null;
    private String sala;
    private Integer lotacao;

    public Aula(UnidadeCurricular uc,String turno, String turma, Integer numInscritos,String sala, Integer lotacao) {
        this.uc = uc;
        this.turno = turno;
        this.turma = turma;
        this.numInscritos = numInscritos;
        this.sala = sala;
        this.lotacao = lotacao;
    }

    public UnidadeCurricular getUc() {
        return uc;
    }

    public DataAula getDataAula() {
        return dataAula;
    }

    public void setDataAula(DataAula dataAula) {
        this.dataAula = dataAula;
    }

    public String getTurno() {
        return turno;
    }

    public String getTurma() {
        return turma;
    }

    public Integer getNumInscritos() {
        return numInscritos;
    }

    public String getSala() {
        return sala;
    }

    public Integer getLotacao() {
        return lotacao;
    }

    @Override
    public int compareTo(Aula o) {
        int cmp = this.getDataAula().getData().compareTo(o.getDataAula().getData());
        if (cmp == 0) {
            cmp = this.getDataAula().getHoraInicio().compareTo(o.getDataAula().getHoraInicio());
        }
        return cmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(turno, aula.turno) &&
                Objects.equals(turma, aula.turma) &&
                Objects.equals(numInscritos, aula.numInscritos) &&
                Objects.equals(dataAula, aula.dataAula) &&
                Objects.equals(sala, aula.sala) &&
                Objects.equals(lotacao, aula.lotacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataAula, sala);
    }

    @Override
    public String toString() {
        return "Aula{" +
                "turno='" + turno + '\'' +
                ", turma='" + turma + '\'' +
                ", numInscritos=" + numInscritos +
                ", dataAula=" + dataAula +
                ", sala='" + sala + '\'' +
                ", lotacao=" + lotacao +
                '}';
    }
}
