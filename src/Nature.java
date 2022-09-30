import java.util.concurrent.locks.ReadWriteLock;

public class Nature implements Runnable {
    private ReadWriteLock readWriteLock;
    private String [][] garden;
    public Nature (String [][] garden, ReadWriteLock readWriteLock){
        this.garden = garden;
        this.readWriteLock = readWriteLock;
    }
    @Override
    public void run() {
        while (true) {
            try {
                readWriteLock.writeLock().lock();
                System.out.println("Природа");
                Thread.sleep(500);

                for (int i = 0; i < garden.length; i++) {
                    for (int j = 0; j < garden[0].length; j++) {
                        int val = (int) (Math.random() * 5);
                        if (val == 0) {
                            garden[i][j] = "Засохли";
                        } else if (val == 2) {
                            garden[i][j] = "Зів'яли";
                        }
                    }
                }
                readWriteLock.writeLock().unlock();
                Thread.sleep(500);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
