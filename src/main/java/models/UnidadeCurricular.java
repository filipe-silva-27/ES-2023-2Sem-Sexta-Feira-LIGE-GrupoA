package models;

import java.util.Set;


/**
 * Modelo para representar as Unidades Curriculares,
 * é a classe que englobará todos os outros modelos.
 */
public class UnidadeCurricular {

    private String nome;
    private Set<Curso> cursos; //  cursos que contém esta UC
    private Set<Turno> turnos; //  turnos que contém esta UC

    /**
     * Método construtor da Unidade Curricular
     * @param nome Nome da Unidade Curricular
     * @param cursos cursos que contém esta UC
     * @param turnos turnos que contém esta UC
     */
    public UnidadeCurricular(String nome, Set<Curso> cursos, Set<Turno> turnos) {
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

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(final Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(final Set<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "UnidadeCurricular{" + "nome=" + nome + ", cursos=" + cursos + ", turnos=" + turnos + '}';
    }

}
