package practices.practice12;

public class GeneralStaff implements MilitaryObject {
    
    public String title;
    public int generals;
    public int secretPapers;
    
    public GeneralStaff(String title, int generals, int secretPapers) {
        this.title = title;
        this.generals = generals;
        this.secretPapers = secretPapers;
    }   

    @Override
    public String toString() {
        return "MilitaryBase{" +
                "title='" + title + '\'' +
                ", generals=" + generals +
                ", secretPapers=" + secretPapers +
                '}';
    }
    
    @Override
    public void accept(Spy spy) {
        spy.visit(this);
    }
}
