package models;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Horario que é o modelo utilizado pela GUI e outras funcionalidades
 * @since 18/04/2023
 */
public class Horario {
    private String name;    // Nome do horário
    private File file;      // Ficheiro associado ao horário para importação e exportação
    private Set<UnidadeCurricular> unidadesCurriculares = new HashSet<>();   // Set de unidades curriculares
    /**
     * Método construtor do horário
     * @param name Nome do horário
     */
    public Horario(String name) {
        this.name = name;
        this.file = null;
    }

    public boolean addUnidadeCurricular(UnidadeCurricular uc){
        return unidadesCurriculares.add(uc);
    }

    public UnidadeCurricular getUnidadeCurricularByNome(String nome){
        for(UnidadeCurricular uc: unidadesCurriculares){
            if(uc.getNomeUC().equals(nome)){
                return uc;
            }
        }
        return null;
    }

    public UnidadeCurricular getUnidadeCurricular(UnidadeCurricular o){
        for(UnidadeCurricular uc: unidadesCurriculares){
            if(uc.equals(o)){
                return uc;
            }
        }
        return null;
    }

    public Set<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
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

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (UnidadeCurricular uc : unidadesCurriculares) {
            bld.append(uc.toString()).append("\n");
        }
        return bld.toString();
    }
}
