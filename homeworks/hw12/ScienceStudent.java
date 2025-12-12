package homeworks.hw12;

public class ScienceStudent extends Student {
    public ScienceStudent(int requiredCredits, int money) {
        super(requiredCredits, money);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
