package models;

import java.util.*;


public class UnidadeCurricular {

    private String curso;
    private String nomeUC;
    private List<Aula> aulas;

    public UnidadeCurricular(String curso, String nomeUC) {
        this.curso = curso;
        this.nomeUC = nomeUC;
        this.aulas = new ArrayList<>();
    }

    public String getCurso() {
        return curso;
    }

    public String getNomeUC() {
        return nomeUC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnidadeCurricular that = (UnidadeCurricular) o;
        return Objects.equals(curso, that.curso) && Objects.equals(nomeUC, that.nomeUC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curso, nomeUC);
    }

    public boolean addAula(Aula aula){
        return aulas.add(aula);
    }

    @Override
    public String toString() {
        return "UnidadeCurricular{" +
                "curso='" + curso + '\'' +
                ", nomeUC='" + nomeUC + '\'' +
                ", aulas=" + aulas +
                '}';
    }

}
