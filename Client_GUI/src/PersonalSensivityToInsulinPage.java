import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dbast on 12/05/2016.
 */
public class PersonalSensivityToInsulinPage {
    private JPanel PersonalSensivityToInsulinView;
    private JButton backButton;

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
    }

    public JPanel getPersonalSensivityToInsulinView() {
        return PersonalSensivityToInsulinView;
    }

    public void setPersonalSensivityToInsulinView(JPanel personalSensivityToInsulinView) {
        PersonalSensivityToInsulinView = personalSensivityToInsulinView;
    }
}
