package models;

import java.util.List;


/**
 * Modelo para representar as Unidades Curriculares,
 * é a classe que englobará todos os outros modelos.
 */
public class UnidadeCurricular {

    private String nome;
    private List<Curso> cursos; // Lista de cursos que contém esta UC
    private List<Turno> turnos; // Lista de turnos que contém esta UC

    /**
     * Método construtor da Unidade Curricular
     * @param nome Nome da Unidade Curricular
     * @param cursos Lista de cursos que contém esta UC
     * @param turnos Lista de turnos que contém esta UC
     */
    public UnidadeCurricular(final String nome, final List<Curso> cursos, final List<Turno> turnos) {
        this.nome = nome;
        this.cursos = cursos;
        this.turnos = turnos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(final List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(final List<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "UnidadeCurricular{" + "nome=" + nome + ", cursos=" + cursos + ", turnos=" + turnos + '}';
    }

}
