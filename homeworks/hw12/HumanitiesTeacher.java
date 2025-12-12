package homeworks.hw12;

public class HumanitiesTeacher implements Visitor {
    private int credits;

    public HumanitiesTeacher(int credits) {
        this.credits = credits;
    }

    @Override
    public void visit(HumanitiesStudent s) {
        s.addCredits(credits);
    }

    @Override
    public void visit(ScienceStudent s) {
        // System.out.println("Can't teach a science student.");
    }

    @Override
    public void visit(SHStudent s) {
        s.addCredits(credits);
    }
}
