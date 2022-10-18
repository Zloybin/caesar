package ua.com.javarush.ezlobin.caesar.processors.fileprocessor;

import ua.com.javarush.ezlobin.caesar.exceptions.FileProcessingException;
import ua.com.javarush.ezlobin.caesar.processors.DataProcessor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileProcessor implements DataProcessor {

    @Override
    public String read(String filePathValue) {
        Path filePath = Path.of(filePathValue);
        try {
            return Files.readString(filePath);
        } catch (IOException ex) {
            throw new FileProcessingException("Error during reading the file: " + filePath, ex);
        }
    }

    @Override
    public void write(String outputFilePathValue, char[] outputData) {
        Path filePath = Path.of(outputFilePathValue);
        try {
            Files.writeString(filePath, String.copyValueOf(outputData), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new FileProcessingException("Error during writing the file: " + filePath, ex);
        }
    }
}
