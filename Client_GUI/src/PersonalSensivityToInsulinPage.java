import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dbast on 12/05/2016.
 */
public class PersonalSensivityToInsulinPage {
    private JPanel PersonalSensivityToInsulinView;
    private JButton backButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JButton calculateButton;
    private JTextField textField1;

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
                String i1 = textArea1.getText();
                String i2 = textArea2.getText();
                String i3 = textArea3.getText();
                try {
                    int physicalActivityLevel = Integer.parseInt(i1);
                    int kSamplesOfPhysicalActivity = Integer.parseInt(i2);
                    int kSamplesDropsInBloodSugar = Integer.parseInt(i3);
                    if(physicalActivityLevel>=0 && physicalActivityLevel<=10 && kSamplesOfPhysicalActivity>=0
                            && kSamplesOfPhysicalActivity<=10 && kSamplesDropsInBloodSugar>=15 && kSamplesDropsInBloodSugar<=100)
                    {
                        // FAZER AQUI CHAMADA A FUNCAO!
                        textField1.setText(physicalActivityLevel+"");
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
