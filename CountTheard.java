package room4;

import java.util.concurrent.Semaphore;

public class CountTheard implements Runnable{
    Shelf shelf;
    CommonResourse res;
    Semaphore sem;
    String name;

    String potionName;

    public CountTheard(CommonResourse res, Semaphore sem, String name, String potionName, Shelf shelf) {
        this.res = res;
        this.name = name;
        this.sem = sem;
        this.potionName = potionName;
        this.shelf = shelf;

    }

    @Override
    public void run() {
        try {
            System.out.println(name + " дет доступ к котлу");
            sem.acquire();
            System.out.println(name + " получил(а) доступ к котлу");
            res.x = 1;
            for (int i = 0; i < 5; i++) {
                System.out.println(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(name + " освободите доступ к котлу");
        System.out.println(name + " сварил: " + potionName);
        shelf.addPotion(potionName);

        sem.release();
    }

}
