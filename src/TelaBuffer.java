import javax.swing.*;

public class TelaBuffer extends JFrame {
    private JPanel telaBuffer;
    private JButton startButton;
    private JButton stopButton;

    public TelaBuffer() {
        criarComponentes();
        startButton.addActionListener(e -> {

        });
        stopButton.addActionListener(e -> {

        });
    }

    private void criarComponentes() {
        setContentPane(telaBuffer);
        pack();
        setVisible(true);
    }

}
