package models;

import java.util.Objects;

/**
 * Classe que representa uma aula de uma unidade curricular.
 * Esta classe possui informações sobre a unidade curricular, turno, turma, número de inscritos,
 * data da aula, sala e lotação da sala.
 * Implementa a interface Comparable para permitir comparação entre aulas com base na data e hora de início.
 * Implementa os métodos equals, hashCode e toString para permitir comparação e representação textual adequada
 * dos objetos do tipo Aula.
 */
public class Aula implements Comparable<Aula>{

    private UnidadeCurricular uc;
    private String turno;
    private String turma;
    private Integer numInscritos;
    private DataAula dataAula = null;
    private String sala;
    private Integer lotacao;

    /**
     * Construtor da classe Aula.
     *
     * @param uc Unidade curricular da aula
     * @param turno Turno da aula
     * @param turma Turma da aula
     * @param numInscritos Número de inscritos na aula
     * @param sala Sala onde ocorre a aula
     * @param lotacao Lotação da sala
     */
    public Aula(UnidadeCurricular uc,String turno, String turma, Integer numInscritos,String sala, Integer lotacao) {
        this.uc = uc;
        this.turno = turno;
        this.turma = turma;
        this.numInscritos = numInscritos;
        this.sala = sala;
        this.lotacao = lotacao;
    }

    /**
     * Obtém a unidade curricular da aula.
     * @return Unidade curricular da aula
     */
    public UnidadeCurricular getUc() {
        return uc;
    }

    /**
     * Obtém a data da aula.
     * @return Data da aula
     */
    public DataAula getDataAula() {
        return dataAula;
    }

    /**
     * Define a data da aula.
     * @param dataAula Data da aula
     */
    public void setDataAula(DataAula dataAula) {
        this.dataAula = dataAula;
    }

    /**
     * Obtém o turno da aula.
     * @return Turno da aula
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Obtém a turma da aula.
     * @return Turma da aula
     */
    public String getTurma() {
        return turma;
    }

    /**
     * Obtém o número de inscritos na aula.
     * @return Número de inscritos na aula
     */
    public Integer getNumInscritos() {
        return numInscritos;
    }

    /**
     * Obtém a sala onde ocorre a aula.
     * @return Sala onde ocorre a aula
     */
    public String getSala() {
        return sala;
    }

    /**
     * Obtém a lotação da sala.
     * @return Lotação da sala
     */
    public Integer getLotacao() {
        return lotacao;
    }

    /**
     * Compara dois objetos Aula com base na sua data e hora de início.
     * @param o o objeto Aula a ser comparado
     * @return um valor inteiro que indica a ordem dos dois objetos
     */
    @Override
    public int compareTo(Aula o) {
        int cmp = this.getDataAula().getData().compareTo(o.getDataAula().getData());
        if (cmp == 0) {
            cmp = this.getDataAula().getHoraInicio().compareTo(o.getDataAula().getHoraInicio());
        }
        return cmp;
    }

    /**
     * Verifica se um objeto é igual a este objeto Aula.
     * @param o o objeto a ser comparado
     * @return true se os objetos forem iguais, false caso contrário
     */
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

    /**
     * Retorna o valor de hash deste objeto Aula.
     * @return o valor de hash do objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(dataAula, sala);
    }

    /**
     * Retorna uma representação em formato de string deste objeto Aula.
     * @return uma string que representa o objeto Aula
     */
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

    public void setSala(String sala) {
        this.sala = sala;
    }
}

