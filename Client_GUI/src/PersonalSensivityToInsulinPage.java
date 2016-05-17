import client.Voter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by dbast on 12/05/2016.
 */
public class PersonalSensivityToInsulinPage {
    private JPanel PersonalSensivityToInsulinView;
    private JButton backButton;
    private JButton calculateButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton seeDetailsButton;

    public PersonalSensivityToInsulinPage() {
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
                try {
                    int physicalActivityLevel = Integer.parseInt(i1);
                    int kSamplesOfPhysicalActivity = Integer.parseInt(i2);
                    int kSamplesDropsInBloodSugar = Integer.parseInt(i3);
                    if(physicalActivityLevel>=0 && physicalActivityLevel<=10 && kSamplesOfPhysicalActivity>=0
                            && kSamplesOfPhysicalActivity<=10 && kSamplesDropsInBloodSugar>=15 && kSamplesDropsInBloodSugar<=100)
                    {
                        // FAZER AQUI CHAMADA A FUNCAO!
                        Voter voter = new Voter();

                        //voter.
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
                } catch (NumberFormatException n){
                    textField1.setText("Invalid Input");
                }
            }
        });
    }

    public JPanel getPersonalSensivityToInsulinView() {
        return PersonalSensivityToInsulinView;
    }

    public void setPersonalSensivityToInsulinView(JPanel personalSensivityToInsulinView) {
        PersonalSensivityToInsulinView = personalSensivityToInsulinView;
    }
}
