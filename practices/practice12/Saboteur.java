package practices.practice12;

public class Saboteur implements Spy{
    
    public String name;
    public String info = "";

    public Saboteur(String name) {
        this.name = name;
    }

    @Override
    public void visit(GeneralStaff generalStaff) {
        info = "Destroyed: " + generalStaff.generals + " generals, " + generalStaff.secretPapers + " secret papers.";
        generalStaff.generals = 0;
        generalStaff.secretPapers = 0;
    }

    @Override
    public void visit(MilitaryBase militaryBase) {
        info = "Destroyed: " + militaryBase.soldiers + " soldiers, " + militaryBase.tanks + " tanks.";
        militaryBase.soldiers = 0;
        militaryBase.tanks = 0;
    }

    @Override
    public String toString() {
        return "Saboteur{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
