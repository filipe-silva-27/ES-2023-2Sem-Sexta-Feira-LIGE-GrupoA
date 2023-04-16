package models;

import java.util.List;

public class UnidadeCurricular {

    private String nome;
    private List<AulaTurno> aulas;

    public List<AulaTurno> getAulas() {
        return aulas;
    }

    public void addAula(AulaTurno cls) {
        aulas.add(cls);
    }


}
