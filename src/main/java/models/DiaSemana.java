package models;

public enum DiaSemana {

    MONDAY("Seg"),
    TUESDAY("Ter"),
    WEDNESDAY("Qua"),
    THURSDAY("Qui"),
    FRIDAY("Sex"),
    SATURDAY("SÃ¡b"),
    SUNDAY("Dom");

    private String name;

    DiaSemana(String name) {
        this.name = name;
    }

    public static DiaSemana fromName(String nome) {
        for (DiaSemana diaSemana : DiaSemana.values()) {
            if (diaSemana.getName().equals(nome)) {
              return diaSemana;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + nome);
    }

    public String getName() {
        return name;
    }

}
