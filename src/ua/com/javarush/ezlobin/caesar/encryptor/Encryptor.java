package ua.com.javarush.ezlobin.caesar.encryptor;

import ua.com.javarush.ezlobin.caesar.operations.BrutForce;
import ua.com.javarush.ezlobin.caesar.operations.Encoder;
import ua.com.javarush.ezlobin.caesar.mode.Mode;

public class Encryptor {
    private Encoder encoder;
    private BrutForce brutForce;

    private Mode mode;
    private char[] encryptionData;
    private Integer key;
    private char[] referenceData;


    public Encryptor(Mode mode, char[] encryptionData, Integer key, char[] referenceData) {
        this.mode = mode;
        this.encryptionData = encryptionData;
        this.key = key;
        this.referenceData = referenceData;
    }

    public char[] launch() {
        if (mode == Mode.BRUTFORCE) {
            brutForce = new BrutForce(encryptionData, referenceData);
            key = brutForce.process();
        }
        encoder = new Encoder(mode, encryptionData, key);
        return encoder.process();
    }

    public Integer getKey(){
        return key;
    }
}
