package com.test.hyundaicrawling.domain.model;

import java.util.*;

public class AlphanumericData {

    Set<Character> upperSet;
    Set<Character> lowerSet;
    SortedSet<Character> numberSortedSet;

    public AlphanumericData(List<String> stringDataList) {
        this.upperSet = new HashSet<>();
        this.lowerSet = new HashSet<>();
        this.numberSortedSet = new TreeSet<>();

        for (String stringData : stringDataList) {
            for (char c : stringData.toCharArray()) {
                if (isUpperCase(c)) upperSet.add(c);
                else if (isLowerCase(c)) lowerSet.add(c);
                else if (isDigit(c)) numberSortedSet.add(c);
            }
        }
    }

    public String merged() {
        StringBuilder builder = new StringBuilder();
        Iterator<Character> numberIterator = numberSortedSet.iterator();

        for (char c = 'A'; c <= 'Z'; c++) {
            if (upperSet.contains(c)) builder.append(c);

            char lowerChar = Character.toLowerCase(c);
            if (lowerSet.contains(lowerChar)) builder.append(lowerChar);

            if (numberIterator.hasNext() &&
                    (upperSet.contains(c) || lowerSet.contains(lowerChar))) builder.append(numberIterator.next());
        }

        while (numberIterator.hasNext()) {
            builder.append(numberIterator.next());
        }

        return builder.toString();
    }

    private boolean isUpperCase(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    private boolean isLowerCase(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    private boolean isDigit(char c) {
        return (c >= '0') && (c <= '9');
    }
}
