package practices.practice09;

import javax.management.RuntimeErrorException;

public class example01 {
    public static void main(String[] args) {
        Thread1 t01 = new Thread1();
        Thread2 t02 = new Thread2();

        System.out.println("START");
        t01.start();
        t02.start();
        try {
            t01.join();
            t02.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("END");
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            Thread.yield();
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        for (int i = -1; i > -31; i--) {
            System.out.println(i);
        }
    }
}