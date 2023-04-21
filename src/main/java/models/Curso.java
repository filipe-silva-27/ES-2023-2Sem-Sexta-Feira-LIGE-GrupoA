package models;

/**
 * Classe que representa um curso.
 */
public class Curso {

    private String nome;

    /**
     * Construtor que cria um objeto Curso com o nome especificado.
     * @param nome o nome do curso
     */
    public Curso(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o nome do curso.
     * @return o nome do curso
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do curso.
     * @param nome o nome do curso
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * Retorna uma representação em formato de string deste objeto Curso.
     * @return uma string que representa o objeto Curso
     */
    @Override
    public String toString() {
        return "Curso{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
