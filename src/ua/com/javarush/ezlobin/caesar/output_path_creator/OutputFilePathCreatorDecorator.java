package ua.com.javarush.ezlobin.caesar.output_path_creator;

public class OutputFilePathCreatorDecorator implements OutputFilePathCreator {
    private final OutputFilePathCreator outputFilePathCreator;

    public OutputFilePathCreatorDecorator(OutputFilePathCreator outputFilePathCreator) {
        this.outputFilePathCreator = outputFilePathCreator;
    }

    @Override
    public String createOutputFilePathValue() {
        return outputFilePathCreator.createOutputFilePathValue();
    }

    @Override
    public String getOutputFilePathValue() {
        return outputFilePathCreator.getOutputFilePathValue();
    }
}
