import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable {
    private int id;
    private Semaphore semaforo;
    private JLabel textoConsumidor, bufferAtual;
    private Boolean executar = true;

    public Consumidor(int id, Semaphore semaforo, JLabel textoConsumidor, JLabel buffer, Boolean executar) {
        this.id = id;
        this.semaforo = semaforo;
        this.textoConsumidor = textoConsumidor;
        this.bufferAtual = buffer;
        this.executar = executar;
    }

    Random radom = new Random();

    @Override
    public synchronized void run() {
        try {
            Thread.sleep((long) (Math.random() * 3000));
            semaforo.acquire();
            while (this.getExecutar()) {
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
        }
    }

    public void setTexto(String texto) {
        this.textoConsumidor.setText(texto);
    }

    public void mostrarBuffer() {
        int[] buffer = TelaBuffer.buffer;
        bufferAtual.setText("{ " + buffer[0] + ", " + buffer[1] + ", " + buffer[2] + ", " + buffer[3] + ", " + buffer[4] + "}");
    }

    public Boolean getExecutar() {
        return executar;
    }

    public void setExecutar(Boolean executar) {
        this.executar = executar;
    }
}
