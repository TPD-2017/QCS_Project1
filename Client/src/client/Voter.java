package client;

/**
 * Created by Tiago Andrade on 01/05/2016.
 *
 */

import service.InsulinDoseCalculator;

import java.util.ArrayList;
import java.util.List;

public class Voter {

    public Voter(){

    }

    public static void TestVoter(){

        List<WebServiceHandler> webservices = new ArrayList<>();

        webservices.add(new WebServiceHandler("http://qcsproject1-qcsproject.rhcloud.com/InsulinDoseCalculator/?wsdl","InsulinDoseCalculator", "InsulinDoseCalculatorPort", "http://server/"));
        webservices.add(new WebServiceHandler("http://qcsa1-beardsdei.rhcloud.com/qcsa1/InsulinDoseCalculator?wsdl","InsulinDoseCalculatorService", "InsulinDoseCalculatorPort", "http://server/"));
        webservices.add(new WebServiceHandler("http://insulincalculator-aybareon.rhcloud.com:80/InsulinCalculatorTomcat/InsulinCalculator?wsdl","InsulinCalculator", "InsulinCalculatorPort", "http://insulincalculator-aybareon.rhcloud.com/InsulinCalculatorTomcat"));
        InsulinDoseCalculator proxy = null;

        for(WebServiceHandler x: webservices){
            try {
                proxy = x.getProxy();
                System.out.println("Resultado: " + proxy.mealtimeInsulinDose(60, 12, 200, 100, 25));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
        try {
            proxy = webservice.getProxy();
            System.out.println("resultado da primeira funcao: " + proxy.backgroundInsulinDose(79));
        } catch (Exception e) {
            e.printStackTrace();
        }
        */


        /*InsulinDoseCalculator_Service service = new InsulinDoseCalculator_Service();

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
        System.out.println("resultado da terceira funcao: " + personal);*/
    }
}
