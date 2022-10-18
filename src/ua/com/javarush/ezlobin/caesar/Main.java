package ua.com.javarush.ezlobin.caesar;

import ua.com.javarush.ezlobin.caesar.processors.fileprocessor.FileProcessor;

public class Main {
    public static void main(String[] args) {
        try {
            new Anigma(new FileProcessor()).start(args);
        } catch (RuntimeException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
