import javax.swing.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Produtor implements Runnable {
    private int id;
    private Semaphore semaforo;
    private JLabel textoProdutor, bufferAtual;
    private Boolean executar;

    public Produtor(int id, Semaphore semaforo, JLabel textoProdutor, JLabel buffer, Boolean executar) {
        this.id = id;
        this.semaforo = semaforo;
        this.textoProdutor = textoProdutor;
        this.bufferAtual = buffer;
        this.executar = executar;
    }

    @Override
    public synchronized void run() {
        Random radom = new Random();
        try {
            Thread.sleep((long) (Math.random() * 3000));
            semaforo.acquire();
            while (this.getExecutar()) {
                int posicao = radom.nextInt(5);
                if (TelaBuffer.buffer[posicao] == 0) {
                    int numero = radom.nextInt(10);
                    TelaBuffer.buffer[posicao] = numero;
                    System.out.println("Produtor: " + id + " produziu " + TelaBuffer.buffer[posicao] + " na posicao: " + (posicao + 1));
                    setTexto("Adicionou " + numero + " na posição " + (posicao + 1));
                } else {
                    System.out.println("Produtor: " + id + " não pode colocar o numero " + TelaBuffer.buffer[posicao] + " na posicao: " + (posicao + 1));
                    setTexto("Posição " + (posicao + 1) + " já preenchida");
                }
                mostrarBuffer();
                Thread.sleep((long) (Math.random() * 3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTexto(String texto) {
        this.textoProdutor.setText(texto);
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
