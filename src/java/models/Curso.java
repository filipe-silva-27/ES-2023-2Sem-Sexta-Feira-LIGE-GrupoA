package models;

import java.util.List;

public class Curso {

    private String nome;
    private List<UnidadeCurricular> unidadesCurriculares;

    public Curso(final String nome, final List<UnidadeCurricular> unidadesCurriculares) {
        this.nome = nome;
        this.unidadesCurriculares = unidadesCurriculares;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public List<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    public void setUnidadesCurriculares(final List<UnidadeCurricular> unidadesCurriculares) {
        this.unidadesCurriculares = unidadesCurriculares;
    }
}
