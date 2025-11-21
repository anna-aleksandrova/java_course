package homeworks.hw09;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class B09_02 {
    public static AtomicLong totalWaitTime = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        double T1 = 0.5;
        double T2 = 1.0;
        double T3 = 2.0;
        double T4 = 4.0;
        
        final int n = 7; // amount of clients
        final int N = 3;
        
        Semaphore lock = new Semaphore(N, true);
        Thread[] clients = new Thread[n];
        Log.print("Start");
        long currentArriveTime = 0;

        for (int i = 0; i < n; i++) {
            long delta = (long) ((Math.random() * (T2 - T1) + T1) * 1000);
            currentArriveTime += delta;
            long stayTime = (long) ((Math.random() * (T4 - T3) + T3) * 1000);
            int j = i;
            long arrival = currentArriveTime;
            
            clients[i] = new Thread(() -> client(j, arrival, stayTime, lock));
            clients[i].start();
        }

        for (int i = 0; i < n; i++) clients[i].join();
        Log.print("End");
        System.out.printf("Average wait time: %.2f sec\n", (totalWaitTime.get() / 1000.0) / n);
    }

    public static void client(int i, long arriveTime, long stayTime, Semaphore lock) {
        Thread.currentThread().setName("Client " + i);
        try {
            Thread.sleep(arriveTime);
            Log.print("arrived");
            long startWait = System.currentTimeMillis();
            lock.acquire();
            long endWait = System.currentTimeMillis();
            totalWaitTime.addAndGet(endWait - startWait);
            Log.print("check-in (waited " + (endWait - startWait)/1000.0 + "s)");
            Thread.sleep(stayTime);
            lock.release();
            Log.print("check-out");
            
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
