package com.bugjc.java.problems.level.medium;

import com.bugjc.java.problems.level.Tests;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubstringWithoutRepeatingCharactersTest {

    @Test
    void lengthOfLongestSubstring() {
        List<Tests<String, Integer>> testSet = new ArrayList<Tests<String, Integer>>() {{
            add(new Tests<>("abcabcbb", 3));
            add(new Tests<>("bbbbb", 1));
            add(new Tests<>("pwwkew", 3));
            add(new Tests<>(" ", 1));
            add(new Tests<>("ckilbkd", 5));
        }};

        for (Tests<String, Integer> tests : testSet) {
            int result = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(tests.getInput());
            assertEquals(tests.getOutput(), result);
        }
    }
}