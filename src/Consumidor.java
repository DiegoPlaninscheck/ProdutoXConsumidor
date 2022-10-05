import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable {
    private int id;
    private Semaphore semaforo;

    public Consumidor(int id, Semaphore semaforo) {
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
                if (Main.buffer[posicao] != 0) {
                    System.out.println("Consumidor: " + id + " consumiu " + Main.buffer[posicao] + " na posicao: " + (posicao + 1));
                    Main.buffer[posicao] = 0;
                } else {
                    System.out.println("Consumidor: " + id + " não pode retirar o numero " + Main.buffer[posicao] + " na posicao: " + (posicao + 1));
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
