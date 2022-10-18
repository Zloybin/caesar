package ua.com.javarush.ezlobin.caesar.output_path_creator;

public class BrutForceOutputFilePathCreator extends OutputFilePathCreatorDecorator {
    private final String OUTPUT_FILE_EXTENSION = ".txt";
    private final int key;

    public BrutForceOutputFilePathCreator(OutputFilePathCreator outputFilePathCreator, int key) {
        super(outputFilePathCreator);
        this.key = key;
    }

    @Override
    public String createOutputFilePathValue() {
        return super.createOutputFilePathValue() + "_" + key;
    }

    @Override
    public String getOutputFilePathValue() {
        return createOutputFilePathValue() + OUTPUT_FILE_EXTENSION;
    }
}
