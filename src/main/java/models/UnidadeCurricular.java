package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;


public class UnidadeCurricular {

    private String curso;
    private String nomeUC;
    @JsonIgnore
    private List<Aula> aulas;

    /**
     * Construtor da classe UnidadeCurricular.
     * @param curso O curso da unidade curricular.
     * @param nomeUC O nome da unidade curricular.
     */
    public UnidadeCurricular(String curso, String nomeUC) {
        this.curso = curso;
        this.nomeUC = nomeUC;
        this.aulas = new ArrayList<>();
    }

    public UnidadeCurricular(String nomeUC) {
        this.nomeUC = nomeUC;
        this.aulas = new ArrayList<>();
    }

    /**
     * Retorna o curso da unidade curricular.
     * @return O curso da unidade curricular.
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Retorna o nome da unidade curricular.
     * @return O nome da unidade curricular.
     */
    public String getNomeUC() {
        return nomeUC;
    }

    /**
     * Retorna a lista de aulas associadas à unidade curricular.
     * @return A lista de aulas associadas à unidade curricular.
     */
    public List<Aula> getAulas() {
        return aulas;
    }

    /**
     * Verifica se duas unidades curriculares são iguais.
     * Duas unidades curriculares são iguais se possuem o mesmo curso e mesmo nome.
     * @param o O objeto a ser comparado.
     * @return true se as unidades curriculares são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnidadeCurricular that = (UnidadeCurricular) o;
        return Objects.equals(curso, that.curso) && Objects.equals(nomeUC, that.nomeUC);
    }

    /**
     * Retorna o código de hash da unidade curricular.
     * @return O código de hash da unidade curricular.
     */
    @Override
    public int hashCode() {
        return Objects.hash(curso, nomeUC);
    }

    /**
     * Adiciona uma aula à lista de aulas associadas à unidade curricular.
     * @param aula A aula a ser adicionada.
     * @return true se a aula foi adicionada com sucesso, false caso contrário.
     */
    public boolean addAula(Aula aula){
        return aulas.add(aula);
    }

    /**
     * Retorna uma representação em formato de String do objeto UnidadeCurricular.
     * A representação contém o curso, nome e aulas associadas à unidade curricular.
     * @return Uma String representando o objeto UnidadeCurricular.
     */
    @Override
    public String toString() {
        return "UnidadeCurricular{" +
                "curso='" + curso + '\'' +
                ", nomeUC='" + nomeUC + '\'' +
                ", aulas=" + aulas +
                '}';
    }

}
