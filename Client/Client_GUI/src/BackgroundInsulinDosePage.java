import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dbast on 12/05/2016.
 */
public class BackgroundInsulinDosePage {
    private JPanel BackgroundInsulinDoseView;
    private JButton backButton;
    private JButton calculateButton;
    private JTextField textField1;
    private JTextArea textArea1;

    public BackgroundInsulinDosePage() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage hp = new HomePage();
                JFrame frame = hp.getFrame();
                JPanel jp = hp.getHomePageView();
                frame.setContentPane(jp);
                frame.invalidate();
                frame.validate();
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String i1 = textArea1.getText();
                try {
                    int weight = Integer.parseInt(i1);
                    if (weight>=40 && weight<=130) {
                        // FAZER AQUI CHAMADA A FUNCAO!
                        int result = 0;
                        textField1.setText(result+"");
                    } else {
                        textField1.setText("Invalid Input");
                    }
                } catch (NumberFormatException n) {
                    textField1.setText("Invalid Input");
                }
            }
        });
    }

    public JPanel getBackgroundInsulinDoseView() {
        return BackgroundInsulinDoseView;
    }

    public void setBackgroundInsulinDoseView(JPanel backgroundInsulinDoseView) {
        BackgroundInsulinDoseView = backgroundInsulinDoseView;
    }
}
