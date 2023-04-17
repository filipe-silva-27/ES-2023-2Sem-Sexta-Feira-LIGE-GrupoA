package models;

import java.util.Objects;

public class Sala {
    private final String nome;
    private final Integer lotacao;

    public Sala(String sala, Integer lotacao) {
        this.nome = sala;
        this.lotacao = lotacao;
    }

    public String getNome() {
        return nome;
    }

    public Integer getLotacao() {
        return lotacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return Objects.equals(nome, sala.nome) && Objects.equals(lotacao, sala.lotacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, lotacao);
    }
}
