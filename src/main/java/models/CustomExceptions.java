package models;

public class CustomExceptions {

    /**
     * Exceção lançada quando a URL está vazia ou nula.
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
     * Exceção lançada quando o nome do ficheiro é inválido.
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
     * Exceção lançada quando a extensão do ficheiro é inválida.
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




}
