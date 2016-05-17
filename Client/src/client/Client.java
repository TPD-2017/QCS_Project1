package client;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by Tiago on 03/05/2016.
 */
public class Client {
    public static void main(String[] args){
        Voter voter = new Voter();
        //voter.calculateInsulinDose(79);
        //voter.mealtimeInsulinDose(60,12,200,100,25);
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        voter.calculateInsulinDose(79);
        System.out.println(voter.technicalDetail());
        /*ArrayList<Integer> lista1 = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();
        lista1.add(1);
        lista1.add(6);
        lista1.add(8);
        lista1.add(9);
        lista2.add(32);
        lista2.add(61);
        lista2.add(91);
        lista2.add(88);*/

        //voter.personalSensitivityToInsulin(4, lista1, lista2);
    }
}
