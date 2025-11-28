package practices.practice08;
import java.util.Stack;

public class B_08_04 {
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> s3 = new Stack<>();

        for (int i = 0; i < 7; i++) s1.push(i);
        for (int i = 7; i < 14; i++) s2.push(i);

        System.out.print("s1: ");
        System.out.println(s1);
        System.out.print("s2: ");
        System.out.println(s2);

        while (!s1.empty()) {
            s3.push(s1.pop());
        }

        while (!s2.empty()) {
            s1.push(s2.pop());
        }

        while (!s3.empty()) {
            s2.push(s3.pop());
        }

        System.out.print("s1: ");
        System.out.println(s1);
        System.out.print("s2: ");
        System.out.println(s2);
    }
}
