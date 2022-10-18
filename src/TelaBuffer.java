import javax.swing.*;
import java.util.concurrent.Semaphore;

public class TelaBuffer extends JFrame implements Runnable {
    private JPanel telaBuffer;
    private JButton startButton;
    private JButton stopButton;
    private JLabel produtor1;
    private JLabel consumidor1;
    private JLabel produtor2;
    private JLabel produtor3;
    private JLabel consumidor2;
    private JLabel bufferAtual;
    public Semaphore semaphore = new Semaphore(5);
    public static volatile int[] buffer = {0, 0, 0, 0, 0};

    public TelaBuffer() {
        Produtor produtor = new Produtor(1, semaphore, produtor1, bufferAtual, true);
        new Thread(produtor, "produtor1").start();
        criarComponentes();
        startButton.addActionListener(e -> {
            produtor.setExecutar(true);
            produtor.run();
//            new Thread(new Consumidor(1, semaphore, consumidor1, bufferAtual), "consumidor1").start();
//            new Thread(new Produtor(2, semaphore, produtor2, bufferAtual), "produtor2").start();
//            new Thread(new Consumidor(2, semaphore, consumidor2, bufferAtual), "comnsumidor2").start();
//            new Thread(new Produtor(3, semaphore, produtor3, bufferAtual), "produtor3").start();
        });
        stopButton.addActionListener(e -> {
            produtor.setExecutar(false);
            produtor.run();
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
