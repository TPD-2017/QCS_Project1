import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            }
        });
    }


    public JPanel getMeltimeInsulinDoseView() {
        return MeltimeInsulinDoseView;
    }

}
