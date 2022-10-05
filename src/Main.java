import java.util.concurrent.Semaphore;

public class Main {

    public static int[] buffer = {0, 0, 0, 0, 0};

    public static void main(String[] args) {
        new TelaBuffer();
        Semaphore semaphore = new Semaphore(5);
        Produtor produtor = new Produtor(1, semaphore);
        Consumidor consumidor = new Consumidor(1, semaphore);
        new Thread(produtor, "Produtor 1").start();
        new Thread(consumidor, "Consumidor 1").start();
        Produtor produtor2 = new Produtor(2, semaphore);
        Consumidor consumidor2 = new Consumidor(2, semaphore);
        new Thread(produtor2, "Produtor 2").start();
        new Thread(consumidor2, "Consumidor 2").start();
        Produtor produtor3 = new Produtor(3, semaphore);
        new Thread(produtor3, "Produtor 3").start();
    }

    public static void mostrarBuffer() {
        for (int i = 0; i < buffer.length; i++) {
            System.out.println("Posicao " + (i + 1) + ": " + buffer[i]);
        }
    }
}