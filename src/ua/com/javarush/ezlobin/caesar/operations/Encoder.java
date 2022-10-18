package ua.com.javarush.ezlobin.caesar.operations;

import ua.com.javarush.ezlobin.caesar.mode.Mode;

import java.util.ArrayList;
import java.util.List;

public class Encoder {

    private static final List<Character> UPPER_ALPHABETIC;
    private static final List<Character> LOWER_ALPHABETIC;
    private static final int ALPHABETIC_SIZE;
    private static final int MAX_ALPHABETIC_INDEX;
    private static final int MIN_ALPHABETIC_INDEX = 0;

    private Mode mode;
    private char[] sourceData;
    private Integer key;
    private char[] outputData;

    static {
        UPPER_ALPHABETIC = new ArrayList<>(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        LOWER_ALPHABETIC = new ArrayList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        ALPHABETIC_SIZE = UPPER_ALPHABETIC.size();
        MAX_ALPHABETIC_INDEX = UPPER_ALPHABETIC.size() - 1;
    }


    public Encoder(Mode mode, char[] sourceData, int key) {
        this.mode = mode;
        this.sourceData = sourceData;
        this.key = key;
    }



    public char[] process() {
        char[] encodingArray = new char[sourceData.length];
        char oldChar;
        for (int i = 0; i < encodingArray.length; i++) {
            oldChar = sourceData[i];
            if (!isCharValid(oldChar)) {
                encodingArray[i] = oldChar;
            } else {
                if (Character.isLowerCase(oldChar)) {
                    char newChar = charEncoder(oldChar, LOWER_ALPHABETIC);
                    encodingArray[i] = newChar;
                } else {
                    char newChar = charEncoder(oldChar, UPPER_ALPHABETIC);
                    encodingArray[i] = newChar;
                }
            }
        }
        outputData = encodingArray;
        return outputData;
    }


    private boolean isCharValid(char oldChar) {
        return UPPER_ALPHABETIC.contains(oldChar) || LOWER_ALPHABETIC.contains(oldChar);
    }

    private char charEncoder(char oldChar, List<Character> alphabetic) {
        int correctKey = correctKey(mode, key);
        int oldCharIndex = alphabetic.indexOf(oldChar);
        int newCharIndex = getNewCharIndex(oldCharIndex, correctKey);
        return alphabetic.get(newCharIndex);
    }

    private Integer correctKey(Mode mode, int key) {
        if (!mode.equals(Mode.ENCODE)) {
            key = - key;
        }
        return key;
    }

    private int getNewCharIndex(int oldCharIndex, int key) {
        int newCharIndex = oldCharIndex + key;

        if (newCharIndex > MAX_ALPHABETIC_INDEX) {
            newCharIndex = (newCharIndex - ALPHABETIC_SIZE) % ALPHABETIC_SIZE;
        } else if (newCharIndex < MIN_ALPHABETIC_INDEX) {
            int difference = newCharIndex % ALPHABETIC_SIZE;
            if (difference != 0) {
                newCharIndex = ALPHABETIC_SIZE + difference;
            } else {
                newCharIndex = 0;
            }
        }
        return newCharIndex;
    }

}
