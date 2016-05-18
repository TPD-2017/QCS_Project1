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
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton seeDetailsButton;

    Voter voter;

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
                String i1 = textField2.getText();
                String i2 = textField3.getText();
                String i3 = textField4.getText();
                String i4 = textField5.getText();
                String i5 = textField6.getText();
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
                        voter = new Voter();
                        int result = voter.mealtimeInsulinDose(carbohydratesInTheMeal, carbohydratesProcessedBy1UnitOfInsulin, actualBloodSugarLevel, targetBloodSugarLevel, individualSensivity);
                        textField1.setText(result+"");
                        seeDetailsButton.setEnabled(true);
                    } else {
                        textField1.setText("Invalid Input");
                    }
                } catch (NumberFormatException n) {
                    textField1.setText("Invalid Input");
                }
            }
        });

        seeDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame detailsFrame = new JFrame("Details");
                detailsFrame.setContentPane(new Details(voter).DetailsView);
                detailsFrame.setResizable(false);
                detailsFrame.pack();
                detailsFrame.setLocationRelativeTo(null);
                detailsFrame.setVisible(true);
            }
        });
    }


    public JPanel getMeltimeInsulinDoseView() {
        return MeltimeInsulinDoseView;
    }

}
