package homeworks.hw08;

public class B_08_02 {
    public static void main(String[ ]args) {
        boolean s1 = check("(){}[]");      // true
        boolean s2 = check("[]({})");      // true
        boolean s3 = check(")(){}[]");     // false
        boolean s4 = check("()[]{}([{");   // false
        boolean s5 = check("()[}{]");      // false
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
    }

    public static boolean check(String pars) {
        Stack<Character> s = new Stack<Character>();
        for (int i = 0; i < pars.length(); i++) {
            char pars_i = pars.charAt(i);
            if (pars_i == '{' || pars_i == '[' || pars_i == '(') s.push(pars_i);
            else if (pars_i == '}' || pars_i == ']' || pars_i == ')') {
                try {
                    char prev = s.pop();
                    if (!(pars_i == '}' && prev == '{' || pars_i == ']' && prev == '[' || pars_i == ')' && prev == '(')) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        if (!s.empty()) return false;
        return true;
    }
}
