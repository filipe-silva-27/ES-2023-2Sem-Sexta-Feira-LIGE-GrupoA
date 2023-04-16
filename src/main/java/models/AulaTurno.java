package models;

import java.time.LocalTime;
import java.util.Date;

public class AulaTurno {

    private Date dia;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Date data;
    private String sala;
    private int lotacao;
    private Turno turno;
    private UnidadeCurricular uc;

public AulaTurno(Date dia, LocalTime horaInicio, LocalTime horaFim, Date data, String sala, int lotacao, Turno turno, UnidadeCurricular uc) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.data = data;
        this.sala = sala;
        this.lotacao = lotacao;
        this.turno = turno;
        this.uc = uc;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(final Date dia) {
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(final LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(final LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public Date getData() {
        return data;
    }

    public void setData(final Date data) {
        this.data = data;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(final String sala) {
        this.sala = sala;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(final int lotacao) {
        this.lotacao = lotacao;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(final Turno turno) {
        this.turno = turno;
    }

    public UnidadeCurricular getUc() {
        return uc;
    }

    public void setUc(final UnidadeCurricular uc) {
        this.uc = uc;
    }
}
