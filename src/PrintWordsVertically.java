/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 26/08/22
    @copyright: Check the repository license.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Problem link: https://leetcode.com/problems/print-words-vertically/
public class PrintWordsVertically {
    public List<String> printVertically(String s) {
        final String[] list = s.split(" ");

        int maxLength = Arrays.stream(list)
                .map(String::length)
                .max(Integer::compare)
                .orElse(0);
        final ArrayList<StringBuilder> builders = new ArrayList<>();

        for (int i = 0; i < maxLength; i++) {
            builders.add(new StringBuilder());

            for (String string : list) {
                Character c;
                try {
                    c = string.charAt(i);
                } catch (IndexOutOfBoundsException e) {
                    c = ' ';
                }

                builders.get(i).append(c);
            }
        }

        final String regex = "\\s+$";

        return builders.stream()
                .map(x -> x.toString().replaceAll(regex, ""))
                .toList();
    }
}
