package homeworks.hw05;

public class B_05_02a {
    public static void main(String[] args) {
        System.out.println(check(""));
        System.out.println(check("0"));
        System.out.println(check("0sladfkj"));
        System.out.println(check("2k3"));
        System.out.println(check("2klkas"));
        System.out.println(check("2klkas3"));
        System.out.println(check("2kl"));
    }

    public static boolean check(String st) {
        if (st.length() == 0) return false;
        StringBuilder s = new StringBuilder(st);
        char f = st.charAt(0);
        int first = Character.getNumericValue(f);
        if (first == 0) return false;
        if (s.length() - 1 != first) return false;
        for (int i = 1; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i))) return false;
        }
        return true;
    }
}
