package ua.com.javarush.ezlobin.caesar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlphabeticHistogram {

    private static final List<Character> ALPHABETIC = new ArrayList<>(List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
    private final HashMap<Character, Integer> map;

    public AlphabeticHistogram(){
        map = new HashMap<>();
        for(Character alphabetChar : ALPHABETIC) {
            map.put(alphabetChar, 0);
        }
    }

    public AlphabeticHistogram(char[] dataArray){
        this();
        aggregateAlphabeticHistogram(dataArray);
    }

    public ArrayList<Integer> getCharFrequencyList(){
        return new ArrayList<>(map.values());
    }

    private void countCharFrequency(char Char){
        for (Character alphabetChar : ALPHABETIC) {
            if (alphabetChar == Char) {
                map.put(Char, map.get(Char) + 1);
                break;
            }
        }
    }

    private void aggregateAlphabeticHistogram(char[] dataArray) {
        for (char symbol : dataArray) {
            char charMap = Character.toUpperCase(symbol);
            if (ALPHABETIC.contains(charMap)) {
                countCharFrequency(charMap);
            }
        }
    }
}
