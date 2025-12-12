package homeworks.hw12;

public class SHStudent extends Student {
    public SHStudent(int requiredCredits, int money) {
        super(requiredCredits, money);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
