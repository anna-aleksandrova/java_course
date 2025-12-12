package homeworks.hw12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (int i = 1; i < 15; i++) {
            run(i);
        }
    }

    public static void run(int i) {
        String filename = "";
        if (i > 0 && i < 10) {
            filename = "homeworks/hw12/input/input" + "0" + Integer.toString(i) + ".txt";
        } 
        if (i > 9) {
            filename = "homeworks/hw12/input/input" + Integer.toString(i) + ".txt";
        }
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNext()) return;

            String type = scanner.next();
            int requiredCredits = scanner.nextInt();
            int startMoney = scanner.nextInt();

            Student student = createStudent(type, requiredCredits, startMoney);

            System.out.println("---------- Student: " + type + " ----------");
            System.out.println("Start: money=" + startMoney + ", requiredCredits=" + requiredCredits);

            while (scanner.hasNext()) {
                if (student.isExpelled) {
                    System.out.println("---------- Student is expelled ----------");
                    break;
                }

                String command = scanner.next();
                String subType = scanner.next();
                if (!scanner.hasNextInt()) break; 
                int amount = scanner.nextInt();

                Visitor visitor = null;

                switch (command) {
                    case "teach":
                        if (subType.equals("natural")) {
                            visitor = new ScienceTeacher(amount);
                        } else if (subType.equals("humanitarian")) {
                            visitor = new HumanitiesTeacher(amount);
                        }
                        break;

                    case "obtain":
                        if (subType.equals("scholarship") || subType.equals("help")) {
                            visitor = new ScholarshipParents(amount);
                        }
                        break;

                    case "pay":
                        if (subType.equals("hostel") || subType.equals("canteen")) {
                            visitor = new DormCanteen(amount);
                        }
                        break;
                }

                if (visitor != null) {
                    student.accept(visitor);
                }
            }

            if (student.isExpelled) {
                System.out.println("Student has been expelled. No diploma.");
            } else {
                System.out.println("End: ");
                System.out.println("Money left: " + student.money + "; credits obtained: " + student.credits);
                System.out.println("Student has got a diploma.");
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    private static Student createStudent(String type, int credits, int money) {
        switch (type.toLowerCase()) {
            case "humanitarian":
                return new HumanitiesStudent(credits, money);
            case "natural":
                return new ScienceStudent(credits, money);
            case "natural-humanitarian":
                return new SHStudent(credits, money);
            default:
                return null;
        }
    }
}
