package models;

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

}
