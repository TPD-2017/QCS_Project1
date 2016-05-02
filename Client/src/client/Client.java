package client;

/**
 * Created by Tiago Andrade on 01/05/2016.
 *
 */

import service.*;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args){
        InsulinDoseCalculator_Service service = new InsulinDoseCalculator_Service();

        /*SO PARA TESTES*/
        InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
        int bolus_ins_replace_dose = proxy.mealtimeInsulinDose(60, 12, 200, 100, 25);
        int back_ins = proxy.backgroundInsulinDose(79);
        ArrayList<Integer> lista1 = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();
        lista1.add(2);
        lista1.add(8);
        lista2.add(32);
        lista2.add(83);
        int personal = proxy.personalSensitivityToInsulin(6, lista1, lista2);
        System.out.println("resultado da primeira funcao: " + bolus_ins_replace_dose);
        System.out.println("resultado da segunda funcao: " + back_ins);
        System.out.println("resultado da terceira funcao: " + personal);
    }
}
