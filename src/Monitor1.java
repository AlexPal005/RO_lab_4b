import java.util.concurrent.locks.ReadWriteLock;
import java.io.FileWriter;
import java.io.*;

public class Monitor1 implements Runnable{
    private ReadWriteLock readWriteLock;
    private String [][] garden;
    public Monitor1 (String [][] garden, ReadWriteLock readWriteLock){
        this.garden = garden;
        this.readWriteLock = readWriteLock;
    }
    @Override
    public void run() {
        while(true) {
            try {
                readWriteLock.readLock().lock();
                System.out.println("Монітор1");
                FileWriter writer = new FileWriter("C:\\Users\\Qwerty\\Desktop\\3 course\\RO\\lab_4\\RO_lab_4b\\src\\test.txt", true);
                BufferedWriter w = new BufferedWriter(writer);
                Thread.sleep(5000);
                for (int i = 0; i < garden.length; i++) {
                    for (int j = 0; j < garden[0].length; j++) {
                        w.write(garden[i][j] + " ");
                    }
                    w.append("\n");
                }
                w.append("\n");
                w.append("\n");
                w.close();
                readWriteLock.readLock().unlock();
                Thread.sleep(500);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
