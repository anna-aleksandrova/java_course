package homeworks.hw12;

public class ScienceTeacher implements Visitor{
    private int credits;

    public ScienceTeacher(int credits) {
        this.credits = credits;
    }

    @Override
    public void visit(ScienceStudent s) {
        s.addCredits(credits);
    }

    @Override
    public void visit(HumanitiesStudent s) {
        // System.out.println("Can't teach a humanities student.");
    }

    @Override
    public void visit(SHStudent s) {
        s.addCredits(credits);
    }
}
