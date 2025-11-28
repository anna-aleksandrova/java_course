package java_course.practices.practice05;

public class A_05_03 {
    public static void main(String[] args) {
        // System.setProperty("user.dir", System.getProperty("user.dir") + "/src");
        try {
            FileReader fr = new FileReader("input.txt");
            Scanner in = new Scanner(fr);
            String largestWord = "";

            // while ()
            FileWriter fw = new FileWriter("output.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println("lsfdksjfdlkjsf");
            pw.close();
            in.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    // некласична робота з файлами
    public static void F(String inp, String out) {
        File fin = new File(inp);
        File fout = new File(out);
        Path pathfin = fin.toPath();
        Path pathfout = fout.toPath();
    }
}
