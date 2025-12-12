package homeworks.hw12;

public class DormCanteen implements Visitor{
    private int amount;

    public DormCanteen(int amount) {
        this.amount = amount;
    }

    private void charge(Student s) {
        s.pay(amount);
    }

    @Override
    public void visit(HumanitiesStudent s) { charge(s); }

    @Override
    public void visit(ScienceStudent s) { charge(s); }

    @Override
    public void visit(SHStudent s) { charge(s); }
}
