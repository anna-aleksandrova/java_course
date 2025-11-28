package practices.practice09;

import java.util.concurrent.locks.ReentrantLock;

public class A_09_02 {
    public static void main(String[] args) {
        
    }

    public static void train(long arriveTime, long passTime, ReentrantLock lock) {
        Thread.currentThread();
        try {
            Thread.sleep(arriveTime);
            log.print("arrived");
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        log.print("arrived");

    }
}
