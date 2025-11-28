package java_course.practices.practice05;

public class A_05_02 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("amma"));
    }

    public static boolean isPalindrome(String s) {
        StringBuffer new_s = new StringBuffer(s.replaceAll("\\s", " "));
        String rev = new_s.reverse().toString();
        return rev.equals(s);
    }
}
