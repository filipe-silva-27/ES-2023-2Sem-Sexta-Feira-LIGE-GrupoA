package models;

import java.util.List;

public class UnidadeCurricular {

    private String nome;
    private List<AulaUCTurno> aulas;

    public List<AulaUCTurno> getAulas() {
        return aulas;
    }

    public void addAula(AulaUCTurno cls) {
        aulas.add(cls);
    }


}
