import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dbast on 12/05/2016.
 */
public class BackgroundInsulinDosePage {
    private JPanel BackgroundInsulinDoseView;
    private JButton backButton;

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
    }

    public JPanel getBackgroundInsulinDoseView() {
        return BackgroundInsulinDoseView;
    }

    public void setBackgroundInsulinDoseView(JPanel backgroundInsulinDoseView) {
        BackgroundInsulinDoseView = backgroundInsulinDoseView;
    }
}
