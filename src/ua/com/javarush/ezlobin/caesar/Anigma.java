package ua.com.javarush.ezlobin.caesar;

import ua.com.javarush.ezlobin.caesar.encryptor.Encryptor;
import ua.com.javarush.ezlobin.caesar.exceptions.InputDataProcessingException;
import ua.com.javarush.ezlobin.caesar.output_path_creator.BrutForceOutputFilePathCreator;
import ua.com.javarush.ezlobin.caesar.output_path_creator.EncoderOutputFilePathCreator;
import ua.com.javarush.ezlobin.caesar.output_path_creator.OutputFilePathCreator;
import ua.com.javarush.ezlobin.caesar.processors.DataProcessor;
import ua.com.javarush.ezlobin.caesar.mode.Mode;


public class Anigma {

    private DataProcessor dataProcessor;

    private Mode mode;
    private String encryptionDataResource;
    private char[] encryptionData;
    private Integer key;
    private char[] referenceData;
    private char[] encryptedData;

    public Anigma(DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    public void start(String[] args) {
        AnigmaInputProcessor anigmaInputProcessor = new AnigmaInputProcessor(dataProcessor);
        anigmaInputProcessor.processInputData(args);

        Encryptor encryptor = new Encryptor(mode, encryptionData, key, referenceData);
        encryptedData = encryptor.launch();

        AnigmaOutputProcessor anigmaOutputProcessor = new AnigmaOutputProcessor(encryptor.getKey());
        anigmaOutputProcessor.process();
    }


    private class AnigmaInputProcessor {
        private final DataProcessor dataProcessor;

        public AnigmaInputProcessor(DataProcessor dataProcessor) {
            this.dataProcessor = dataProcessor;
        }

        private void processInputData(String[] args) {

            class EncryptorArgumentsReader {

                private String modeArgument;
                private String encryptionFilePathArgument;
                private String keyArgument;
                private String referenceFilePathArgument;


                private EncryptorArgumentsReader() {
                }

                private void readArguments(String[] args) {

                    if (args.length != 3) {
                        throw new InputDataProcessingException("Wrong arguments! You insert " + args.length + " arguments instead 3.");
                    }
                    modeArgument = args[0];
                    encryptionFilePathArgument = args[1];
                    if (modeArgument.equalsIgnoreCase("brutforce")) {
                        referenceFilePathArgument = args[2];
                    } else {
                        keyArgument = args[2];
                    }
                }
            }

            EncryptorArgumentsReader encryptorArgumentsReader = new EncryptorArgumentsReader();
            encryptorArgumentsReader.readArguments(args);

            mode = castModeValueToMode(encryptorArgumentsReader.modeArgument);

            encryptionDataResource = encryptorArgumentsReader.encryptionFilePathArgument;
            String encryptionDataString = dataProcessor.read(encryptionDataResource);
            encryptionData = encryptionDataString.toCharArray();

            if (mode != Mode.BRUTFORCE) {

                key = castKeyValueToInt(encryptorArgumentsReader.keyArgument);

            } else {

                String referenceDataString = dataProcessor.read(encryptorArgumentsReader.referenceFilePathArgument);
                referenceData = referenceDataString.toCharArray();
            }
        }

        private Mode castModeValueToMode(String modeValue) {
            try {
                return Mode.valueOf(modeValue.toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new InputDataProcessingException("Wrong mode argument: \"" + modeValue + "\"! \nReason: " + ex.getMessage() + "\nPlease, insert the correct mode of encoder: encode, decode or brutforce.", ex);
            }
        }

        private Integer castKeyValueToInt(String keyValue) {
            try {
                return Integer.parseInt(keyValue);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Wrong key argument!\n" + ex.getMessage() + "\nThe key value must be a number.");
            }
        }
    }

    private class AnigmaOutputProcessor {
        private DataProcessor dataProcessor;
        private OutputFilePathCreator outputFilePathCreator;
        private Mode mode;
        private char[] outputData;
        private String path;
        private Integer key;

        public AnigmaOutputProcessor(Integer key) {
            this.dataProcessor = Anigma.this.dataProcessor;
            this.mode = Anigma.this.mode;
            this.outputData = Anigma.this.encryptedData;
            this.path = Anigma.this.encryptionDataResource;
            this.key = key;
        }

        public void process() {

            if (!(mode == Mode.BRUTFORCE)) {
                this.outputFilePathCreator = new EncoderOutputFilePathCreator(mode, path);
            } else {
                this.outputFilePathCreator = new BrutForceOutputFilePathCreator(new EncoderOutputFilePathCreator(mode, path), key);
            }
            String outputFilePath = outputFilePathCreator.getOutputFilePathValue();
            dataProcessor.write(outputFilePath, outputData);
        }
    }

}
