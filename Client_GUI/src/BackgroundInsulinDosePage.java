import client.Voter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

/**
 * Created by dbast on 12/05/2016.
 */
public class BackgroundInsulinDosePage {
    private JPanel BackgroundInsulinDoseView;
    private JButton backButton;
    private JButton calculateButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton seeDetailsButton;

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
                String i1 = textField2.getText();
                try {
                    int weight = Integer.parseInt(i1);
                    if (weight>=40 && weight<=130) {
                        // FAZER AQUI CHAMADA A FUNCAO!
                        Voter voter = new Voter();
                        voter.calculateInsulinDose(weight);
                        try {
                            sleep(4000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        voter.majority();
                        int result = voter.getMostfreqent();
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
