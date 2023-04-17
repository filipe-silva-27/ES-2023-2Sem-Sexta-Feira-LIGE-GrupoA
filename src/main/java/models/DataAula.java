package models;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataAula)) return false;
        DataAula dataAula = (DataAula) o;
        return diaSemana == dataAula.diaSemana &&
                horaInicio.equals(dataAula.horaInicio) &&
                horaFim.equals(dataAula.horaFim) &&
                data.equals(dataAula.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diaSemana, horaInicio, horaFim, data);
    }

    @Override
    public String toString() {
        return "DataAula{" +
                "diaSemana=" + diaSemana +
                ", horaInicio=" + horaInicio +
                ", horaFim=" + horaFim +
                ", data=" + data +
                '}';
    }
}
