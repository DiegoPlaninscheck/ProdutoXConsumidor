import javax.swing.*;
import java.util.concurrent.Semaphore;

public class TelaBuffer extends JFrame implements Runnable {
    private JPanel telaBuffer;
    private JButton startButton;
    private JButton stopButton;
    private JLabel textoProdutor1;
    private JLabel textoConsumidor1;
    private JLabel textoProdutor2;
    private JLabel textoProdutor3;
    private JLabel textoConsumidor2;
    private JLabel bufferAtual;
    public Semaphore semaphore = new Semaphore(5);
    public static volatile int[] buffer = {0, 0, 0, 0, 0};

    public TelaBuffer() {
        Produtor produtor = new Produtor(1, semaphore, textoProdutor1, bufferAtual, false);
        Consumidor consumidor = new Consumidor(1, semaphore, textoConsumidor1, bufferAtual, false);
        Produtor produtor2 = new Produtor(2, semaphore, textoProdutor2, bufferAtual, false);
        Consumidor consumidor2 = new Consumidor(2, semaphore, textoConsumidor2, bufferAtual, false);
        Produtor produtor3 = new Produtor(3, semaphore, textoProdutor3, bufferAtual, false);
        new Thread(produtor, "produtor1").start();
        new Thread(consumidor, "consumidor1").start();
        new Thread(produtor2, "produtor2").start();
        new Thread(consumidor2, "consumidor2").start();
        new Thread(produtor3, "produtor3").start();
        criarComponentes();
        startButton.addActionListener(e -> {
            produtor.setExecutar(true);
            produtor2.setExecutar(true);
            produtor3.setExecutar(true);
            consumidor.setExecutar(true);
            consumidor2.setExecutar(true);
        });
        stopButton.addActionListener(e -> {
            produtor.setExecutar(false);
            produtor2.setExecutar(false);
            produtor3.setExecutar(false);
            consumidor.setExecutar(false);
            consumidor2.setExecutar(false);
        });
    }

    @Override
    public void run() {
        if (!isVisible()) {
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "A janela ja esta aberta");
        }
    }

    private void criarComponentes() {
        setContentPane(telaBuffer);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        run();
        pack();
    }

    public static void main(String[] args) {
        TelaBuffer telaBuffer = new TelaBuffer();
    }
}
