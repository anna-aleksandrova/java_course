package practices.practice12;

public class Main {
    public static void main(String[] args) {
        var GenStaff = new GeneralStaff("alpha", 100, 200);
        var MilBase = new MilitaryBase("beta", 1000, 50);

        System.out.println(GenStaff);
        System.out.println(MilBase);

        var saboteur = new Saboteur("007");
        var secretAgent = new SecretAgent("008");

        GenStaff.accept(secretAgent);
        System.out.println(secretAgent.info);

        MilBase.accept(secretAgent);
        System.out.println(secretAgent.info);

        GenStaff.accept(saboteur);
        System.out.println(saboteur.info);
        
        MilBase.accept(saboteur);
        System.out.println(saboteur.info);
    }
}
