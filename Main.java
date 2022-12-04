package room4;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        NamePain namePain = new NamePain();
        int lines = namePain.nameAndPain.length;

        System.out.println("We have " + lines + " wizards(threads)");

        Shelf shelf = new Shelf();
        Semaphore sem = new Semaphore(1);
        CommonResourse res = new CommonResourse();
        Thread[] wizzard = new Thread[lines];
        for (int i = 0; i < namePain.nameAndPain.length; i++) {
            String[] splitRes = namePain.nameAndPain[i].split(",");
            String name = splitRes[0];
            String potion = splitRes[1];
            System.out.println("We can create a thread with name: " + name + " and potion: " + potion);
            wizzard[i] = new Thread(new CountTheard(res, sem, name, potion, shelf));

            }

        for (int i = 0; i < lines; i++) {
            wizzard[i].start();
        }
        for (int i = 0; i < lines; i++) {
            try {
                wizzard[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        shelf.showShelf();
        shelf.saveShelf();

    }
}
