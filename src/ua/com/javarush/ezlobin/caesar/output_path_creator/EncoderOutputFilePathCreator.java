package ua.com.javarush.ezlobin.caesar.output_path_creator;

import ua.com.javarush.ezlobin.caesar.mode.Mode;

public class EncoderOutputFilePathCreator implements OutputFilePathCreator {
    private static final String OUTPUT_FILE_EXTENSION = ".txt";
    private final Mode mode;
    private String sourceFilePathValue;

    public EncoderOutputFilePathCreator(Mode mode, String sourceFilePathValue){
        this.mode = mode;
        this.sourceFilePathValue = sourceFilePathValue;
    }

    @Override
    public String createOutputFilePathValue() {
        int indexOfLastSlash = sourceFilePathValue.lastIndexOf('\\');
        String parent = sourceFilePathValue.substring(0, indexOfLastSlash);
        String modeName = mode.getOperationName();
        int indexOfLastPoint = sourceFilePathValue.lastIndexOf('.');
        String name = sourceFilePathValue.substring(indexOfLastSlash, indexOfLastPoint);
        return  parent + name + "_" + modeName;
    }

    @Override
    public String getOutputFilePathValue() {
        return createOutputFilePathValue() + OUTPUT_FILE_EXTENSION;
    }
}

