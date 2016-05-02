package client;

/**
 * Created by Tiago Andrade on 01/05/2016.
 *
 */

import service.*;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args){
        InsulinDoseCalculatorService service = new InsulinDoseCalculatorService();

        /*SO PARA TESTES*/
        InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
        int bolus_ins_replace_dose = proxy.mealtimeInsulinDose(60.0, 12.0, 200.0, 100.0, 25.0);
        int back_ins = proxy.backgroundInsulinDose(79.0);
        ArrayList<Double> lista1 = new ArrayList<>();
        ArrayList<Double> lista2 = new ArrayList<>();
        lista1.add(2.0);
        lista1.add(8.0);
        lista2.add(32.0);
        lista2.add(83.0);
        int personal = proxy.personalSensitivityToInsulin(6, lista1, lista2);
        System.out.println("resultado da primeira funcao: " + bolus_ins_replace_dose);
        System.out.println("resultado da segunda funcao: " + back_ins);
        System.out.println("resultado da terceira funcao: " + personal);
    }
}
