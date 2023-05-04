package models;

public class CustomExceptions {

    public static class EmptyUrlException extends Exception{
        public EmptyUrlException(String message){
            super(message);
        }
    }

    public static class InvalidFilenameException extends Exception{
        public InvalidFilenameException(String message){
            super(message);
        }
    }

    public static class InvalidFileExtensionException extends Exception{
        public InvalidFileExtensionException(String message){
            super(message);
        }
    }




}
