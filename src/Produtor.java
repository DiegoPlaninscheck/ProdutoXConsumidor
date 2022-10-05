import java.util.Random;
import java.util.concurrent.Semaphore;

public class Produtor implements Runnable {
    private int id;
    private Semaphore semaforo;

    public Produtor(int id, Semaphore semaforo) {
        this.id = id;
        this.semaforo = semaforo;
    }

    Random radom = new Random();

    @Override
    public synchronized void run() {
        try {
            Thread.sleep((long) (Math.random() * 3000));
            semaforo.acquire();
            while (true) {
                int posicao = radom.nextInt(5);
                int numero = radom.nextInt(10);
                if (Main.buffer[posicao] == 0) {
                    Main.buffer[posicao] = numero;
                    System.out.println("Produtor: " + id + " produziu " + Main.buffer[posicao] + " na posicao: " + (posicao + 1));
                } else {
                    System.out.println("Produtor: " + id + " não pode colocar o numero " + Main.buffer[posicao] + " na posicao: " + (posicao + 1));
                }
                Main.mostrarBuffer();
                Thread.sleep((long) (Math.random() * 3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }

}
