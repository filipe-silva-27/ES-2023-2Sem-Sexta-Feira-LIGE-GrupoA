package models;

/**
 * Classe que contém exceções customizadas
 */
public class CustomExceptions {

    /**
     * Construtor privado de modo a não permitir a instanciação
     */
    private CustomExceptions(){
        throw new IllegalArgumentException("Classe não pode ser instanciada!");
    }

    /**
     * Exceção para urls vazios
     * @see Exception
     */
    public static class EmptyUrlException extends Exception{
        public EmptyUrlException(String message){
            super(message);
        }
    }

    /**
     * Exceção para nomes de ficheiros inválidos
     * @see Exception
     */
    public static class InvalidFilenameException extends Exception{
        public InvalidFilenameException(String message){
            super(message);
        }
    }

    /**
     * Exceção para extensões de ficheiros inválidos
     * @see Exception
     */
    public static class InvalidFileExtensionException extends Exception{
        public InvalidFileExtensionException(String message){
            super(message);
        }
    }




}
