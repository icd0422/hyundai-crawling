package com.test.hyundaicrawling.domain.model;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class AlphanumericData {

    SortedSet<Character> upperSet;
    SortedSet<Character> lowerSet;
    SortedSet<Character> numberSet;

    public AlphanumericData(List<String> stringDataList) {
        this.upperSet = new TreeSet<>();
        this.lowerSet = new TreeSet<>();
        this.numberSet = new TreeSet<>();

        for (String stringData : stringDataList) {
            for (char c : stringData.toCharArray()) {
                if (isUpperCase(c)) upperSet.add(c);
                else if (isLowerCase(c)) lowerSet.add(c);
                else if (isDigit(c)) numberSet.add(c);
            }
        }
    }

    public String merged() {
        StringBuilder builder = new StringBuilder();

        Iterator<Character> upperIterator = upperSet.iterator();
        Iterator<Character> lowerIterator = lowerSet.iterator();
        Iterator<Character> numberIterator = numberSet.iterator();

        while (upperIterator.hasNext() || lowerIterator.hasNext() || numberIterator.hasNext()) {
            if (upperIterator.hasNext()) builder.append(upperIterator.next());
            if (lowerIterator.hasNext()) builder.append(lowerIterator.next());
            if (numberIterator.hasNext()) builder.append(numberIterator.next());
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
