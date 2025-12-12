package homeworks.hw12;

public interface Visitor {
    void visit(HumanitiesStudent s);
    void visit(ScienceStudent s);
    void visit(SHStudent s);
}
