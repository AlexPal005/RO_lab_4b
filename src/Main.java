import java.util.concurrent.locks.*;

public class Main {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private String[][] garden;
    public static void main(String[] args) {
        new Main().start();
    }
    private void start(){
        create_garden();
        print_garden();
        Gardener gardener = new Gardener(garden, readWriteLock);
        new Thread(gardener).start();
        Nature nature = new Nature(garden, readWriteLock);
        new Thread(nature).start();
        Monitor2 monitor2 = new Monitor2(garden, readWriteLock);
        new Thread(monitor2).start();
        Monitor1 monitor1 = new Monitor1(garden, readWriteLock);
        new Thread(monitor1).start();

    }
    private void create_garden(){
        /*
        Стани рослин:
        1)Зів'яли
        2)Ростуть
        3)Полито
        4)Засохли
         */
        garden = new String[5][5];
        for(int i = 0; i < garden.length; i++){
            for(int j = 0; j < garden[0].length; j++){
                int val = (int) (Math.random()*2) ;
                if(val == 0){
                    garden[i][j] = "Зів'яли";
                }
                else{
                    garden[i][j] = "Ростуть";
                }
            }
        }

    }
    public void print_garden(){
        for(int i = 0; i < garden.length; i++) {
            for(int j = 0; j < garden[0].length; j++) {
                System.out.print(garden[i][j] + " ");
            }
            System.out.println();
        }
    }
}