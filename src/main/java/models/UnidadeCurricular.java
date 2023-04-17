package models;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


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
     */
    public UnidadeCurricular(String nome) {
        this.nome = nome;
        this.cursos = new HashSet<>();
        this.turnos = new HashSet<>();
    }

    public boolean addCurso(Curso curso){
        return cursos.add(curso);
    }

    public boolean addTurno(Turno turno){
        return turnos.add(turno);
    }

    public Turno getTurnoPorNome(String nome) {
        for (Turno turno : turnos) {
            if (turno.getIdTurno().equals(nome)) {
                return turno;
            }
        }
        return null;
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
