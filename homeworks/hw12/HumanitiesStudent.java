package homeworks.hw12;

public class HumanitiesStudent extends Student {
    public HumanitiesStudent(int requiredCredits, int money) {
        super(requiredCredits, money);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
