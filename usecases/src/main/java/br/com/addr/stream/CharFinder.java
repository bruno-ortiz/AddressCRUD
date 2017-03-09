package br.com.addr.stream;

import java.util.*;

public class CharFinder {

    public static char firstChar(Stream input) {
        char ant = '\u0000', mid = '\u0000', last = '\u0000';
        Map<Character, Integer> vowelMap = new HashMap<>();
        Set<Character> charSet = new LinkedHashSet<>();

        while (input.hasNext()) {
            char c = input.getNext();
            if (isVowel(c)) {
                registerVowel(vowelMap, c);
                if ((ant == '\u0000' || isVowel(ant)) && mid == '\u0000') {
                    ant = c;
                } else if (mid == '\u0000') {
                    ant = mid = last = '\u0000';
                } else {
                    last = c;
                }
            } else {
                if (ant != '\u0000' && mid == '\u0000') {
                    mid = c;
                } else {
                    ant = mid = last = '\u0000';
                }
            }
            if (last != '\u0000') {
                charSet.add(last);
                ant = last;
                mid = '\u0000';
                last = '\u0000';
            }
        }
        Optional<Character> first = charSet.stream().filter(character -> vowelMap.get(character) == 0).findFirst();
        return first.orElseThrow(() -> new IllegalStateException("Stream does not met the conditions."));
    }

    private static void registerVowel(Map<Character, Integer> vowelMap, char c) {
        if (vowelMap.containsKey(c)) {
            int appearances = vowelMap.get(c) + 1;
            vowelMap.put(c, appearances);
        } else {
            vowelMap.put(c, 0);
        }
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) >= 0 || "AEIOU".indexOf(c) >= 0;
    }

}
