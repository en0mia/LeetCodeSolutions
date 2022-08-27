/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 27/08/22
    @copyright: Check the repository license.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// Problem link: https://leetcode.com/problems/valid-parentheses/
public class ValidParentheses {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            // If it's an open parenthesis.
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                Character open = stack.pop();
                if (!c.equals(map.get(open))) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
