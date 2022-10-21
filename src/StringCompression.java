/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 21/10/22
    @copyright: Check the repository license.
*/

// Problem link: https://leetcode.com/problems/string-compression/
public class StringCompression {
    public static int compress(char[] chars) {
        int counter;
        int res = 0;
        int i = 0;

        // Legal but useless case.
        if (chars.length == 1) {
            return 1;
        }

        while (i < chars.length) {
            char current = chars[i];
            chars[res++] = current;
            counter = 0;

            for (int j = i; j < chars.length && chars[j] == current; j++, counter++);

            if (counter > 1) {
                // insert the number in chars[i+1, ...]
                String tmp = Integer.toString(counter);

                for (int k = 0; k < tmp.length(); k++) {
                    chars[res + k] = tmp.charAt(k);
                }

                res += tmp.length();
            }

            i += counter;
        }

        return res;
    }
}
