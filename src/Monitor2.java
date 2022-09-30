import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.concurrent.locks.ReadWriteLock;

public class Monitor2 implements Runnable{
    private ReadWriteLock readWriteLock;
    private String [][] garden;
    public Monitor2 (String [][] garden, ReadWriteLock readWriteLock){
        this.garden = garden;
        this.readWriteLock = readWriteLock;
    }
    @Override
    public void run() {
        while(true) {
            try {
                readWriteLock.readLock().lock();
                System.out.println("Монітор2");
                Thread.sleep(5000);
                for(int i = 0; i < garden.length; i++) {
                    for(int j = 0; j < garden[0].length; j++) {
                        System.out.print(garden[i][j] + " ");
                    }
                    System.out.println();
                }
                readWriteLock.readLock().unlock();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
