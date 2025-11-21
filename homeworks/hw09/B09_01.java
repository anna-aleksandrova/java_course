package homeworks.hw09;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;

public class B09_01 {
    public static void main(String[] args) throws InterruptedException, IOException {
        createFile();

        double T1 = 0.5;
        double T2 = 1.0;
        double T3 = 1.5;
        
        long readTime = (long) (T1 * 1000);
        long processTime1 = (long) (T2 * 1000);
        long processTime2 = (long) (T3 * 1000);

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        Thread th1 = new Thread(() -> read(readTime, queue));
        Thread th2 = new Thread(() -> write("F1.txt", processTime1, queue));
        Thread th3 = new Thread(() -> write("F2.txt", processTime2, queue));

        Log.print("Start");
        th1.start();
        th2.start();
        th3.start();

        th1.join();
        th2.join();
        th3.join();

        Log.print("End");
    }

    public static void read(long time, ArrayBlockingQueue<String> queue) {
        Thread.currentThread().setName("Reader");
        try (BufferedReader br = new BufferedReader(new FileReader("F.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Thread.sleep(time);
                Log.print("read " + line);
                queue.put(line);
            }

            queue.put("");
            queue.put("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(String fileName, long time, ArrayBlockingQueue<String> queue) {
        Thread.currentThread().setName("Write-" + fileName);
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            while (true) {
                String line = queue.take();
                if (line.isEmpty()) {
                    break;
                }
                Thread.sleep(time);
                pw.println(line);
                Log.print("wrote " + line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void createFile() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("F.txt"))) {
            for (int i = 0; i < 10; i++) {
                pw.println("MSG-" + i);
            }
        }
    }
}
