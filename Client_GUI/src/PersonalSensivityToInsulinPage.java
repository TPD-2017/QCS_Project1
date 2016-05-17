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
    Voter voter;

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
                    String[] kspa = i2.trim().split("\\s*,\\s*");
                    ArrayList<Integer> kSamplesOfPhysicalActivity = new ArrayList<Integer>();
                    System.out.println(kspa.length);
                    for (String aKspa : kspa) {
                        kSamplesOfPhysicalActivity.add(Integer.parseInt(aKspa));
                    }
                    String[] ksdbs = i3.trim().split("\\s*,\\s*");
                    ArrayList<Integer> kSamplesDropsInBloodSugar = new ArrayList<Integer>();
                    System.out.println(ksdbs.length);
                    for (String ksdb : ksdbs) {
                        kSamplesDropsInBloodSugar.add(Integer.parseInt(ksdb));
                    }

                    if(physicalActivityLevel>=0 && physicalActivityLevel<=10 && kspa.length==ksdbs.length && kspa.length>1)
                    {
                        // FAZER AQUI CHAMADA A FUNCAO!
                        voter = new Voter();
                        voter.personalSensitivityToInsulin(physicalActivityLevel,kSamplesOfPhysicalActivity, kSamplesDropsInBloodSugar);
                        try {
                            sleep(4000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        String result =voter.majority();
                        textField1.setText(result+"");
                        seeDetailsButton.setEnabled(true);
                    } else {
                        textField1.setText("Invalid Input");
                    }
                } catch (NumberFormatException n){
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
                detailsFrame.setVisible(true);
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
