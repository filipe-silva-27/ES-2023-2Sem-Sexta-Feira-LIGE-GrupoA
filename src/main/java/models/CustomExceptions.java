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

        /**
         * Cria uma nova exceção com a mensagem especificada.
         * @param message A mensagem de erro.
         */
        public EmptyUrlException(String message){
            super(message);
        }
    }

    /**
     * Exceção para nomes de ficheiros inválidos
     * @see Exception
     */
    public static class InvalidFilenameException extends Exception{
        /**
         * Cria uma nova exceção com a mensagem especificada.
         * @param message A mensagem de erro.
         */
        public InvalidFilenameException(String message){
            super(message);
        }
    }

    /**
     * Exceção para extensões de ficheiros inválidos
     * @see Exception
     */
    public static class InvalidFileExtensionException extends Exception{

        /**
         * Cria uma nova exceção com a mensagem especificada.
         * @param message A mensagem de erro.
         */
        public InvalidFileExtensionException(String message){
            super(message);
        }
    }

    /**
     * Exceção para formato URI invalido
     * @see Exception
     */
    public static class WebcalInvalidFormatException extends Exception{
        /**
         * Cria uma nova exceção com a mensagem especificada.
         * @param message A mensagem de erro.
         */
        public WebcalInvalidFormatException(String message) {
            super(message);
        }
    }




}
