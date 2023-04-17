package models;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class Aula implements Comparable<Aula>{

    private DataAula dataAula;
    private Sala sala;

    public Aula(DataAula dataAula, Sala sala) {
        this.dataAula = dataAula;
        this.sala = sala;
    }

    public DataAula getDataAula() {
        return dataAula;
    }

    public void setDataAula(DataAula dataAula) {
        this.dataAula = dataAula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
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
        if (!(o instanceof Aula)) return false;
        Aula that = (Aula) o;
        return Objects.equals(dataAula, that.dataAula) && Objects.equals(sala, that.sala);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataAula, sala);
    }

    @Override
    public String toString() {
        return "Aula{" +
                "dataAula=" + dataAula +
                ", sala=" + sala.getNome() +
                '}';
    }

}
