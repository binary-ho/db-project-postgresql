package com.gov.appDemo.domain;

import java.util.HashMap;
import java.util.Map;

public class DegreeMap {
    Map<String, Integer> degreeMapStringToInt;
    Map<Integer, String> degreeMapIntToString;

    public DegreeMap() {
        degreeMapStringToInt = new HashMap<>();
        degreeMapStringToInt.put("phd", 0);
        degreeMapStringToInt.put("master", 1);
        degreeMapStringToInt.put("undergrad", 2);

        degreeMapIntToString = new HashMap<>();
        degreeMapIntToString.put(0, "phd");
        degreeMapIntToString.put(1, "master");
        degreeMapIntToString.put(2, "undergrad");
    }

    public int get(String degree) {
        if (!degreeMapStringToInt.containsKey(degree)) return -1;
        return degreeMapStringToInt.get(degree);
    }

    public String get(int idx) {
        return degreeMapIntToString.get(idx);
    }
}
