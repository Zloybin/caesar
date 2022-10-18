package ua.com.javarush.ezlobin.caesar.processors;

public interface DataProcessor {

    String read(String input);

    void write(String output, char[] outputData);

}
