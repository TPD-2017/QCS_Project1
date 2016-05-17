import client.Voter;

import javax.swing.*;

/**
 * Created by dbast on 17/05/2016.
 */
public class Details {
    private JTextArea textArea1;
    public JPanel DetailsView;

    public Details(Voter voter){
        System.out.print(voter.technicalDetail());
        textArea1.setText(voter.technicalDetail());
    }
}
