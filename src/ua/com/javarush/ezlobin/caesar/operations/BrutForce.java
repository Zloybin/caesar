package ua.com.javarush.ezlobin.caesar.operations;

import ua.com.javarush.ezlobin.caesar.model.AlphabeticHistogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrutForce {
    private static final List<Character> ALPHABETIC;
    private char[] encryptionData;
    private char[] referenceData;
    private Integer key;

    static{
        ALPHABETIC = new ArrayList<>(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
    }

    public BrutForce(char[] encryptionData, char[] referenceData) {
        this.encryptionData = encryptionData;
        this.referenceData = referenceData;
    }

    public int process() {
        AlphabeticHistogram sourceAH = new AlphabeticHistogram(encryptionData);
        AlphabeticHistogram referenceAH = new AlphabeticHistogram(referenceData);
        ArrayList<Integer> diffList = enumerationOfKeys(sourceAH, referenceAH);
        key = getIndexMinValue(diffList);
        return key;
    }

    private ArrayList<Integer> enumerationOfKeys(AlphabeticHistogram inputFileAH, AlphabeticHistogram analyseFileAH) {
        ArrayList<Integer> diffList = new ArrayList<>();
        ArrayList<Integer> inputFilesListValue;
        ArrayList<Integer> analyseFilesListValue = analyseFileAH.getCharFrequencyList();
        for (int i = 0; i < ALPHABETIC.size(); i++) {
            inputFilesListValue = inputFileAH.getCharFrequencyList();
            charFrequencyListRotator(inputFilesListValue, i);
            int diff = charFrequencyListComparator(inputFilesListValue, analyseFilesListValue);
            diffList.add(diff);
        }
        return diffList;
    }

    private void charFrequencyListRotator(ArrayList<Integer> list, int i) {
        Collections.rotate(list, -i);
    }

    private int charFrequencyListComparator(ArrayList<Integer> valuesList1, ArrayList<Integer> valuesList2) {
        int diff = 0;
        for (int i = 0; i < ALPHABETIC.size(); i++) {
            diff = diff + Math.abs(valuesList1.get(i) - valuesList2.get(i));
        }
        return diff;
    }

    private Integer getIndexMinValue(ArrayList<Integer> list) {
        int minValue = Integer.MAX_VALUE;
        int minValueIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            if (value < minValue) {
                minValue = value;
                minValueIndex = i;
            }
        }
        return minValueIndex;
    }

}
