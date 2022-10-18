package ua.com.javarush.ezlobin.caesar.exceptions;

public class InputDataProcessingException extends RuntimeException {
    public InputDataProcessingException(){
    }

    public InputDataProcessingException(String message){super(message);}

    public InputDataProcessingException(String message, Throwable cause){ super(message, cause);}


}
