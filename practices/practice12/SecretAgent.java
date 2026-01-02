package practices.practice12;

public class SecretAgent implements Spy {
    
    public String name;
    public String info = "";

    public SecretAgent(String name) {
        this.name = name;
    }

    public void visit(GeneralStaff generalStaff) {
        info = "Stolen: " + generalStaff.secretPapers + " secret papers.";
        generalStaff.secretPapers = 0;
        info = "Collected: " + generalStaff.secretPapers + " secret papers.";
    }

    public void visit(MilitaryBase militaryBase) {
        info = "Collected: " + militaryBase;
    }
}
