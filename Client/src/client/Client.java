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
        //voter.mealtimeInsulinDose(120,14,170,100,60);
        //voter.calculateInsulinDose(79);
        ArrayList<Integer> lista1 = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();
        lista1.add(1);
        lista1.add(3);
        lista1.add(10);
        lista2.add(33);
        lista2.add(43);
        lista2.add(70);

        voter.personalSensitivityToInsulin(0, lista1, lista2);


        System.out.println(voter.technicalDetail());
    }
}
