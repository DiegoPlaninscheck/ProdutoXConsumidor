import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable {
    private int id;
    private Semaphore semaforo;
    private JLabel textoConsumidor, bufferAtual;

    public Consumidor(int id, Semaphore semaforo, JLabel textoConsumidor, JLabel buffer) {
        this.id = id;
        this.semaforo = semaforo;
        this.textoConsumidor = textoConsumidor;
        this.bufferAtual = buffer;
    }

    Random radom = new Random();

    @Override
    public synchronized void run() {
        try {
            Thread.sleep((long) (Math.random() * 3000));
            semaforo.acquire();
            while (true) {
                int posicao = radom.nextInt(5);
                if (TelaBuffer.buffer[posicao] != 0) {
                    System.out.println("Consumidor: " + id + " consumiu " + TelaBuffer.buffer[posicao] + " na posicao: " + (posicao + 1));
                    setTexto("Consumiu " + TelaBuffer.buffer[posicao] + " na posição " + (posicao + 1));
                    TelaBuffer.buffer[posicao] = 0;
                } else {
                    setTexto("Posição " + (posicao + 1) + " vazia");
                    System.out.println("Consumidor: " + id + " não pode retirar o numero " + TelaBuffer.buffer[posicao] + " na posicao: " + (posicao + 1));
                }
                mostrarBuffer();
                Thread.sleep((long) (Math.random() * 3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }

    public void setTexto(String texto) {
        this.textoConsumidor.setText(texto);
    }

    public void mostrarBuffer() {
        int[] buffer = TelaBuffer.buffer;
        bufferAtual.setText("{ " + buffer[0] + ", " + buffer[1] + ", " + buffer[2] + ", " + buffer[3] + ", " + buffer[4] + "}");
    }
}
