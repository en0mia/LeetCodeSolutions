/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 05/08/22
    @copyright: Check the repository license.
*/

import java.util.*;

// Problem link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class LettersCombinations {
    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new LinkedList<>();
        }

        Map<String, String> digitsToLetters = new HashMap<>();
        digitsToLetters.put("2", "abc");
        digitsToLetters.put("3", "def");
        digitsToLetters.put("4", "ghi");
        digitsToLetters.put("5", "jkl");
        digitsToLetters.put("6", "mno");
        digitsToLetters.put("7", "pqrs");
        digitsToLetters.put("8", "tuv");
        digitsToLetters.put("9", "wxyz");

        String[] digitsArray = digits.split("");
        List<String> haystack = new ArrayList<>();
        List<String> result = new LinkedList<>();

        for (String digit : digitsArray) {
            haystack.add(digitsToLetters.get(digit));
        }

        recursive(result, haystack, new StringBuilder(), digits.length(), 0);

        return result;
    }

    private void recursive(List<String> result, List<String> haystack, StringBuilder current, int goal, int index) {
        if (current.length() == goal) {
            result.add(current.toString());
            return;
        }

        String[] currentHaystack = haystack.get(index).split("");

        for (String letter : currentHaystack) {
            current.append(letter);
            recursive(result, haystack, current, goal, index + 1);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
