import java.util.concurrent.locks.*;

public class Gardener implements Runnable{
    private ReadWriteLock readWriteLock;
    private String [][] garden;
    public Gardener(String [][] garden, ReadWriteLock readWriteLock){
        this.garden = garden;
        this.readWriteLock = readWriteLock;
    }
    @Override
    public synchronized void run() {
        while(true) {
            try {
                readWriteLock.writeLock().lock();
                System.out.println("Садівник");
                Thread.sleep(500);

                for (int i = 0; i < garden.length; i++) {
                    for (int j = 0; j < garden[0].length; j++) {
                        if (garden[i][j].equals("Зів'яли")) {
                            garden[i][j] = "Полито";
                        }
                    }
                }
                readWriteLock.writeLock().unlock();
                Thread.sleep(500);
            } catch (InterruptedException e ) {
                throw new RuntimeException(e);
            }
        }

    }


}
