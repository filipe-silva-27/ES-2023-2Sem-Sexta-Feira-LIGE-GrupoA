package models;

import java.time.LocalTime;
import java.util.Date;

public class Aula {

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
}
