package models;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * Classe que representa uma data de aula.
 */
public class DataAula {
    private final DiaSemana diaSemana;
    private final LocalTime horaInicio;
    private final LocalTime horaFim;
    private final Date data;

    /**
     * Construtor que cria um objeto DataAula com os parâmetros especificados.
     *
     * @param diaSemana  o dia da semana da aula
     * @param horaInicio a hora de início da aula
     * @param horaFim    a hora de fim da aula
     * @param data       a data da aula
     */
    public DataAula(DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFim, Date data) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.data = data;
    }

    /**
     * Obtém o dia da semana da aula.
     * @return o dia da semana da aula
     */
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    /**
     * Obtém a hora de início da aula.
     * @return a hora de início da aula
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Obtém a hora de fim da aula.
     * @return a hora de fim da aula
     */
    public LocalTime getHoraFim() {
        return horaFim;
    }

    /**
     * Obtém a data da aula.
     * @return a data da aula
     */
    public Date getData() {
        return data;
    }

    /**
     * Verifica se este objeto DataAula é igual a outro objeto.
     * @param o o objeto a ser comparado
     * @return true se os objetos forem iguais, false caso contrário
     */
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

    /**
     * Retorna o código de hash deste objeto DataAula.
     * @return o código de hash do objeto DataAula
     */
    @Override
    public int hashCode() {
        return Objects.hash(diaSemana, horaInicio, horaFim, data);
    }

    /**
     * Retorna uma representação em formato de string deste objeto DataAula.
     * @return uma string que representa o objeto DataAula
     */
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
