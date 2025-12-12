package homeworks.hw12;

public class ScholarshipParents implements Visitor {
    private int amount;

    public ScholarshipParents(int amount) {
        this.amount = amount;
    }

    private void pay(Student s) {
        s.obtain(amount);
    }

    @Override
    public void visit(HumanitiesStudent s) { pay(s); }

    @Override
    public void visit(ScienceStudent s) { pay(s); }

    @Override
    public void visit(SHStudent s) { pay(s); }
}
