package models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Set;

/**
 * Classe Horario que é o modelo utilizado pela GUI e outras funcionalidades
 * para representar o horário que contém as aulas, turnos, turmas, salas, etc.
 * @since 16/04/2023
 */
public class Horario {
    private String name;    // Nome do horário
    private File file;      // Ficheiro associado ao horário para importação e exportação
    private Set<UnidadeCurricular> unidadesCurriculares;   // Set de unidades curriculares
    private static final Logger logger = LoggerFactory.getLogger(Horario.class);

    /**
     * Método construtor do horário
     * @param name Nome do horário
     * @param unidadesCurriculares Set de unidades curriculares associadas ao horário
     */
    public Horario(String name, Set<UnidadeCurricular> unidadesCurriculares) {
        this.name = name;
        this.unidadesCurriculares = unidadesCurriculares;
        this.file = null;
    }

    public Set<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    public void setUnidadesCurriculares(Set<UnidadeCurricular> unidadesCurriculares) {
        this.unidadesCurriculares = unidadesCurriculares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File selectedFile) {
        this.file = selectedFile;
    }
}
