import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * Created by dbast on 11/05/2016.
 */
public class HomePage {
    private static JFrame frame;
    private JPanel HomePageView;
    private JButton mealTimeInsulinDoseButton;
    private JButton backgroundInsulinDoseButton;
    private JButton personalSensitivityToInsulinButton;


    public HomePage() {
        mealTimeInsulinDoseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MealtimeInsulinDosePage mid = new MealtimeInsulinDosePage();
                JPanel jp = mid.getMeltimeInsulinDoseView();
                frame.setContentPane(jp);
                frame.invalidate();
                frame.validate();
            }
        });

        backgroundInsulinDoseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackgroundInsulinDosePage bid = new BackgroundInsulinDosePage();
                JPanel jp = bid.getBackgroundInsulinDoseView();
                frame.setContentPane(jp);
                frame.invalidate();
                frame.validate();
            }
        });

        personalSensitivityToInsulinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonalSensivityToInsulinPage psi = new PersonalSensivityToInsulinPage();
                JPanel jp = psi.getPersonalSensivityToInsulinView();
                frame.setContentPane(jp);
                frame.invalidate();
                frame.validate();
            }
        });
    }

    public static JFrame getFrame() {
        return frame;
    }

    public JPanel getHomePageView() {
        return HomePageView;
    }

    public static void main(String[] args) {
        frame = new JFrame("Highly Reliable Insulin Dose Calculator");
        frame.setContentPane(new HomePage().HomePageView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
