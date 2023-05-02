package models;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa um horário, contendo o nome do horário, o arquivo associado para importação/exportação,
 * e um conjunto de unidades curriculares.
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


    /**
     * Adiciona uma unidade curricular ao horário.
     *
     * @param uc Unidade curricular a ser adicionada.
     * @return true se a unidade curricular foi adicionada com sucesso, false caso contrário.
     */
    public boolean addUnidadeCurricular(UnidadeCurricular uc){
        return unidadesCurriculares.add(uc);
    }

    /**
     * Obtém uma unidade curricular pelo nome.
     *
     * @param nome Nome da unidade curricular a ser buscada.
     * @return A unidade curricular correspondente, ou null se não encontrada.
     */
    public UnidadeCurricular getUnidadeCurricularByNome(String nome){
        for(UnidadeCurricular uc: unidadesCurriculares){
            if(uc.getNomeUC().equals(nome)){
                return uc;
            }
        }
        return null;
    }



    /**
     * Obtém uma unidade curricular por objeto.
     *
     * @param o Unidade curricular a ser buscada.
     * @return A unidade curricular correspondente, ou null se não encontrada.
     */
    public UnidadeCurricular getUnidadeCurricular(UnidadeCurricular o){
        for(UnidadeCurricular uc: unidadesCurriculares){
            if(uc.equals(o)){
                return uc;
            }
        }
        return null;
    }

    /**
     * Obtém o conjunto de unidades curriculares do horário.
     * @return O conjunto de unidades curriculares.
     */
    public Set<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    /**
     * Obtém o nome do horário.
     * @return O nome do horário.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do horário.
     * @param name O nome do horário.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o arquivo associado ao horário.
     * @return O arquivo associado ao horário.
     */
    public File getFile() {
        return file;
    }

    /**
     * Define o arquivo associado ao horário.
     * @param selectedFile O arquivo associado ao horário.
     */
    public void setFile(File selectedFile) {
        this.file = selectedFile;
    }

    /**
     * Retorna a extensão de um arquivo.
     * @return a extensão do arquivo, ou null se o arquivo não tiver extensão
     */
    public String getFileExtension() {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        return (dotIndex == -1) ? null : fileName.substring(dotIndex + 1);
    }

    /**
     * Retorna uma representação em formato de String do objeto Horario.
     * Cada UnidadeCurricular associada ao horário é convertida numa String
     * usando o método toString() da classe UnidadeCurricular, e separada por uma nova linha.
     *
     * @return Uma String representando o objeto Horario.
     */
    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (UnidadeCurricular uc : unidadesCurriculares) {
            bld.append(uc.toString()).append("\n");
        }
        return bld.toString();
    }
}
