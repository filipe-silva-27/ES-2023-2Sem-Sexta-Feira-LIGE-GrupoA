package models;

import java.time.LocalTime;
import java.util.Date;

public class DataAula {
    private final DiaSemana diaSemana;
    private final LocalTime horaInicio;
    private final LocalTime horaFim;
    private final Date data;

    public DataAula(DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFim, Date data) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.data = data;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public Date getData() {
        return data;
    }
}
