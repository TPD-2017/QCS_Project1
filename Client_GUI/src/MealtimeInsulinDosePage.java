import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.Voter;

import static java.lang.Thread.sleep;

/**
 * Created by dbast on 12/05/2016.
 */
public class MealtimeInsulinDosePage {
    public JPanel MeltimeInsulinDoseView;
    private JButton calculateButton;
    private JTextField textField1;
    private JButton backButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;

    public MealtimeInsulinDosePage() {
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
                String i2 = textArea2.getText();
                String i3 = textArea3.getText();
                String i4 = textArea4.getText();
                String i5 = textArea5.getText();
                try {
                    int carbohydratesInTheMeal = Integer.parseInt(i1);
                    int carbohydratesProcessedBy1UnitOfInsulin = Integer.parseInt(i2);
                    int actualBloodSugarLevel = Integer.parseInt(i3);
                    int targetBloodSugarLevel = Integer.parseInt(i4);
                    int individualSensivity = Integer.parseInt(i5);
                    if (carbohydratesInTheMeal>=60 && carbohydratesInTheMeal<=120 && carbohydratesProcessedBy1UnitOfInsulin>=10
                            && carbohydratesProcessedBy1UnitOfInsulin<=15 && actualBloodSugarLevel>=50 && actualBloodSugarLevel<=250
                            && targetBloodSugarLevel>=80 && targetBloodSugarLevel<=120 && individualSensivity>=15 && individualSensivity<=100) {
                        // FAZER AQUI CHAMADA A FUNCAO
                        Voter voter = new Voter();
                        voter.mealtimeInsulinDose(carbohydratesInTheMeal, carbohydratesProcessedBy1UnitOfInsulin, actualBloodSugarLevel, targetBloodSugarLevel, individualSensivity);
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


    public JPanel getMeltimeInsulinDoseView() {
        return MeltimeInsulinDoseView;
    }

}
