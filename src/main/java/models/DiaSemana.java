package models;

/**
 * Enumeração que representa os dias da semana.
 */
public enum DiaSemana {

    MONDAY("Seg"),
    TUESDAY("Ter"),
    WEDNESDAY("Qua"),
    THURSDAY("Qui"),
    FRIDAY("Sex"),
    SATURDAY("Sáb"),
    SUNDAY("Dom");

    private String name;

    /**
     * Construtor da enumeração DiaSemana.
     * @param name Nome do dia da semana.
     */
    DiaSemana(String name) {
        this.name = name;
    }

    /**
     * Obtém a enumeração DiaSemana com base no nome do dia da semana.
     * @param nome Nome do dia da semana.
     * @return Enumeração DiaSemana correspondente.
     * @throws IllegalArgumentException se o nome fornecido for inválido.
     */
    public static DiaSemana fromName(String nome) {
        for (DiaSemana diaSemana : DiaSemana.values()) {
            if (diaSemana.getName().equals(nome)) {
              return diaSemana;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + nome);
    }

    /**
     * Obtém o nome do dia da semana.
     * @return Nome do dia da semana.
     */
    public String getName() {
        return name;
    }

}
