/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 16/08/22
    @copyright: Check the repository license.
*/

import java.util.HashMap;
import java.util.Map;

// Problem link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepeatingChars {
    public int lengthOfLongestSubstring(String s) {
        int index = 0, max = 0, current;
        Map<Character, Integer> map = new HashMap<>();
        Character c = 'a';

        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }

        while (index < s.length()) {
            int inner = index;
            current = 0;
            map.clear();

            while (inner < s.length()) {
                c = s.charAt(inner);

                if (map.containsKey(s.charAt(inner))) {
                    break;
                }

                map.put(c, inner);
                inner ++;
                current ++;
            }

            if (current > max) {
                max = current;
            }

            index = map.get(c) + 1;
        }

        return max;
    }
}
