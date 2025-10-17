package homeworks.hw05;

import java.util.ArrayDeque;
import java.util.Deque;

public class B_05_01 {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("(((a)))(asd(alsdkfj))lsdkfj(b)");
        System.out.println(extract(s));
    }

    public static StringBuilder extract(StringBuilder s) {
        Deque<Integer> stack = new ArrayDeque<>();
        if (!Parentheses(s, stack)) return new StringBuilder("Incorrect expression.");
        int beg, end;
        while (!stack.isEmpty()) {
            beg = stack.pop();
            end = s.indexOf(")", beg);
            s.delete(beg, end+1);
        }
        return s;
    } 

    public static boolean Parentheses(StringBuilder s, Deque<Integer> begs) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
                begs.push(i);
            }
            if (c == ')') {
                if (stack.isEmpty()) return false;
                else stack.pop();
            }
        }
        if (stack.isEmpty()) return true;
        else return false;
    }
}
